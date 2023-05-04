package com.matth.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.matth.entity.model.Options;
import com.matth.entity.model.SysDepartment;
import com.matth.entity.model.SysMenu;
import com.matth.entity.model.User;
import com.matth.entity.param.DepartmentParam;
import com.matth.handler.SecurityDetailsHolder;
import com.matth.mapper.DepartmentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Matthiola incana
 * @create 2022/7/15 15:19
 */
@Log4j2
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DepartmentService extends ServiceImpl<DepartmentMapper, SysDepartment> {
    public List<SysDepartment> tree(DepartmentParam param) {
        LambdaQueryWrapper<SysDepartment> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasLength(param.getCurrenId())){
            wrapper.ne(SysDepartment::getId, param.getCurrenId());
        }
        List<SysDepartment> list = list(wrapper);
        SysDepartment sysDepartment = new SysDepartment();
        listToTree(list,sysDepartment, "0");
        return sysDepartment.getChildren();
    }

    private void listToTree(List<SysDepartment> list, SysDepartment sysDepartment , String parentId){
        list.forEach(item -> {
            if (item.getParentId().equals(parentId)){
                listToTree(list,item,item.getId());
                if (sysDepartment.getChildren() == null){
                    sysDepartment.setChildren(new ArrayList<>());
                }
                sysDepartment.getChildren().add(item);
            }
        });
    }

    public List<Options> options(DepartmentParam param) {
        LambdaQueryWrapper<SysDepartment> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasLength(param.getCurrenId())){
            wrapper.ne(SysDepartment::getId, param.getCurrenId());
        }
        if (param.getIsRole()){
            User details = SecurityDetailsHolder.details();

        }
        List<Options> list = list(wrapper).stream().map(s -> {
                Options options = new Options();
                options.setLabel(s.getName());
                options.setValue(s.getId());
                options.setParentId(s.getParentId());
                return options;
        }).collect(Collectors.toList());
        Options options = new Options();
        listToTree(list,options, "0");
        return options.getChildren();
    }

    private void listToTree(List<Options> list, Options option , String parentId){
        list.forEach(item -> {
            if (item.getParentId().equals(parentId)){
                listToTree(list,item,item.getValue());
                if (option.getChildren() == null){
                    option.setChildren(new ArrayList<>());
                }
                option.getChildren().add(item);
            }
        });
    }

}
