package com.zhao.www.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhao.www.antui.AntTabelColumn;
import com.zhao.www.entity.model.SysDictData;
import com.zhao.www.entity.param.SysDictDataParam;
import com.zhao.www.mapper.SysDictDataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author Matthiola incana
 * @create 2022/1/22 10:57
 */
@Log4j2
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysDictDataService extends ServiceImpl<SysDictDataMapper,SysDictData>{

    private final RedisTemplate<String,SysDictData> redisTemplate;

    public List<SysDictData> inquireSysDictDataList(SysDictDataParam param) {
        LambdaQueryWrapper<SysDictData> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasLength(param.getTypeId())){
            queryWrapper.eq(SysDictData::getTypeId,param.getTypeId());
        }
        if (StringUtils.hasLength(param.getValue())){
            queryWrapper.like(SysDictData::getValue,param.getValue());
        }
        if (StringUtils.hasLength(param.getStatus())){
            queryWrapper.eq(SysDictData::getStatus,param.getStatus());
        }
        return list(queryWrapper);
    }

    public List<AntTabelColumn> inquireSysDictDataHeadList(SysDictDataParam param) {
        return AntTabelColumn.getTableLabel(SysDictData.class);
    }

    public boolean increaseSysDictData(SysDictDataParam param) {
        SysDictData sysDictData = new SysDictData();
        sysDictData.setTypeId(param.getTypeId());
        sysDictData.setValue(param.getValue());
        sysDictData.setCode(param.getCode());
        sysDictData.setSort(param.getSort());
        sysDictData.setRemark(param.getRemark());
        sysDictData.setStatus(param.getStatus());
        sysDictData.setCssClass(param.getCssClass());
        sysDictData.setListClass(param.getListClass());
        return save(sysDictData);
    }

    public boolean deleteSysDictData(SysDictDataParam param) {
        SysDictData dictData = getById(param.getId());
        if (Boolean.TRUE.equals(redisTemplate.hasKey(dictData.getCode()))){
            redisTemplate.delete(dictData.getCode());
        }
        return removeById(param.getId());
    }

    public boolean updataSysDictData(SysDictDataParam param) {
        SysDictData dictData = getById(param.getId());
        if (Boolean.TRUE.equals(redisTemplate.hasKey(dictData.getCode()))){
            redisTemplate.delete(dictData.getCode());
        }
        SysDictData sysDictData = new SysDictData();
        sysDictData.setId(param.getId());
        sysDictData.setTypeId(param.getTypeId());
        sysDictData.setValue(param.getValue());
        sysDictData.setCode(param.getCode());
        sysDictData.setSort(param.getSort());
        sysDictData.setRemark(param.getRemark());
        sysDictData.setStatus(param.getStatus());
        sysDictData.setCssClass(param.getCssClass());
        sysDictData.setListClass(param.getListClass());
        return updateById(sysDictData);
    }
}
