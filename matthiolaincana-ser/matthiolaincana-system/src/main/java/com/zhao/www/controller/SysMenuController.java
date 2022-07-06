package com.zhao.www.controller;

import com.zhao.www.annotation.UserAuthenticate;
import com.zhao.www.base.controller.impl.ControllerImpl;
import com.zhao.www.base.entity.result.ServiceResult;
import com.zhao.www.entity.model.SysMenu;
import com.zhao.www.entity.param.SysMenuParam;
import com.zhao.www.service.SysMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Matthiola incana
 * @create 2021/11/12 15:11
 */
@Tag(name = "/sysMenu",description = "菜单信息")
@Log4j2
@RestController
@RequestMapping("/sysMenu")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysMenuController extends ControllerImpl<SysMenuService,SysMenu> {

    @UserAuthenticate
    @Operation(summary = "获取菜单", description = "获取菜单")
    @PostMapping("/maun")
    public ServiceResult<?> maun(@RequestBody List<String> list){
        return service.maun(list);
    }

    @Operation(summary = "获取菜单信息", description = "获取菜单信息")
    @PostMapping("/inquireSysMenuList")
    public ServiceResult<?> inquireSysMenuList(@RequestBody SysMenuParam param){
        return service.inquireSysMenuList(param);
    }

    @Operation(summary = "获取菜单表头部信息", description = "获取菜单表头部信息")
    @PostMapping("/inquireSysMenuHeadList")
    public ServiceResult<?> inquireSysMenuHeadList(@RequestBody SysMenuParam param){
        return service.inquireSysMenuHeadList(param);
    }

    @Operation(summary = "添加菜单", description = "添加菜单")
    @PostMapping("/increaseSysMenu")
    public ServiceResult<?> increaseSysMenu(@RequestBody SysMenu param){
        return service.increaseSysMenu(param);
    }

    @Operation(summary = "删除菜单", description = "删除菜单")
    @PostMapping("/deleteSysMenu")
    public ServiceResult<?> deleteSysMenu(@RequestBody SysMenu param){
        return service.deleteSysMenu(param);
    }

    @Operation(summary = "修改菜单", description = "修改菜单")
    @PostMapping("/updataSysMenu")
    public ServiceResult<?> updataSysMenu(@RequestBody SysMenu param){
        return service.updataSysMenu(param);
    }

}
