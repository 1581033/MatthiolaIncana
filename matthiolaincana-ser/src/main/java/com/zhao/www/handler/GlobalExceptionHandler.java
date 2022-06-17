package com.zhao.www.handler;

import com.zhao.www.base.entity.code.ServiceCode;
import com.zhao.www.base.entity.result.ServiceResult;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Objects;

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
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ServiceResult<?> exceptionHandler(Exception e) {
        return ServiceResult.exception(ServiceCode.EXCEPTION,e.getMessage());
    }

    /**
     * 拦截RedisConnectionFailureException类的异常
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RedisConnectionFailureException.class)
    public ServiceResult<?> redisConnectionFailureException(Exception e) {
        return ServiceResult.exception(ServiceCode.REDISCONNECTIONFAILURE,e.getMessage());
    }

    /**
     * 拦截nullPointerException类的异常
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NullPointerException.class)
    public ServiceResult<?> nullPointerException(Exception e) {
        return ServiceResult.exception(ServiceCode.NUllPOINTEREXCEPTION,e.getMessage());
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ServiceResult<?> httpMessageNotReadableException(Exception e) {
        return ServiceResult.exception(ServiceCode.HTTPMESSAGENOTREADABLEEXCEPTION,e.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ServiceResult<?> methodArgumentNotValidException(MethodArgumentNotValidException e){
        return ServiceResult.exception(ServiceCode.METHODARGUMENTNOTVALIDEXCEPTION, Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UsernameNotFoundException.class)
    public ServiceResult<?> usernameNotFoundException(Exception e){
        return ServiceResult.exception(ServiceCode.EXCEPTION, e.getMessage());
    }

}
