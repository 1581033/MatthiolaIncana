package com.zhao.www.service.sys;

import com.zhao.www.entity.ServiceResult;
import com.zhao.www.entity.model.sys.SysUser;
import com.zhao.www.entity.param.sys.SysUserParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Matthiola incana
 * @create 2021/11/12 10:41
 */
public interface SysUserService {
    ServiceResult<?> userInfo(HttpServletRequest request);

    ServiceResult<?> queryUserInformation(SysUserParam param);

    ServiceResult<?> inquireSysUserList(SysUserParam param);

    ServiceResult<?> inquireSysUserHeadList(SysUserParam param);

    ServiceResult<?> increaseSysUser(SysUserParam param);

    ServiceResult<?> deleteSysUser(SysUserParam param);

    ServiceResult<?> updataSysUser(SysUserParam param);
}
