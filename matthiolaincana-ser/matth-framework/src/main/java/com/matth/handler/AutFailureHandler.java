package com.matth.handler;

import com.matth.entity.code.ServiceCode;
import com.matth.entity.result.ServiceResult;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Matthiola incana
 * @create 2022/6/13 10:42
 */
public class AutFailureHandler implements AuthenticationFailureHandler {

    public AutFailureHandler(){
    }

    public AutFailureHandler(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        this.onAuthenticationFailure(request,response,exception);
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        ServiceCode loginfailure = ServiceCode.LOGINFAILURE;
        if (exception instanceof DisabledException){
            loginfailure = ServiceCode.LOGINDISABLED;
        }else if (exception instanceof LockedException){
            loginfailure = ServiceCode.LOGINLOCKED;
        }else if (exception instanceof AccountExpiredException){
            loginfailure = ServiceCode.LOGINACCOUNT;
        }else if (exception instanceof CredentialsExpiredException){
            loginfailure = ServiceCode.LOGINCREDENTIALS;
        }
        ServiceResult.requestError(response, loginfailure);
    }

}
