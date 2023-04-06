package com.matth.controller;

import com.matth.controller.impl.ControllerImpl;
import com.matth.entity.model.SysMenu;
import com.matth.entity.param.SysMenuParam;
import com.matth.entity.result.ServiceResult;
import com.matth.service.SysMenuService;
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
public class SysMenuController extends ControllerImpl<SysMenuService, SysMenu> {

    @Operation(summary = "获取菜单", description = "获取菜单")
    @PostMapping("/maun")
    public ServiceResult<?> maun(){
        return service.maun();
    }

    @Operation(summary = "获取菜单信息", description = "获取菜单信息")
    @PostMapping("/inquireSysMenuList")
    public ServiceResult<?> inquireSysMenuList(@RequestBody SysMenuParam param){
        return service.inquireSysMenuList(param);
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
