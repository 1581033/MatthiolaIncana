package com.zhao.www.controller.login;

import com.zhao.www.base.entity.result.ServiceResult;
import com.zhao.www.service.login.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Matthiola incana
 * @create 2021/11/28 11:10
 */
@Api(value = "/login",tags = "登录")
@Log4j2
@RestController
@RequestMapping("/login")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LoginController {

    private final LoginService loginService;

    @ApiOperation(value = "登录", notes = "登录")
    @PostMapping("/userLogin")
    public void userLogin(@ApiParam("返回内容") @RequestParam("username")String username,@RequestParam("password")String password){
        log.info("测试接口一 -------- > 账号："  + username + "密码：" + password);
    }

    @ApiOperation(value = "登出", notes = "登出")
    @PostMapping("/userLogout")
    public ServiceResult<?> userLogout(HttpServletRequest request){
        return loginService.userLogout(request);
    }

}
