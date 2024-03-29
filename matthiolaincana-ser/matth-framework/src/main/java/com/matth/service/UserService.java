package com.matth.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.matth.entity.model.User;
import com.matth.mapper.UserMapper;
import com.matth.utils.jwt.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author Matthiola incana
 * @create 2022/7/6 9:56
 */
@Service
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService extends ServiceImpl<UserMapper, User> implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = Optional.ofNullable(baseMapper.selectByUserName(username)).orElseThrow(() -> new UsernameNotFoundException("用户名错误"));
        user.setAuthorities(user.getRoles().toArray(new String[0]));
        return user;
    }

    public Authentication loadUserByToken(String token, String key) throws UsernameNotFoundException {
        String userName = JwtTokenUtil.getTokenUserName(token, key);
        User user = Optional.ofNullable(baseMapper.selectByUserName(userName)).orElseThrow(() -> new UsernameNotFoundException("用户名错误"));
        user.setAuthorities(user.getRoles().toArray(new String[0]));
        return user.getAuthentication();
    }

}
