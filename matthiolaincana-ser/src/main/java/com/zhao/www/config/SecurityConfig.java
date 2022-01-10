package com.zhao.www.config;

import cn.hutool.core.lang.UUID;
import com.zhao.www.entity.ServiceCode;
import com.zhao.www.entity.ServiceResult;
import com.zhao.www.service.login.LoginService;
import com.zhao.www.utils.json.JsonUtil;
import com.zhao.www.utils.jwt.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Matthiola incana
 * @create 2021/10/18 16:59
 */
@Log4j2
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    private final LoginService loginService;
    private final RedisTemplate<String,Object> redisTemplate;
    private final static String CONTENTTYPE = "application/json;charset=utf-8";
    private final static String USERNAME = "username";
    private final static String PASSWORD = "password";
    private final static String LOGINURL = "/login/userLogin";
    private final static String LOGOUTURL = "/login/userLogout";

    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() {
        return new JwtAuthenticationTokenFilter(loginService,redisTemplate);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.formLogin()
                .loginProcessingUrl(LOGINURL).permitAll()
                .usernameParameter(USERNAME)
                .passwordParameter(PASSWORD)
                //自定义登录失败
                .failureHandler((httpServletRequest, httpServletResponse, e) -> {
                    httpServletResponse.setContentType(CONTENTTYPE);
                    PrintWriter writer = httpServletResponse.getWriter();
                    ServiceResult<ServiceCode> result;
                    if (e instanceof UsernameNotFoundException){
                        result = ServiceResult.error("用户名错误");
                    }else if (e instanceof BadCredentialsException){
                        result = ServiceResult.error("密码错误");
                    }else if (e instanceof DisabledException){
                        result = ServiceResult.error("账户已经被禁用");
                    }else if (e instanceof LockedException){
                        result = ServiceResult.error("账户锁定");
                    }else if (e instanceof AccountExpiredException){
                        result = ServiceResult.error("账户过期");
                    }else if (e instanceof CredentialsExpiredException){
                        result = ServiceResult.error("证书过期");
                    }else {
                        result = ServiceResult.error("登陆失败");
                    }
                    writer.write(JsonUtil.toJSONStringWithDateFormat(result));
                    writer.flush();
                    writer.close();
                })
                //自定义登录成功
                .successHandler((httpServletRequest, httpServletResponse, authentication) -> {
                    httpServletResponse.setContentType(CONTENTTYPE);
                    PrintWriter writer = httpServletResponse.getWriter();
                    String tokenSecret = UUID.randomUUID().toString();
                    String token = JwtTokenUtil.creatToken(authentication.getName(), tokenSecret);
                    redisTemplate.opsForValue().set(token,tokenSecret,2, TimeUnit.HOURS);
                    Map<String, Object> map = new HashMap<>(1);
                    map.put("token",token);
                    writer.write(JsonUtil.toJSONStringWithDateFormat(ServiceResult.success(map, 200, "登陆成功")));
                    writer.flush();
                    writer.close();
                })
                .permitAll()

        .and().exceptionHandling()
                .authenticationEntryPoint(((httpServletRequest, httpServletResponse, e) -> ServiceResult.requestError(httpServletResponse,ServiceCode.NOTLOGGEDIN, HttpServletResponse.SC_UNAUTHORIZED)))
                .accessDeniedHandler((httpServletRequest, httpServletResponse, e) -> ServiceResult.requestSuccess(httpServletResponse,ServiceCode.INSUFFICIENTPERMISSIONS,null))

        .and().logout()
                //自定义登出
                .logoutSuccessHandler((httpServletRequest, httpServletResponse, authentication) -> {
                    httpServletResponse.setContentType(CONTENTTYPE);
                    String token = httpServletRequest.getHeader("Access-Token");
                    PrintWriter writer = httpServletResponse.getWriter();
                    ServiceResult<ServiceCode> result;
                    if (Boolean.TRUE.equals(redisTemplate.delete(token))) {
                        result = ServiceResult.success(ServiceCode.SUCCESS, "退出成功");
                    }else {
                        result = ServiceResult.success(ServiceCode.ERROR, "退出失败");
                    }
                    writer.write(JsonUtil.toJSONStringWithDateFormat(result));
                    writer.flush();
                    writer.close();
                })
                .permitAll()

        .and().authorizeRequests()
                .antMatchers(HttpMethod.POST,LOGINURL).permitAll()
                .antMatchers(LOGOUTURL).permitAll()
                .anyRequest().authenticated()

        .and().addFilterAfter(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class)
        //关闭session
        .sessionManagement().disable()
        //开启跨域访问
        .cors()
        //开启模拟请求，比如API POST测试工具的测试，不开启时，API POST为报403错误
        .and().csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth){
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setHideUserNotFoundExceptions(false);
        authenticationProvider.setUserDetailsService(loginService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8082","http://localhost:8000"));
        configuration.setAllowedMethods(Arrays.asList("POST", "GET", "OPTIONS", "DELETE", "PUT", "HEAD"));
        /*configuration.setAllowedHeaders(Arrays.asList("Content-Type", "X-Token"));*/
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3000L);
        configuration.applyPermitDefaultValues();
        source.registerCorsConfiguration("/**",configuration);
        return source;
    }

}
