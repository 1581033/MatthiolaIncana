package com.matth.security;

import com.matth.filter.JwtAuthenticationTokenFilter;
import com.matth.handler.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 * @author Matthiola incana
 * @create 2021/10/18 16:59
 */
@Log4j2
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityConfig {

    private final RedisTemplate<String,Object> redisTemplate;

    private final JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    private final String[] MATCHERS = {"/swagger-ui/**", "/css/xx", "/js/xx", "/index.html", "/doc.html", "/webjars/**", "/swagger-resources/**", "/v3/api-docs/**", "/captcha", "/user/register", "/ws/**", "/druid/**","/swagger-ui.html"};

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.formLogin().permitAll()
                .failureHandler(new AutFailureHandler())
                .successHandler(new AutSuccessHandler(redisTemplate))
                .and().exceptionHandling()
                .accessDeniedHandler(new AutDeniedHandler())
                .authenticationEntryPoint(new AutEntryPointHandler())
                .and().logout().logoutSuccessHandler(new AutOutSuccessHandler(redisTemplate))
                .and().authorizeRequests().antMatchers(MATCHERS).permitAll().anyRequest().authenticated()
                .and().addFilterAfter(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().cors()
                .and().csrf().disable();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();
        //configuration.setAllowedOrigins(Arrays.asList("http://localhost:8082","http://localhost:8000"));
        configuration.addAllowedOriginPattern("*");
        configuration.setAllowedMethods(Arrays.asList("POST", "GET", "OPTIONS", "DELETE", "PUT", "HEAD"));
        /*configuration.setAllowedHeaders(Arrays.asList("Content-Type", "X-Token"));*/
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3000L);
        configuration.applyPermitDefaultValues();
        source.registerCorsConfiguration("/**",configuration);
        return source;
    }

}
