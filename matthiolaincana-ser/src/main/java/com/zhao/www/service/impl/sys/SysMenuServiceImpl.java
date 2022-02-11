package com.zhao.www.service.impl.sys;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhao.www.entity.ServiceResult;
import com.zhao.www.entity.component.AntTabelColumn;
import com.zhao.www.entity.dto.sys.MenuDto;
import com.zhao.www.entity.model.sys.SysMenu;
import com.zhao.www.entity.model.sys.SysRole;
import com.zhao.www.entity.param.sys.SysMenuParam;
import com.zhao.www.mapper.sys.SysMenuMapper;
import com.zhao.www.service.sys.SysMenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Matthiola incana
 * @create 2021/11/12 15:13
 */
@Service
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysMenuServiceImpl implements SysMenuService {

    private final SysMenuMapper sysMenuMapper;

    @Override
    public ServiceResult<?> nav(List<SysRole> sysRole) {
        log.info(sysRole);
        return null;
    }

    @Override
    public ServiceResult<?> maun(List<String> list) {
        List<MenuDto> menuDto = sysMenuMapper.selectMenuByRolesId(list);
        return ServiceResult.success(menuDto);
    }

    @Override
    public ServiceResult<?> inquireSysMenuList(SysMenuParam param) {
        QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
        List<SysMenu> list = sysMenuMapper.selectList(wrapper);
        SysMenu sysMenu = new SysMenu();
        listToTree(list,sysMenu,"0");
        return ServiceResult.success(sysMenu.getChildren());
    }

    @Override
    public ServiceResult<?> inquireSysMenuHeadList(SysMenuParam param) {
        return ServiceResult.success(AntTabelColumn.getTableLabel(SysMenu.class));
    }

    @Override
    public ServiceResult<?> increaseSysMenu(SysMenu param) {
        return ServiceResult.success(sysMenuMapper.insert(param));
    }

    @Override
    public ServiceResult<?> deleteSysMenu(SysMenu param) {
        LambdaQueryWrapper<SysMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysMenu::getId,param.getId()).or().eq(SysMenu::getParentId,param.getId());
        return ServiceResult.success(sysMenuMapper.delete(wrapper));
    }

    @Override
    public ServiceResult<?> updataSysMenu(SysMenu param) {
        log.info(param);
        return ServiceResult.success(sysMenuMapper.updateById(param));
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
