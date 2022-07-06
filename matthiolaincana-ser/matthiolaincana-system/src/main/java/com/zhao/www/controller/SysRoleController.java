package com.zhao.www.controller;

import com.zhao.www.base.controller.impl.ControllerImpl;
import com.zhao.www.base.entity.result.ServiceResult;
import com.zhao.www.entity.model.SysRole;
import com.zhao.www.entity.param.SysRoleParam;
import com.zhao.www.service.SysRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "/sysRole",description = "角色信息")
@Log4j2
@RestController
@RequestMapping("/sysRole")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysRoleController extends ControllerImpl<SysRoleService, SysRole> {

    @Operation(summary = "获取权限表头部信息", description = "获取权限表头部信息")
    @PostMapping("/inquireSysRoleHeadList")
    public ServiceResult<?> inquireSysRoleHeadList(@RequestBody SysRoleParam param){
        return service.inquireSysRoleHeadList(param);
    }

    @Operation(summary = "获取权限表信息", description = "获取权限表信息")
    @PostMapping("/inquireSysRoleList")
    public ServiceResult<?> inquireSysRoleList(@RequestBody SysRoleParam param){
        return service.inquireSysRoleList(param);
    }

    @Operation(summary = "获取角色下菜单", description = "获取角色下菜单")
    @PostMapping("/inquireSysRoleByMenu")
    public ServiceResult<?> inquireSysRoleByMenu(@RequestBody SysRoleParam param){
        return service.inquireSysRoleByMenu(param);
    }

    @Operation(summary = "添加角色", description = "添加角色")
    @PostMapping("/increaseSysRole")
    public ServiceResult<?> increaseSysRole(@RequestBody SysRoleParam param){
        return service.increaseSysRole(param);
    }

    @Operation(summary = "删除角色", description = "删除角色")
    @PostMapping("/deleteSysRole")
    public ServiceResult<?> deleteSysRole(@RequestBody SysRoleParam param){
        return service.deleteSysRole(param);
    }

    @Operation(summary = "修改角色", description = "修改角色")
    @PostMapping("/updataSysRole")
    public ServiceResult<?> updataSysRole(@RequestBody SysRoleParam param){
        return service.updataSysRole(param);
    }

}
