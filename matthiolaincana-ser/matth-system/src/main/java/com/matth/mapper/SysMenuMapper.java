package com.matth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.matth.entity.dto.sys.MenuDto;
import com.matth.entity.model.SysMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Matthiola incana
 * @create 2021/11/12 11:14
 */
@Repository
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    List<MenuDto> selectMenuByRolesId(@Param("list") List<String> list);
}
