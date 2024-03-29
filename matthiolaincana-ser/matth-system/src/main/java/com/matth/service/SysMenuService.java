package com.matth.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.matth.entity.dto.sys.MenuDto;
import com.matth.entity.model.SysMenu;
import com.matth.entity.model.SysRole;
import com.matth.entity.param.SysMenuParam;
import com.matth.entity.result.ServiceResult;
import com.matth.handler.SecurityDetailsHolder;
import com.matth.mapper.SysMenuMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Matthiola incana
 * @create 2021/11/12 15:13
 */
@Service
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysMenuService extends ServiceImpl<SysMenuMapper, SysMenu> {

    public ServiceResult<?> nav(List<SysRole> sysRole) {
        log.info(sysRole);
        return null;
    }

    public ServiceResult<?> maun() {
        List<String> roles = SecurityDetailsHolder.roles();
        List<MenuDto> menuDto = baseMapper.selectMenuByRolesId(roles);
        return ServiceResult.success(menuDto);
    }

    public ServiceResult<?> inquireSysMenuList(SysMenuParam param) {
        QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
        List<SysMenu> list = list(wrapper);
        SysMenu sysMenu = new SysMenu();
        listToTree(list,sysMenu,"0");
        return ServiceResult.success(sysMenu.getChildren());
    }

    public ServiceResult<?> increaseSysMenu(SysMenu param) {
        return ServiceResult.success(save(param));
    }

    public ServiceResult<?> deleteSysMenu(SysMenu param) {
        LambdaQueryWrapper<SysMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysMenu::getId,param.getId()).or().eq(SysMenu::getParentId,param.getId());
        return ServiceResult.success(remove(wrapper));
    }

    public ServiceResult<?> updataSysMenu(SysMenu param) {
        log.info(param);
        return ServiceResult.success(updateById(param));
    }

    private void listToTree(List<SysMenu> list,SysMenu sysMenu ,String parentId){
        list.forEach(item -> {
            if (item.getParentId().equals(parentId)){
                listToTree(list,item,item.getId());
                if (sysMenu.getChildren() == null){
                    sysMenu.setChildren(new ArrayList<>());
                }
                sysMenu.getChildren().add(item);
            }
        });
    }
}
