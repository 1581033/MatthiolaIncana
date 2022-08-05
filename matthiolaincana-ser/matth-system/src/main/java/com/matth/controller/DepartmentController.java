package com.matth.controller;

import com.matth.controller.impl.ControllerImpl;
import com.matth.entity.model.SysDepartment;
import com.matth.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Matthiola incana
 * @create 2022/7/15 15:19
 */
@Tag(name = "/department",description = "部门管理")
@Log4j2
@RestController
@RequestMapping("/department")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DepartmentController extends ControllerImpl<DepartmentService, SysDepartment> {

    @Operation(summary = "获取字典类型", description = "获取字典类型")
    @PostMapping("/tree")
    public List<SysDepartment> tree(){
        return service.tree();
    }

}
