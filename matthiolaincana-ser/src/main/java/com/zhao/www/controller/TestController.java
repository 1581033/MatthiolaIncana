package com.zhao.www.controller;

import com.zhao.www.annotation.UserAuthenticate;
import com.zhao.www.entity.ServiceResult;
import com.zhao.www.entity.param.TestParam;
import com.zhao.www.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @author Matthiola incana
 * @create 2021/10/13 14:15
 */
@Api(value = "/test",tags = "测试")
@Log4j2
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TestController {

    private final TestService testService;

    @UserAuthenticate
    @ApiOperation(value = "测试接口一", notes = "测试接口一")
    @GetMapping("/testOne")
    public ServiceResult<?> tset(@ApiParam("返回内容") String param){
        log.info("测试接口一 -------- > "  + param);
        return testService.test(param);
    }

    @UserAuthenticate
    @ApiOperation(value = "用户登录接口", notes = "用户登录接口")
    @PostMapping("/userLogin")
    public ServiceResult<?> userLogin(@ApiParam("返回内容") @RequestBody TestParam param){
        log.info("用户登录接口 -------- > "  +param);
        return testService.userLogin(param);
    }

    @UserAuthenticate
    @ApiOperation(value = "用户登录接口", notes = "用户登录接口")
    @GetMapping("/info")
    public ServiceResult<?> info(){
        return testService.info();
    }

    @UserAuthenticate
    @ApiOperation(value = "用户登录接口", notes = "用户登录接口")
    @PostMapping("/meun")
    public ServiceResult<?> meun(){
        return testService.meun();
    }

}
