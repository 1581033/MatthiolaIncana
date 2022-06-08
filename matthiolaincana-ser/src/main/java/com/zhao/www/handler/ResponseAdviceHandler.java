package com.zhao.www.handler;

import com.zhao.www.base.entity.result.ServiceResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author Matthiola incana
 * @create 2022/6/7 18:29
 */
@RestControllerAdvice
public class ResponseAdviceHandler implements ResponseBodyAdvice<Object> {

    private final static List<String> TYPES = Arrays.asList(".*ServiceResult.*","void");

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        Type type = Optional.ofNullable(returnType.getMethod()).orElseThrow(NullPointerException::new).getGenericReturnType();
        return TYPES.stream().noneMatch(s -> type.getTypeName().matches(s));
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        return ServiceResult.success(body);
    }
}
