package com.zhao.www.controller;

import com.zhao.www.annotation.UserAuthenticate;
import com.zhao.www.base.controller.impl.ControllerImpl;
import com.zhao.www.base.entity.result.ServiceResult;
import com.zhao.www.entity.dto.sys.UserInfoDto;
import com.zhao.www.entity.model.SysUser;
import com.zhao.www.entity.param.SysUserParam;
import com.zhao.www.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

/**
 * @author Matthiola incana
 * @create 2021/11/12 10:39
 */
@Tag(name = "/sysUser",description = "用户信息")
@Log4j2
@RestController
@RequestMapping("/sysUser")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysUserController extends ControllerImpl<SysUserService, SysUser> {

    @UserAuthenticate
    @Operation(summary = "用户信息", description = "用户信息")
    @GetMapping("/userInfo")
    public UserInfoDto userInfo(){
        return service.userInfo();
    }

    @Secured({"ROLE_ADMINS","ROLE_mannger"})
    @Operation(summary = "查询用户信息", description = "查询用户信息")
    @PostMapping("/queryUserInformation")
    public ServiceResult<?> queryUserInformation(@RequestBody SysUserParam param){
        return service.queryUserInformation(param);
    }

    @Operation(summary = "获取用户信息", description = "获取用户信息")
    @PostMapping("/inquireSysUserList")
    public ServiceResult<?> inquireSysUserList(@RequestBody SysUserParam param){
        return service.inquireSysUserList(param);
    }

    @Operation(summary = "获取用户表头部信息", description = "获取用户表头部信息")
    @PostMapping("/inquireSysUserHeadList")
    public ServiceResult<?> inquireSysUserHeadList(@RequestBody SysUserParam param){
        return service.inquireSysUserHeadList(param);
    }

    @Operation(summary = "添加角色", description = "添加角色")
    @PostMapping("/increaseSysUser")
    public ServiceResult<?> increaseSysUser(@RequestBody SysUserParam param){
        return service.increaseSysUser(param);
    }

    @Operation(summary = "删除角色", description = "删除角色")
    @PostMapping("/deleteSysUser")
    public ServiceResult<?> deleteSysUser(@RequestBody SysUserParam param){
        return service.deleteSysUser(param);
    }

    @Operation(summary = "修改角色", description = "修改角色")
    @PostMapping("/updataSysUser")
    public ServiceResult<?> updataSysUser(@RequestBody SysUserParam param){
        return service.updataSysUser(param);
    }

}
