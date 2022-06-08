package com.zhao.www.controller.sys;

import com.zhao.www.annotation.UserAuthenticate;
import com.zhao.www.base.controller.impl.ControllerImpl;
import com.zhao.www.base.entity.result.ServiceResult;
import com.zhao.www.entity.model.sys.SysMenu;
import com.zhao.www.entity.param.sys.SysMenuParam;
import com.zhao.www.service.sys.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Matthiola incana
 * @create 2021/11/12 15:11
 */
@Api(value = "/sysMenu",tags = "菜单信息")
@Log4j2
@RestController
@RequestMapping("/sysMenu")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysMenuController extends ControllerImpl<SysMenuService,SysMenu> {

    @UserAuthenticate
    @ApiOperation(value = "获取菜单", notes = "获取菜单")
    @PostMapping("/maun")
    public ServiceResult<?> maun(@RequestBody List<String> list){
        return service.maun(list);
    }

    @ApiOperation(value = "获取菜单信息", notes = "获取菜单信息")
    @PostMapping("/inquireSysMenuList")
    public ServiceResult<?> inquireSysMenuList(@RequestBody SysMenuParam param){
        return service.inquireSysMenuList(param);
    }

    @ApiOperation(value = "获取菜单表头部信息", notes = "获取菜单表头部信息")
    @PostMapping("/inquireSysMenuHeadList")
    public ServiceResult<?> inquireSysMenuHeadList(@RequestBody SysMenuParam param){
        return service.inquireSysMenuHeadList(param);
    }

    @ApiOperation(value = "添加菜单", notes = "添加菜单")
    @PostMapping("/increaseSysMenu")
    public ServiceResult<?> increaseSysMenu(@RequestBody SysMenu param){
        return service.increaseSysMenu(param);
    }

    @ApiOperation(value = "删除菜单", notes = "删除菜单")
    @PostMapping("/deleteSysMenu")
    public ServiceResult<?> deleteSysMenu(@RequestBody SysMenu param){
        return service.deleteSysMenu(param);
    }

    @ApiOperation(value = "修改菜单", notes = "修改菜单")
    @PostMapping("/updataSysMenu")
    public ServiceResult<?> updataSysMenu(@RequestBody SysMenu param){
        return service.updataSysMenu(param);
    }

}
