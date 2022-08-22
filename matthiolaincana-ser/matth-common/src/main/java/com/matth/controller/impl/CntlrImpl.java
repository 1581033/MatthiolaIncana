package com.matth.controller.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.matth.controller.IController;

import java.util.List;

/**
 * @author Matthiola incana
 * @create 2022/7/28 12:00
 */
public class CntlrImpl<S extends IService<T>, T> implements IController<T> {

    @Override
    public T get(String id) throws Exception {
        return null;
    }

    @Override
    public T saveOrUpdate(T t) throws Exception {
        return null;
    }

    @Override
    public int delete(String id) throws Exception {
        return 0;
    }

    @Override
    public int delete(List<String> id) throws Exception {
        return 0;
    }

}
