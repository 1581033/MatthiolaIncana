package com.zhao.www.controller.sys;

import com.zhao.www.entity.ServiceResult;
import com.zhao.www.entity.model.sys.SysRole;
import com.zhao.www.entity.param.sys.SysRoleParam;
import com.zhao.www.service.sys.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Matthiola incana
 * @create 2021/12/15 15:12
 */
@Api(value = "/sysRole",tags = "菜单信息")
@Log4j2
@RestController
@RequestMapping("/sysRole")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysRoleController {

    private final SysRoleService sysRoleService;

    @ApiOperation(value = "获取权限表头部信息", notes = "获取权限表头部信息")
    @PostMapping("/inquireSysRoleHeadList")
    public ServiceResult<?> inquireSysRoleHeadList(@RequestBody SysRoleParam param){
        return sysRoleService.inquireSysRoleHeadList(param);
    }

    @ApiOperation(value = "获取权限表信息", notes = "获取权限表信息")
    @PostMapping("/inquireSysRoleList")
    public ServiceResult<?> inquireSysRoleList(@RequestBody SysRoleParam param){
        return sysRoleService.inquireSysRoleList(param);
    }

    @ApiOperation(value = "获取角色下菜单", notes = "获取角色下菜单")
    @PostMapping("/inquireSysRoleByMenu")
    public ServiceResult<?> inquireSysRoleByMenu(@RequestBody SysRoleParam param){
        return sysRoleService.inquireSysRoleByMenu(param);
    }

    @ApiOperation(value = "添加角色", notes = "添加角色")
    @PostMapping("/increaseSysRole")
    public ServiceResult<?> increaseSysRole(@RequestBody SysRoleParam param){
        return sysRoleService.increaseSysRole(param);
    }

    @ApiOperation(value = "删除角色", notes = "删除角色")
    @PostMapping("/deleteSysRole")
    public ServiceResult<?> deleteSysRole(@RequestBody SysRoleParam param){
        return sysRoleService.deleteSysRole(param);
    }

    @ApiOperation(value = "修改角色", notes = "修改角色")
    @PostMapping("/updataSysRole")
    public ServiceResult<?> updataSysRole(@RequestBody SysRoleParam param){
        return sysRoleService.updataSysRole(param);
    }

}
