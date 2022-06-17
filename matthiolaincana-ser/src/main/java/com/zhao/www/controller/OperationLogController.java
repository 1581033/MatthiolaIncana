package com.zhao.www.controller;

import com.zhao.www.base.controller.impl.ControllerImpl;
import com.zhao.www.entity.model.OperationLog;
import com.zhao.www.service.OperationLogService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Matthiola incana
 * @create 2022/6/8 15:23
 */
@Api(value = "/operationLog",tags = "日志管理")
@Log4j2
@RestController
@RequestMapping("/operationLog")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OperationLogController extends ControllerImpl<OperationLogService, OperationLog> {
}
