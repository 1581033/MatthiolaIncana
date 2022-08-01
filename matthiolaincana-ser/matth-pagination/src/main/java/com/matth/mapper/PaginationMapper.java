package com.matth.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.matth.entity.model.SysPagination;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Matthiola incana
 * @create 2022/7/14 16:12
 */
@Repository
public interface PaginationMapper extends BaseMapper<SysPagination> {
    IPage<Object> selectObjsByPage(IPage<Object> page, @Param(Constants.WRAPPER) Wrapper<Object> wrapper,@Param("sq") String name);

    List<Object> selectObjsByList(@Param(Constants.WRAPPER) QueryWrapper<Object> queryWrapper,@Param("sq")  String name);
}
