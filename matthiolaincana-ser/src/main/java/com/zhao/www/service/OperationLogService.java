package com.zhao.www.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhao.www.entity.model.OperationLog;
import com.zhao.www.mapper.OperationLogMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Matthiola incana
 * @create 2022/6/8 15:24
 */
@Log4j2
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OperationLogService extends ServiceImpl<OperationLogMapper, OperationLog> {
}
