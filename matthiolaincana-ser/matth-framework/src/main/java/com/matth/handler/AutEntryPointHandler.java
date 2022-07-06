package com.matth.handler;

import com.matth.entity.code.ServiceCode;
import com.matth.entity.result.ServiceResult;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Matthiola incana
 * @create 2022/6/13 11:04
 */
public class AutEntryPointHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        ServiceResult.requestError(response, ServiceCode.NOTLOGGEDIN);
    }
}
