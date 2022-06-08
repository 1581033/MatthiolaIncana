package com.zhao.www.config;

import com.zhao.www.base.entity.code.ServiceCode;
import com.zhao.www.base.entity.code.ServiceExceptionEnum;
import com.zhao.www.base.entity.result.ServiceResult;
import com.zhao.www.service.login.LoginService;
import com.zhao.www.utils.jwt.JwtTokenUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Matthiola incana
 * @create 2021/11/28 14:56
 */
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private final LoginService loginService;
    private final RedisTemplate<String,Object> redisTemplate;

    public JwtAuthenticationTokenFilter(LoginService loginService,RedisTemplate<String,Object> redisTemplate ) {
        this.loginService = loginService;
        this.redisTemplate = redisTemplate;
    }

    private final List<String> FILTERURL = new ArrayList<>();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        FILTERURL.add("/login/userLogout");
        if (!FILTERURL.contains(request.getServletPath())){
            String token = request.getHeader(JwtTokenUtil.TOKENNAME);
            if (StringUtils.hasLength(token)){
                if (Boolean.TRUE.equals(redisTemplate.hasKey(token))) {
                    String o = Objects.requireNonNull(redisTemplate.opsForValue().get(token)).toString();
                    UserDetails userDetails = loginService.loadUserByUsername(JwtTokenUtil.getTokenUserName(token, o));
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }else {
                    ServiceResult.requestError(response, ServiceCode.TOKENFAILEDVALIDATION, 1000);
                    return;
                }
            }else {
                ServiceResult.requestError(response, ServiceCode.NOTLOGGEDIN, 1000);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

}
