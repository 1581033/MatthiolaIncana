package com.matth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.matth.entity.dto.sys.UserInfoDto;
import com.matth.entity.model.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Matthiola incana
 * @create 2021/11/12 10:44
 */
@Mapper
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {
    UserInfoDto selectByIdForRole(@Param("id") String s);

    UserInfoDto selectByUserNameForRole(@Param("username")String tokenUserName);
}
