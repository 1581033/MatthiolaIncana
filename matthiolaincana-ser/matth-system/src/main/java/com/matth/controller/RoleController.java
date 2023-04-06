package com.matth.controller;

import com.matth.controller.impl.ControllerImpl;
import com.matth.entity.model.SysRole;
import com.matth.service.RoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Matthiola incana
 * @create 2021/12/15 15:12
 */
@Tag(name = "/role",description = "角色信息")
@Log4j2
@RestController
@RequestMapping("/role")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RoleController extends ControllerImpl<RoleService, SysRole> {

}
