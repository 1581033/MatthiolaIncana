package com.zhao.www.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhao.www.antui.AntTabelColumn;
import com.zhao.www.base.entity.result.ServiceResult;
import com.zhao.www.entity.model.SysRole;
import com.zhao.www.entity.model.SysRoleMenu;
import com.zhao.www.entity.param.SysRoleParam;
import com.zhao.www.mapper.SysMenuMapper;
import com.zhao.www.mapper.SysRoleMapper;
import com.zhao.www.mapper.SysRoleMenuMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.UUID;

/**
 * @author Matthiola incana
 * @create 2021/12/15 15:13
 */
@Service
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysRoleService extends ServiceImpl<SysRoleMapper,SysRole> {

    private final SysMenuMapper sysMenuMapper;
    private final SysRoleMenuMapper sysRoleMenuMapper;

    public ServiceResult<?> inquireSysRoleHeadList(SysRoleParam param) {
        return ServiceResult.success(AntTabelColumn.getTableLabel(SysRole.class));
    }

    public ServiceResult<?> inquireSysRoleList(SysRoleParam param) {
        LambdaQueryWrapper<SysRole> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasLength(param.getName())){
            queryWrapper.like(SysRole::getName,param.getName());
        }
        if (StringUtils.hasLength(param.getStatus())){
            queryWrapper.eq(SysRole::getStatus,param.getStatus());
        }
        if (!CollectionUtils.isEmpty(param.getDateTime()) && param.getDateTime().size() > 1){
            queryWrapper.between(SysRole::getCreateTime,param.getDateTime().get(0),param.getDateTime().get(1));
        }
        return ServiceResult.success(list(queryWrapper));
    }

    public ServiceResult<?> increaseSysRole(SysRoleParam param) {
        String randomUUID = UUID.randomUUID().toString();
        SysRole sysRole = new SysRole();
        sysRole.setId(randomUUID);
        sysRole.setRoleDescribe(param.getRoleDescribe());
        sysRole.setName(param.getName());
        sysRole.setStatus(param.getStatus());
        save(sysRole);
        if (param.getMenus().length > 0){
            for (String menu : param.getMenus()) {
                SysRoleMenu roleMenu = new SysRoleMenu();
                roleMenu.setRoleId(randomUUID);
                roleMenu.setMenuId(menu);
                sysRoleMenuMapper.insert(roleMenu);
            }
        }
        return ServiceResult.success(1);
    }

    public ServiceResult<?> deleteSysRole(SysRoleParam param) {
        removeById(param.getId());
        LambdaQueryWrapper<SysRoleMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysRoleMenu::getRoleId, param.getId());
        sysRoleMenuMapper.delete(wrapper);
        return ServiceResult.success(1);
    }

    public ServiceResult<?> updataSysRole(SysRoleParam param) {
        SysRole sysRole = new SysRole();
        sysRole.setId(param.getId());
        sysRole.setStatus(param.getStatus());
        sysRole.setName(param.getName());
        sysRole.setRoleDescribe(param.getRoleDescribe());
        updateById(sysRole);
        LambdaQueryWrapper<SysRoleMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysRoleMenu::getRoleId,param.getId());
        sysRoleMenuMapper.delete(wrapper);
        if (param.getMenus().length > 0){
            for (String menu : param.getMenus()) {
                SysRoleMenu roleMenu = new SysRoleMenu();
                roleMenu.setRoleId(param.getId());
                roleMenu.setMenuId(menu);
                sysRoleMenuMapper.insert(roleMenu);
            }
        }
        return ServiceResult.success(1);
    }

    public ServiceResult<?> inquireSysRoleByMenu(SysRoleParam param) {
        LambdaQueryWrapper<SysRoleMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysRoleMenu::getRoleId,param.getId());
        return ServiceResult.success(sysRoleMenuMapper.selectList(wrapper));
    }

}
