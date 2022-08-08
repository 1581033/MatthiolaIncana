package com.matth.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.matth.entity.dto.sys.UserInfoDto;
import com.matth.entity.model.SysUser;
import com.matth.entity.model.User;
import com.matth.entity.param.SysUserParam;
import com.matth.entity.result.ServiceResult;
import com.matth.handler.SecurityDetailsHolder;
import com.matth.mapper.SysUserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * @author Matthiola incana
 * @create 2021/11/12 10:41
 */
@Service
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysUserService extends ServiceImpl<SysUserMapper, SysUser>{

    public UserInfoDto userInfo() {
        User user = SecurityDetailsHolder.details();
        UserInfoDto userInfoDto = new UserInfoDto();
        BeanUtils.copyProperties(user,userInfoDto);
        return userInfoDto;
    }

    public ServiceResult<?> queryUserInformation(SysUserParam param) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        Page<SysUser> page = new Page<>(param.getPage(), param.getSize());
        Page<SysUser> userPage = page(page, wrapper);
        return ServiceResult.success(userPage);
    }

    public ServiceResult<?> inquireSysUserList(SysUserParam param) {
        /*QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        if (StringUtils.hasLength(param.getName())){
            wrapper.like("name",param.getName());
        }
        Page<SysUser> page = new Page<>(1, 10);
        Page<SysUser> userPage = sysUserMapper.selectPage(page, wrapper);*/
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasLength(param.getName())){
            queryWrapper.like(SysUser::getName,param.getName());
        }
        if (StringUtils.hasLength(param.getTelephone())){
            queryWrapper.like(SysUser::getTelephone,param.getTelephone());
        }
        /*if (StringUtils.hasLength(param.getStatus())){
            queryWrapper.eq(SysUser::getStatus,param.getStatus());
        }*/
        if (!CollectionUtils.isEmpty(param.getDateTime()) && param.getDateTime().size() > 1){
            queryWrapper.between(SysUser::getName,param.getDateTime().get(0),param.getDateTime().get(1));
        }
        return ServiceResult.success(list(queryWrapper));
    }

    public ServiceResult<?> increaseSysUser(SysUserParam param) {
        SysUser sysUser = new SysUser();
        sysUser.setName(param.getName());
        sysUser.setUsername(param.getUsername());
        sysUser.setAvatar(param.getAvatar());
        sysUser.setStatus(param.getStatus());
        sysUser.setTelephone(param.getTelephone());
        return ServiceResult.success(save(sysUser));
    }

    public ServiceResult<?> deleteSysUser(SysUserParam param) {
        return ServiceResult.success(removeById(param.getId()));
    }

    public ServiceResult<?> updataSysUser(SysUserParam param) {
        SysUser sysUser = new SysUser();
        sysUser.setId(param.getId());
        sysUser.setName(param.getName());
        sysUser.setUsername(param.getUsername());
        sysUser.setAvatar(param.getAvatar());
        sysUser.setStatus(param.getStatus());
        sysUser.setTelephone(param.getTelephone());
        return ServiceResult.success(updateById(sysUser));
    }

}
