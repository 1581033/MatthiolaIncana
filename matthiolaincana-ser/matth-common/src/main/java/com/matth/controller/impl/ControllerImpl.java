package com.matth.controller.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.matth.controller.IController;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author Matthiola incana
 * @create 2022/6/6 14:28
 */
public abstract class ControllerImpl<S extends IService<T>, T> implements IController<T> {

    @Autowired
    protected S service;

    /*@ApiOperation("获取数据集合")
    @PostMapping({"/list"})
    @Override
    public List<T> get(T t) throws Exception {
        return this.service.list();
    }*/

    @Operation(summary = "根据主键获取业务信息")
    @GetMapping({"/{id}"})
    @Override
    public T get(String id) throws Exception {
        return this.service.getById(id);
    }

    @Operation(summary = "保存/更新业务信息")
    @PostMapping
    @Override
    public T saveOrUpdate(T t) throws Exception {
        this.service.saveOrUpdate(t);
        return t;
    }

    @Operation(summary = "根据主键删除业务信息")
    @DeleteMapping({"/{id}"})
    @Override
    public int delete(String id) throws Exception {
        this.service.removeById(id);
        return 1;
    }

    @Operation(summary = "根据主键批量删除业务信息")
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
