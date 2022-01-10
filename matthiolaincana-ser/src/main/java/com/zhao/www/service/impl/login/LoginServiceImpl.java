package com.zhao.www.service.impl.login;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhao.www.entity.ServiceResult;
import com.zhao.www.entity.model.TestDao;
import com.zhao.www.entity.model.sys.SysUser;
import com.zhao.www.mapper.sys.SysUserMapper;
import com.zhao.www.service.login.LoginService;
import com.zhao.www.service.sys.SysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Matthiola incana
 * @create 2021/11/28 11:13
 */
@Service
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LoginServiceImpl implements LoginService {

    private final SysUserMapper sysUserMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUsername,s);
        SysUser sysUser = sysUserMapper.selectOne(wrapper);
        if (ObjectUtil.isNull(sysUser)){
            throw new UsernameNotFoundException("用户名错误");
        }
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMINS");
        return new User(sysUser.getUsername(),sysUser.getPassword(),authorities);
    }

    @Override
    public ServiceResult<?> userLogout(HttpServletRequest request) {
        return ServiceResult.success("退出成功");
    }
}
