package com.zhao.www.controller.sys;

import com.zhao.www.base.controller.impl.ControllerImpl;
import com.zhao.www.entity.component.AntTabelColumn;
import com.zhao.www.entity.model.sys.SysDictData;
import com.zhao.www.entity.param.sys.SysDictDataParam;
import com.zhao.www.service.sys.SysDictDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "/sysDictData",tags = "字典类型")
@Log4j2
@RestController
@RequestMapping("/sysDictData")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysDictDataController extends ControllerImpl<SysDictDataService, SysDictData> {

    @ApiOperation(value = "获取字典类型", notes = "获取字典类型")
    @PostMapping("/inquireSysDictDataList")
    public List<SysDictData> inquireSysDictDataList(@RequestBody SysDictDataParam param){
        return service.inquireSysDictDataList(param);
    }

    @ApiOperation(value = "获取菜单表头部信息", notes = "获取菜单表头部信息")
    @PostMapping("/inquireSysDictDataHeadList")
    public List<AntTabelColumn> inquireSysDictDataHeadList(@RequestBody SysDictDataParam param){
        return service.inquireSysDictDataHeadList(param);
    }

    @ApiOperation(value = "添加菜单", notes = "添加菜单")
    @PostMapping("/increaseSysDictData")
    public boolean increaseSysDictData(@RequestBody SysDictDataParam param){
        return service.increaseSysDictData(param);
    }

    @ApiOperation(value = "删除菜单", notes = "删除菜单")
    @PostMapping("/deleteSysDictData")
    public boolean deleteSysDictData(@RequestBody SysDictDataParam param){
        return service.deleteSysDictData(param);
    }

    @ApiOperation(value = "修改菜单", notes = "修改菜单")
    @PostMapping("/updataSysDictData")
    public boolean updataSysDictData(@RequestBody SysDictDataParam param){
        return service.updataSysDictData(param);
    }

}
