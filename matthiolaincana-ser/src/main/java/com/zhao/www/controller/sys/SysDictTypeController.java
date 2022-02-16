package com.zhao.www.controller.sys;

import com.zhao.www.entity.ServiceResult;
import com.zhao.www.entity.model.sys.SysDictType;
import com.zhao.www.entity.model.sys.SysMenu;
import com.zhao.www.entity.param.sys.SysDictTypeParam;
import com.zhao.www.entity.param.sys.SysMenuParam;
import com.zhao.www.service.sys.SysDictTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Matthiola incana
 * @create 2022/1/22 10:54
 */
@Api(value = "/sysDictType",tags = "字典类型")
@Log4j2
@RestController
@RequestMapping("/sysDictType")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysDictTypeController {

    private final SysDictTypeService sysDictTypeService;

    @ApiOperation(value = "获取字典类型", notes = "获取字典类型")
    @PostMapping("/inquireSysDictTypeList")
    public ServiceResult<?> inquireSysDictTypeList(@RequestBody SysDictTypeParam param){
        return sysDictTypeService.inquireSysDictTypeList(param);
    }

    @ApiOperation(value = "获取菜单表头部信息", notes = "获取菜单表头部信息")
    @PostMapping("/inquireSysDictTypeHeadList")
    public ServiceResult<?> inquireSysDictTypeHeadList(@RequestBody SysDictTypeParam param){
        return sysDictTypeService.inquireSysDictTypeHeadList(param);
    }

    @ApiOperation(value = "添加菜单", notes = "添加菜单")
    @PostMapping("/increaseSysDictType")
    public ServiceResult<?> increaseSysDictType(@RequestBody SysDictTypeParam param){
        return sysDictTypeService.increaseSysDictType(param);
    }

    @ApiOperation(value = "删除菜单", notes = "删除菜单")
    @PostMapping("/deleteSysDictType")
    public ServiceResult<?> deleteSysDictType(@RequestBody SysDictTypeParam param){
        return sysDictTypeService.deleteSysDictType(param);
    }

    @ApiOperation(value = "修改菜单", notes = "修改菜单")
    @PostMapping("/updataSysDictType")
    public ServiceResult<?> updataSysDictType(@RequestBody SysDictTypeParam param){
        return sysDictTypeService.updataSysDictType(param);
    }

    @ApiOperation(value = "上传菜单", notes = "上传菜单")
    @PostMapping("/uploadSysDictType")
    public ServiceResult<?> uploadSysDictType(@RequestBody SysDictTypeParam param){
        return sysDictTypeService.uploadSysDictType(param);
    }

    @ApiOperation(value = "下载菜单", notes = "下载菜单")
    @PostMapping("/downloadSysDictType")
    public void downloadSysDictType(@RequestBody SysDictTypeParam param, HttpServletResponse response){
        sysDictTypeService.downloadSysDictType(param,response);
    }

    @ApiOperation(value = "获取字典数据", notes = "获取字典数据")
    @GetMapping("/getDictData")
    public ServiceResult<?> getDictData(SysDictTypeParam param){
        return sysDictTypeService.getDictData(param);
    }

}
