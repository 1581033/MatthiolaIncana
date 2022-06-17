package com.zhao.www.aop;

import com.alibaba.fastjson.JSONObject;
import com.zhao.www.entity.model.OperationLog;
import com.zhao.www.mapper.OperationLogMapper;
import com.zhao.www.utils.request.IpUtils;
import eu.bitwalker.useragentutils.UserAgent;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author Matthiola incana
 * @create 2022/6/8 14:39
 */
@Aspect
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OperationLogAspect {

    private final OperationLogMapper logMapper;

    private final HttpServletRequest request;

    @Around(value = "@annotation(api)")
    public Object around(ProceedingJoinPoint joinPoint, ApiOperation api) throws Throwable {
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        OperationLog operationLog = new OperationLog();
        operationLog.setName(api.value());
        operationLog.setIp(IpUtils.getIpAddress(request));
        operationLog.setMethod(request.getMethod());
        operationLog.setUrl(request.getRequestURI());
        operationLog.setStatus(1);
        operationLog.setStartTime(new Date());
        operationLog.setBrowser(userAgent.getBrowser().getName());
        operationLog.setMac(userAgent.getOperatingSystem().getName());
        operationLog.setParams(JSONObject.toJSONString(joinPoint.getArgs()));
        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            operationLog.setStatus(2);
        } finally {
            operationLog.setEndTime(new Date());
            operationLog.setSpendTime(operationLog.getEndTime().getTime() - operationLog.getStartTime().getTime());
            logMapper.insert(operationLog);
        }
        return joinPoint.proceed();
    }

}
