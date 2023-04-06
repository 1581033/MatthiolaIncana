package com.matth.controller;

import com.fhs.core.trans.anno.TransMethodResult;
import com.matth.controller.impl.ControllerImpl;
import com.matth.entity.dto.sys.UserInfoDto;
import com.matth.entity.model.SysDepartment;
import com.matth.entity.model.SysUser;
import com.matth.entity.param.DepartmentParam;
import com.matth.entity.param.SysUserParam;
import com.matth.service.SUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Matthiola incana
 * @create 2023/2/22 10:15
 */
@Tag(name = "/sysUser",description = "用户信息")
@Log4j2
@RestController
@RequestMapping("/sysUser")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SUserControlle extends ControllerImpl<SUserService, SysUser> {

    @Operation(summary = "用户信息", description = "用户信息")
    @GetMapping("/userInfo")
    public UserInfoDto userInfo(){
        return service.userInfo();
    }

    @TransMethodResult
    @Operation(summary = "获取用户列表", description = "获取用户列表")
    @PostMapping("/list")
    public List<SysUser> list(@RequestBody SysUserParam param){
        return service.list(param);
    }

    @TransMethodResult
    @Operation(summary = "获取用户列表分页", description = "获取用户列表分页")
    @PostMapping("/page")
    public Object page(@RequestBody SysUserParam param){
        return service.page(param);
    }

}
