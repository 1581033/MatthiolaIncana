package com.matth.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.matth.entity.model.SysPagination;
import com.matth.entity.param.SysPaginationParam;
import com.matth.service.PaginationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Matthiola incana
 * @create 2022/7/14 16:12
 */
@Tag(name = "/pagination",description = "页面样式查询与展示")
@Log4j2
@RestController
@RequestMapping("/pagination/{rn}")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PaginationController {

    private final PaginationService service;

    @Operation(summary = "获取表格头部", description = "获取表格头部")
    @GetMapping("/headerList")
    public List<SysPagination> headerList(@PathVariable(name = "rn") String routeName){
        return service.headerList(routeName);
    }

    @Operation(summary = "获取查询表单", description = "获取查询表单")
    @GetMapping("/formList")
    public List<SysPagination> formList(@PathVariable(name = "rn") String routeName){
        return service.formList(routeName);
    }

    @Operation(summary = "根据表头获取特定数据", description = "根据表头获取特定数据")
    @PostMapping("/page")
    public IPage<Object> page(@PathVariable(name = "rn") String routeName,@RequestBody SysPaginationParam param){
        return service.page(param);
    }

    @Operation(summary = "根据表头获取特定数据", description = "根据表头获取特定数据")
    @PostMapping("/list")
    public List<Object> list(@PathVariable(name = "rn") String routeName,@RequestBody SysPaginationParam param){
        return service.list(param);
    }

    @Operation(summary = "根据表头获取特定数据", description = "根据表头获取特定数据")
    @PostMapping("/upload")
    public void upload(MultipartFile file,@PathVariable(name = "rn") String routeName, @RequestBody SysPaginationParam param) throws Exception {
        service.upload(file, param);
    }

    @Operation(summary = "根据表头获取特定数据", description = "根据表头获取特定数据")
    @PostMapping("/download")
    public void download(@PathVariable(name = "rn") String routeName, @RequestBody SysPaginationParam param){
        service.download(param);
    }

}
