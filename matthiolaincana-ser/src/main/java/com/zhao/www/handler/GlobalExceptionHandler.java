package com.zhao.www.handler;

import com.zhao.www.base.entity.code.ServiceCode;
import com.zhao.www.base.entity.code.ServiceExceptionEnum;
import com.zhao.www.base.entity.result.ServiceResult;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Matthiola incana
 * @create 2021/10/14 15:16
 */
@Log4j2
@RestControllerAdvice
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
    public ServiceResult<?> redisConnectionFailureException(Exception e) {
        return ServiceResult.exception(ServiceCode.REDISCONNECTIONFAILURE,e.getMessage());
    }

    /**
     * 拦截RedisConnectionFailureException类的异常
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public ServiceResult<?> nullPointerException(Exception e) {
        return ServiceResult.exception(ServiceCode.NUllPOINTEREXCEPTION,e.getMessage());
    }



}
