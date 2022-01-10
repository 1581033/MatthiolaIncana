package com.zhao.www.service.impl.sys;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhao.www.entity.ServiceResult;
import com.zhao.www.entity.component.AntTabelColumn;
import com.zhao.www.entity.model.sys.SysMenu;
import com.zhao.www.entity.model.sys.SysRole;
import com.zhao.www.entity.model.sys.SysRoleMenu;
import com.zhao.www.entity.param.sys.SysRoleParam;
import com.zhao.www.mapper.sys.SysMenuMapper;
import com.zhao.www.mapper.sys.SysRoleMapper;
import com.zhao.www.mapper.sys.SysRoleMenuMapper;
import com.zhao.www.service.sys.SysRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

/**
 * @author Matthiola incana
 * @create 2021/12/15 15:13
 */
@Service
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysRoleServiceImpl implements SysRoleService {

    private final SysRoleMapper sysRoleMapper;
    private final SysMenuMapper sysMenuMapper;
    private final SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public ServiceResult<?> inquireSysRoleHeadList(SysRoleParam param) {
        return ServiceResult.success(AntTabelColumn.getTableLabel(SysRole.class));
    }

    @Override
    public ServiceResult<?> inquireSysRoleList(SysRoleParam param) {
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        List<SysRole> list = sysRoleMapper.selectList(wrapper);
        return ServiceResult.success(list);
    }

    @Override
    public ServiceResult<?> increaseSysRole(SysRoleParam param) {
        String format = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        String randomUUID = UUID.randomUUID().toString();
        SysRole sysRole = new SysRole();
        sysRole.setId(randomUUID);
        sysRole.setCreateTime(format);
        sysRole.setRoleDescribe(param.getRoleDescribe());
        sysRole.setName(param.getName());
        sysRole.setStatus(param.getStatus());
        sysRoleMapper.insert(sysRole);
        if (param.getMenus().length > 0){
            LambdaQueryWrapper<SysMenu> wrapper = new LambdaQueryWrapper<>();
            wrapper.in(SysMenu::getId, (Object[]) param.getMenus()).or().in(SysMenu::getParentId, (Object[]) param.getMenus());
            List<SysMenu> sysMenus = sysMenuMapper.selectList(wrapper);
            sysMenus.forEach(s -> {
                SysRoleMenu roleMenu = new SysRoleMenu();
                roleMenu.setRoleId(randomUUID);
                roleMenu.setMenuId(s.getId());
                sysRoleMenuMapper.insert(roleMenu);
            });
        }
        return ServiceResult.success(1);
    }

    @Override
    public ServiceResult<?> deleteSysRole(SysRoleParam param) {
        sysRoleMapper.deleteById(param.getId());
        LambdaQueryWrapper<SysRoleMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysRoleMenu::getRoleId, param.getId());
        sysRoleMenuMapper.delete(wrapper);
        return ServiceResult.success(1);
    }

    @Override
    public ServiceResult<?> updataSysRole(SysRoleParam param) {
        SysRole sysRole = new SysRole();
        sysRole.setId(param.getId());
        sysRole.setStatus(param.getStatus());
        sysRole.setName(param.getName());
        sysRole.setRoleDescribe(param.getRoleDescribe());
        sysRoleMapper.updateById(sysRole);
        LambdaQueryWrapper<SysRoleMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysRoleMenu::getRoleId,param.getId());
        sysRoleMenuMapper.delete(wrapper);
        if (param.getMenus().length > 0){
            LambdaQueryWrapper<SysMenu> wrapper1 = new LambdaQueryWrapper<>();
            wrapper1.in(SysMenu::getId, (Object[]) param.getMenus())
                    .or()
                    .in(SysMenu::getParentId, (Object[]) param.getMenus());
            List<SysMenu> sysMenus = sysMenuMapper.selectList(wrapper1);
            sysMenus.forEach(s -> {
                SysRoleMenu roleMenu = new SysRoleMenu();
                roleMenu.setRoleId(param.getId());
                roleMenu.setMenuId(s.getId());
                sysRoleMenuMapper.insert(roleMenu);
            });
        }
        return ServiceResult.success(1);
    }

    @Override
    public ServiceResult<?> inquireSysRoleByMenu(SysRoleParam param) {
        LambdaQueryWrapper<SysRoleMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysRoleMenu::getRoleId,param.getId());
        return ServiceResult.success(sysRoleMenuMapper.selectList(wrapper));
    }

}
