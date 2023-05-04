package com.matth.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.matth.entity.dto.sys.UserInfoDto;
import com.matth.entity.model.SysDepartment;
import com.matth.entity.model.SysUser;
import com.matth.entity.model.User;
import com.matth.entity.param.SysUserParam;
import com.matth.handler.SecurityDetailsHolder;
import com.matth.mapper.SUserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

/**
 * @author Matthiola incana
 * @create 2023/2/22 10:16
 */
@Service
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SUserService extends ServiceImpl<SUserMapper, SysUser> {

    public UserInfoDto userInfo() {
        User user = SecurityDetailsHolder.details();
        UserInfoDto userInfoDto = new UserInfoDto();
        BeanUtils.copyProperties(user,userInfoDto);
        return userInfoDto;
    }

    public List<SysUser> list(SysUserParam param) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        return list(wrapper);
    }

    public Object page(SysUserParam param) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasLength(param.getDeptId())){
            wrapper.eq(SysUser::getDeptId, param.getDeptId());
        }
        return page(new Page<>(param.getPage(), param.getSize()), wrapper);
    }

}
