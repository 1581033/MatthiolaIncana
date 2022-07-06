package com.zhao.www.handler;

import com.zhao.www.base.entity.code.ServiceCode;
import com.zhao.www.base.entity.result.ServiceResult;
import com.zhao.www.utils.jwt.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Matthiola incana
 * @create 2022/6/13 10:58
 */
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AutoutSuccessHandler implements LogoutSuccessHandler {

    private final RedisTemplate<String,Object> redisTemplate;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String token = response.getHeader(JwtTokenUtil.TOKENNAME);
        if (Boolean.TRUE.equals(redisTemplate.delete(token))) {
            ServiceResult.requestSuccess(response, ServiceCode.LOGINOUTSUCCESS);
        }else {
            ServiceResult.requestError(response, ServiceCode.ERROR);
        }
    }
}
