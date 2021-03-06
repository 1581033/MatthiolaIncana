package com.matth.filter;

import com.matth.handler.AutFailureHandler;
import com.matth.service.UserService;
import com.matth.utils.jwt.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    private final UserService userService;

    private final StringRedisTemplate redisTemplate;

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
