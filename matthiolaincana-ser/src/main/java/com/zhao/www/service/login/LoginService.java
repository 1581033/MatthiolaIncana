package com.zhao.www.service.login;

import com.zhao.www.entity.ServiceResult;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Matthiola incana
 * @create 2021/11/28 11:12
 */
public interface LoginService extends UserDetailsService {
    ServiceResult<?> userLogout(HttpServletRequest request);
}
