package com.zhao.www.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhao.www.entity.model.SysPage;
import com.zhao.www.entity.param.SysPageParam;
import com.zhao.www.mapper.SysPageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Matthiola incana
 * @create 2022/6/16 17:52
 */
@Service
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysPageService extends ServiceImpl<SysPageMapper, SysPage> {
    public List<SysPage> getList(SysPageParam param) {
        LambdaQueryWrapper<SysPage> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(SysPage::getTitle,SysPage::getAlign,SysPage::getCode,SysPage::getColSpan,SysPage::getWidth,SysPage::getSortOrder,SysPage::getEllipsis);
        queryWrapper.eq(SysPage::getTableName,param.getTableName());
        return list(queryWrapper);
    }

}
