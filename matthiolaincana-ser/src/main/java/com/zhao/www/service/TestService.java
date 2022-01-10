package com.zhao.www.service;

import com.zhao.www.entity.ServiceResult;
import com.zhao.www.entity.param.TestParam;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Matthiola incana
 * @create 2021/10/13 18:33
 */
public interface TestService{

    ServiceResult<?> test(String param);

    ServiceResult<?> userLogin(TestParam param);

    ServiceResult<?> meun();

    ServiceResult<?> info();
}
