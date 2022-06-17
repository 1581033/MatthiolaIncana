package com.zhao.www.aop;

import com.zhao.www.annotation.UserAuthenticate;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author Matthiola incana
 * @create 2021/10/14 15:50
 */
@Log4j2
@Aspect
@Component
public class UserTokenAspect {

    @Before(value = "@annotation(userAuthenticate)")
    public void verifyToken(JoinPoint joinPoint, UserAuthenticate userAuthenticate) throws Exception{
        /*
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        assert requestAttributes.getResponse() != null;
        HttpServletRequest request = requestAttributes.getRequest();
        HttpServletResponse response = requestAttributes.getResponse();
        String header = request.getHeader("Access-Token");
        if (!JwtTokenUtil.verifyToken(header)) {
            ServiceResult.requestError(response,ServiceCode.ERROR);
        }
        */
    }

}
