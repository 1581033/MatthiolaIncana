package com.zhao.www.controller;

import com.zhao.www.base.controller.impl.ControllerImpl;
import com.zhao.www.entity.model.SysPage;
import com.zhao.www.entity.param.SysPageParam;
import com.zhao.www.service.SysPageService;
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
 * @create 2022/6/16 17:54
 */
@Tag(name = "/sysPage",description = "页面信息")
@Log4j2
@RestController
@RequestMapping("/sysPage")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysPageController extends ControllerImpl<SysPageService, SysPage> {

    @Operation(summary = "获取权限表头部信息", description = "获取权限表头部信息")
    @PostMapping("/list")
    public List<SysPage> list(@RequestBody SysPageParam param){
        return service.getList(param);
    }

}
