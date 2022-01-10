package com.zhao.www.service.sys;

import com.zhao.www.entity.ServiceResult;
import com.zhao.www.entity.model.sys.SysRole;
import com.zhao.www.entity.param.sys.SysRoleParam;

/**
 * @author Matthiola incana
 * @create 2021/12/15 15:13
 */
public interface SysRoleService {
    ServiceResult<?> inquireSysRoleHeadList(SysRoleParam param);

    ServiceResult<?> inquireSysRoleList(SysRoleParam param);

    ServiceResult<?> increaseSysRole(SysRoleParam param);

    ServiceResult<?> deleteSysRole(SysRoleParam param);

    ServiceResult<?> updataSysRole(SysRoleParam param);

    ServiceResult<?> inquireSysRoleByMenu(SysRoleParam param);
}
