package com.matth.controller;

import com.matth.controller.impl.ControllerImpl;
import com.matth.entity.model.SysDictData;
import com.matth.entity.param.SysDictDataParam;
import com.matth.service.SysDictDataService;
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
 * @create 2022/1/22 10:53
 */
@Tag(name = "/sysDictData",description = "字典类型")
@Log4j2
@RestController
@RequestMapping("/sysDictData")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysDictDataController extends ControllerImpl<SysDictDataService, SysDictData> {

    @Operation(summary = "获取字典类型", description = "获取字典类型")
    @PostMapping("/inquireSysDictDataList")
    public List<SysDictData> inquireSysDictDataList(@RequestBody SysDictDataParam param){
        return service.inquireSysDictDataList(param);
    }

    @Operation(summary = "添加菜单", description = "添加菜单")
    @PostMapping("/increaseSysDictData")
    public boolean increaseSysDictData(@RequestBody SysDictDataParam param){
        return service.increaseSysDictData(param);
    }

    @Operation(summary = "删除菜单", description = "删除菜单")
    @PostMapping("/deleteSysDictData")
    public boolean deleteSysDictData(@RequestBody SysDictDataParam param){
        return service.deleteSysDictData(param);
    }

    @Operation(summary = "修改菜单", description = "修改菜单")
    @PostMapping("/updataSysDictData")
    public boolean updataSysDictData(@RequestBody SysDictDataParam param){
        return service.updataSysDictData(param);
    }

}
