package com.matth.controller;

import com.matth.entity.param.SchedulerQuartzParam;
import com.matth.service.SchedulerQuartzService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Matthiola incana
 * @create 2022/6/17 18:16
 */
@Tag(name = "/quartz",description = "定时模块")
@Log4j2
@RestController
@RequestMapping("/quartz")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SchedulerQuartzController {

    private final SchedulerQuartzService quartzService;

    @Operation(summary = "获取定时数据", description = "获取定时数据")
    @PostMapping("/list")
    public void list(@RequestBody SchedulerQuartzParam param) throws Exception{
        quartzService.addTask(param);
    }

}
