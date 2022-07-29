package com.matth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.matth.entity.model.User;
import org.springframework.stereotype.Repository;

/**
 * @author Matthiola incana
 * @create 2022/7/6 9:58
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
    User selectByUserName(String username);
}
