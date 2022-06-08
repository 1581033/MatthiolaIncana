package com.zhao.www.base.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Matthiola incana
 * @create 2022/6/6 14:27
 */
public interface IController<T> {

    T get(@PathVariable String id) throws Exception ;

    T saveOrUpdate(T t) throws Exception;

    int delete(@PathVariable String id) throws Exception;

    int delete(@RequestParam List<String> id) throws Exception;

}
