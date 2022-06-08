package com.zhao.www.base.controller.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhao.www.base.controller.IController;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Matthiola incana
 * @create 2022/6/6 14:28
 */
public abstract class ControllerImpl<S extends IService<T>, T> implements IController<T> {

    @Autowired
    protected S service;

    @ApiOperation("根据主键获取业务信息")
    @GetMapping({"/{id}"})
    @Override
    public T get(String id) throws Exception {
        return this.service.getById(id);
    }

    @ApiOperation("保存/更新业务信息")
    @PostMapping
    @Override
    public T saveOrUpdate(T t) throws Exception {
        this.service.saveOrUpdate(t);
        return t;
    }

    @ApiOperation("根据主键删除业务信息")
    @DeleteMapping({"/{id}"})
    @Override
    public int delete(String id) throws Exception {
        this.service.removeById(id);
        return 1;
    }

    @ApiOperation("根据主键批量删除业务信息")
    @DeleteMapping
    @Override
    public int delete(List<String> id) throws Exception {
        if (CollectionUtils.isEmpty(id)) {
            return 0;
        } else {
            this.service.removeByIds(id);
            return id.size();
        }
    }
}
