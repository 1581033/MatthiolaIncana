package com.matth.handler;

import cn.hutool.core.util.IdUtil;
import com.matth.entity.code.ServiceCode;
import com.matth.entity.result.ServiceResult;
import com.matth.utils.jwt.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author Matthiola incana
 * @create 2022/6/13 10:43
 */
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AutSuccessHandler implements AuthenticationSuccessHandler {

    private final RedisTemplate<String,Object> redisTemplate;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String tokenSecret = IdUtil.fastSimpleUUID().toUpperCase();
        tokenSecret = "qasdfrsx";
        String token = JwtTokenUtil.creatToken(authentication.getName(), tokenSecret);
        //redisTemplate.opsForValue().set(tokenSecret,token,2, TimeUnit.HOURS);
        response.setHeader(JwtTokenUtil.TOKENNAME,token);
        response.setHeader("Access-Control-Expose-Headers",JwtTokenUtil.TOKENNAME);
        ServiceResult.requestSuccess(response, ServiceCode.LOGINSUCCESS);
    }
}
