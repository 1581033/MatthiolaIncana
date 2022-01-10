package com.zhao.www.config;

import com.zhao.www.entity.ServiceCode;
import com.zhao.www.entity.ServiceResult;
import io.lettuce.core.RedisConnectionException;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Matthiola incana
 * @create 2021/10/14 15:16
 */
@Log4j2
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 拦截所有Exception类的异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ServiceResult<?> exceptionHandler(Exception e) {
        e.printStackTrace();
        return ServiceResult.exception(ServiceCode.EXCEPTION,e.getMessage());
    }

    /**
     * 拦截AccessDeniedException类的异常
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public ServiceResult<?> accessDeniedExceptionHandler(Exception e) {
        return ServiceResult.exception(ServiceCode.INSUFFICIENTPERMISSIONS,e.getMessage());
    }

    /**
     * 拦截RedisConnectionFailureException类的异常
     */
    @ExceptionHandler(RedisConnectionFailureException.class)
    @ResponseBody
    public ServiceResult<?> RedisConnectionFailureException(Exception e) {
        return ServiceResult.exception(ServiceCode.REDISCONNECTIONFAILURE,e.getMessage());
    }



}
