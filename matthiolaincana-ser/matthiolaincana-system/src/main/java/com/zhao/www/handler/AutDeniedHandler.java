package com.zhao.www.handler;

import com.zhao.www.base.entity.code.ServiceCode;
import com.zhao.www.base.entity.result.ServiceResult;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Matthiola incana
 * @create 2022/6/13 11:05
 */
public class AutDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ServiceResult.requestError(response, ServiceCode.INSUFFICIENTPERMISSIONS);
    }
}
