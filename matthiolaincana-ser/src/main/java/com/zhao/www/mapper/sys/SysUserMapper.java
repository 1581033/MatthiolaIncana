package com.zhao.www.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhao.www.entity.dto.sys.UserInfoDto;
import com.zhao.www.entity.model.sys.SysUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Matthiola incana
 * @create 2021/11/12 10:44
 */
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {
    UserInfoDto selectByIdForRole(@Param("id") String s);

    UserInfoDto selectByUserNameForRole(@Param("username")String tokenUserName);
}
