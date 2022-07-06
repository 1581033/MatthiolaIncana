package com.zhao.www.config;

import com.zhao.www.handler.AutFailureHandler;
import com.zhao.www.service.SysUserService;
import com.zhao.www.utils.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * @author Matthiola incana
 * @create 2021/11/28 14:56
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private SysUserService userService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       try{
           String token = request.getHeader(JwtTokenUtil.TOKENNAME);
           if (StringUtils.hasLength(token)){
               Object key = Optional.ofNullable(redisTemplate.opsForValue().get(token)).orElseThrow(() -> new CredentialsExpiredException(null));
               SecurityContextHolder.getContext().setAuthentication(userService.loadUserByToken(token, key.toString()));
           }
           filterChain.doFilter(request, response);
       }catch (AccountStatusException e){
            new AutFailureHandler(request,response,e);
       }
    }

}
