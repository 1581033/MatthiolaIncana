package com.zhao.www.controller.sys;

import com.zhao.www.annotation.UserAuthenticate;
import com.zhao.www.entity.ServiceResult;
import com.zhao.www.entity.model.sys.SysUser;
import com.zhao.www.entity.param.sys.SysRoleParam;
import com.zhao.www.entity.param.sys.SysUserParam;
import com.zhao.www.service.sys.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Matthiola incana
 * @create 2021/11/12 10:39
 */
@Api(value = "/sysUser",tags = "用户信息")
@Log4j2
@RestController
@RequestMapping("/sysUser")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysUserController {

    private final SysUserService sysUserService;

    @UserAuthenticate
    @ApiOperation(value = "用户信息", notes = "用户信息")
    @GetMapping("/userInfo")
    public ServiceResult<?> userInfo(HttpServletRequest request){
        return sysUserService.userInfo(request);
    }

    @Secured({"ROLE_ADMINS","ROLE_mannger"})
    @ApiOperation(value = "查询用户信息", notes = "查询用户信息")
    @PostMapping("/queryUserInformation")
    public ServiceResult<?> queryUserInformation(@RequestBody SysUserParam param){
        return sysUserService.queryUserInformation(param);
    }

    @ApiOperation(value = "获取用户信息", notes = "获取用户信息")
    @PostMapping("/inquireSysUserList")
    public ServiceResult<?> inquireSysUserList(@RequestBody SysUserParam param){
        return sysUserService.inquireSysUserList(param);
    }

    @ApiOperation(value = "获取用户表头部信息", notes = "获取用户表头部信息")
    @PostMapping("/inquireSysUserHeadList")
    public ServiceResult<?> inquireSysUserHeadList(@RequestBody SysUserParam param){
        return sysUserService.inquireSysUserHeadList(param);
    }

    @ApiOperation(value = "添加角色", notes = "添加角色")
    @PostMapping("/increaseSysUser")
    public ServiceResult<?> increaseSysUser(@RequestBody SysUserParam param){
        return sysUserService.increaseSysUser(param);
    }

    @ApiOperation(value = "删除角色", notes = "删除角色")
    @PostMapping("/deleteSysUser")
    public ServiceResult<?> deleteSysUser(@RequestBody SysUserParam param){
        return sysUserService.deleteSysUser(param);
    }

    @ApiOperation(value = "修改角色", notes = "修改角色")
    @PostMapping("/updataSysUser")
    public ServiceResult<?> updataSysUser(@RequestBody SysUserParam param){
        return sysUserService.updataSysUser(param);
    }

}
