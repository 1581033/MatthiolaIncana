package com.matth.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.matth.entity.model.SysDepartment;
import com.matth.entity.model.SysMenu;
import com.matth.mapper.DepartmentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Matthiola incana
 * @create 2022/7/15 15:19
 */
@Log4j2
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DepartmentService extends ServiceImpl<DepartmentMapper, SysDepartment> {
    public List<SysDepartment> tree() {
        LambdaQueryWrapper<SysDepartment> wrapper = new LambdaQueryWrapper<>();
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
}
