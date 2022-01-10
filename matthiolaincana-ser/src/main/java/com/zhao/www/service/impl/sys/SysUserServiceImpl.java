package com.zhao.www.service.impl.sys;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhao.www.entity.ServiceResult;
import com.zhao.www.entity.component.AntTabelColumn;
import com.zhao.www.entity.dto.sys.RoleDto;
import com.zhao.www.entity.dto.sys.UserInfoDto;
import com.zhao.www.entity.model.sys.SysUser;
import com.zhao.www.entity.param.sys.SysUserParam;
import com.zhao.www.mapper.sys.SysUserMapper;
import com.zhao.www.service.sys.SysUserService;
import com.zhao.www.utils.jwt.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Matthiola incana
 * @create 2021/11/12 10:41
 */
@Service
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysUserServiceImpl implements SysUserService {

    private final SysUserMapper sysUserMapper;
    private final RedisTemplate<String,Object> redisTemplate;

    @Override
    public ServiceResult<?> userInfo(HttpServletRequest request) {
        String token = request.getHeader("Access-Token");
        String o = Objects.requireNonNull(redisTemplate.opsForValue().get(token)).toString();
        UserInfoDto sysUser = sysUserMapper.selectByUserNameForRole(JwtTokenUtil.getTokenUserName(token,o));
        return ServiceResult.success(sysUser);
    }

    @Override
    public ServiceResult<?> queryUserInformation(SysUserParam param) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        Page<SysUser> page = new Page<>(1, 10);
        Page<SysUser> userPage = sysUserMapper.selectPage(page, wrapper);
        return ServiceResult.success(userPage);
    }

    @Override
    public ServiceResult<?> inquireSysUserList(SysUserParam param) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        if (StringUtils.hasLength(param.getName())){
            wrapper.like("name",param.getName());
        }
        Page<SysUser> page = new Page<>(1, 10);
        Page<SysUser> userPage = sysUserMapper.selectPage(page, wrapper);
        return ServiceResult.success(userPage);
    }

    @Override
    public ServiceResult<?> inquireSysUserHeadList(SysUserParam param) {
        return ServiceResult.success(AntTabelColumn.getTableLabel(SysUser.class));
    }

    @Override
    public ServiceResult<?> increaseSysUser(SysUserParam param) {
        String format = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        SysUser sysUser = new SysUser();
        sysUser.setName(param.getName());
        sysUser.setUsername(param.getUsername());
        sysUser.setAvatar(param.getAvatar());
        sysUser.setStatus(param.getStatus());
        sysUser.setTelephone(param.getTelephone());
        sysUser.setCreateTime(format);
        return ServiceResult.success(sysUserMapper.insert(sysUser));
    }

    @Override
    public ServiceResult<?> deleteSysUser(SysUserParam param) {
        return ServiceResult.success(sysUserMapper.deleteById(param.getId()));
    }

    @Override
    public ServiceResult<?> updataSysUser(SysUserParam param) {
        SysUser sysUser = new SysUser();
        sysUser.setId(param.getId());
        sysUser.setName(param.getName());
        sysUser.setUsername(param.getUsername());
        sysUser.setAvatar(param.getAvatar());
        sysUser.setStatus(param.getStatus());
        sysUser.setTelephone(param.getTelephone());
        return ServiceResult.success(sysUserMapper.updateById(sysUser));
    }

}
