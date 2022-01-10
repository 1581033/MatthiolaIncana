package com.zhao.www.service.sys;

import com.zhao.www.entity.ServiceResult;
import com.zhao.www.entity.model.sys.SysMenu;
import com.zhao.www.entity.model.sys.SysRole;
import com.zhao.www.entity.param.sys.SysMenuParam;

import java.util.List;

/**
 * @author Matthiola incana
 * @create 2021/11/12 15:13
 */
public interface SysMenuService {
    ServiceResult<?> nav(List<SysRole> sysRole);

    ServiceResult<?> maun(List<String> list);

    ServiceResult<?> inquireSysMenuList(SysMenuParam param);

    ServiceResult<?> inquireSysMenuHeadList(SysMenuParam param);

    ServiceResult<?> increaseSysMenu(SysMenu param);

    ServiceResult<?> deleteSysMenu(SysMenu param);

    ServiceResult<?> updataSysMenu(SysMenu param);
}
