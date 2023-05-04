package com.matth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.matth.entity.dto.sys.UserInfoDto;
import com.matth.entity.model.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Matthiola incana
 * @create 2023/2/22 10:17
 */
@Mapper
@Repository
public interface SUserMapper extends BaseMapper<SysUser> {

    UserInfoDto selectByIdForRole(@Param("id") String s);

    UserInfoDto selectByUserNameForRole(@Param("username")String tokenUserName);

}
