package com.zhao.www.service.sys;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhao.www.base.entity.result.ServiceResult;
import com.zhao.www.entity.component.AntTabelColumn;
import com.zhao.www.entity.dto.sys.MenuDto;
import com.zhao.www.entity.model.sys.SysMenu;
import com.zhao.www.entity.model.sys.SysRole;
import com.zhao.www.entity.param.sys.SysMenuParam;
import com.zhao.www.mapper.sys.SysMenuMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Matthiola incana
 * @create 2021/11/12 15:13
 */
@Service
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysMenuService extends ServiceImpl<SysMenuMapper,SysMenu> {

    public ServiceResult<?> nav(List<SysRole> sysRole) {
        log.info(sysRole);
        return null;
    }

    public ServiceResult<?> maun(List<String> list) {
        List<MenuDto> menuDto = baseMapper.selectMenuByRolesId(list);
        return ServiceResult.success(menuDto);
    }

    public ServiceResult<?> inquireSysMenuList(SysMenuParam param) {
        QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
        List<SysMenu> list = list(wrapper);
        SysMenu sysMenu = new SysMenu();
        listToTree(list,sysMenu,"0");
        return ServiceResult.success(sysMenu.getChildren());
    }

    public ServiceResult<?> inquireSysMenuHeadList(SysMenuParam param) {
        return ServiceResult.success(AntTabelColumn.getTableLabel(SysMenu.class));
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
