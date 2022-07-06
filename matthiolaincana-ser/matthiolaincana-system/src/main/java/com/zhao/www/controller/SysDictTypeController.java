package com.zhao.www.controller;

import com.zhao.www.base.controller.impl.ControllerImpl;
import com.zhao.www.base.entity.result.ServiceResult;
import com.zhao.www.entity.model.SysDictType;
import com.zhao.www.entity.param.SysDictTypeParam;
import com.zhao.www.service.SysDictTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Matthiola incana
 * @create 2022/1/22 10:54
 */
@Tag(name = "/sysDictType",description = "字典详情")
@Log4j2
@RestController
@RequestMapping("/sysDictType")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysDictTypeController extends ControllerImpl<SysDictTypeService, SysDictType> {

    @Operation(summary = "获取字典类型", description = "获取字典类型")
    @PostMapping("/inquireSysDictTypeList")
    public ServiceResult<?> inquireSysDictTypeList(@RequestBody SysDictTypeParam param){
        return service.inquireSysDictTypeList(param);
    }

    @Operation(summary = "获取菜单表头部信息", description = "获取菜单表头部信息")
    @PostMapping("/inquireSysDictTypeHeadList")
    public ServiceResult<?> inquireSysDictTypeHeadList(@RequestBody SysDictTypeParam param){
        return service.inquireSysDictTypeHeadList(param);
    }

    @Operation(summary = "添加菜单", description = "添加菜单")
    @PostMapping("/increaseSysDictType")
    public ServiceResult<?> increaseSysDictType(@RequestBody SysDictTypeParam param){
        return service.increaseSysDictType(param);
    }

    @Operation(summary = "删除菜单", description = "删除菜单")
    @PostMapping("/deleteSysDictType")
    public ServiceResult<?> deleteSysDictType(@RequestBody SysDictTypeParam param){
        return service.deleteSysDictType(param);
    }

    @Operation(summary = "修改菜单", description = "修改菜单")
    @PostMapping("/updataSysDictType")
    public ServiceResult<?> updataSysDictType(@RequestBody SysDictTypeParam param){
        return service.updataSysDictType(param);
    }

    @Operation(summary = "上传菜单", description = "上传菜单")
    @PostMapping("/uploadSysDictType")
    public ServiceResult<?> uploadSysDictType(@RequestBody SysDictTypeParam param){
        return service.uploadSysDictType(param);
    }

    @Operation(summary = "下载菜单", description = "下载菜单")
    @PostMapping("/downloadSysDictType")
    public void downloadSysDictType(@RequestBody SysDictTypeParam param, HttpServletResponse response) throws Exception {
        service.downloadSysDictType(param,response);
    }

    @Operation(summary = "获取字典数据", description = "获取字典数据")
    @GetMapping("/getDictData")
    public ServiceResult<?> getDictData(SysDictTypeParam param){
        return service.getDictData(param);
    }

}
