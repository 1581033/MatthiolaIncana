package com.zhao.www.service.impl.sys;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhao.www.entity.ServiceResult;
import com.zhao.www.entity.component.AntTabelColumn;
import com.zhao.www.entity.model.sys.SysDictData;
import com.zhao.www.entity.model.sys.SysDictType;
import com.zhao.www.entity.param.sys.SysDictDataParam;
import com.zhao.www.mapper.sys.SysDictDataMapper;
import com.zhao.www.service.sys.SysDictDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author Matthiola incana
 * @create 2022/1/22 10:57
 */
@Log4j2
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysDictDataServiceImpl implements SysDictDataService {

    private final SysDictDataMapper sysDictDataMapper;

    private final RedisTemplate<String,SysDictData> redisTemplate;

    @Override
    public ServiceResult<?> inquireSysDictDataList(SysDictDataParam param) {
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
        return ServiceResult.success(sysDictDataMapper.selectList(queryWrapper));
    }

    @Override
    public ServiceResult<?> inquireSysDictDataHeadList(SysDictDataParam param) {
        return ServiceResult.success(AntTabelColumn.getTableLabel(SysDictData.class));
    }

    @Override
    public ServiceResult<?> increaseSysDictData(SysDictDataParam param) {
        SysDictData sysDictData = new SysDictData();
        sysDictData.setTypeId(param.getTypeId());
        sysDictData.setValue(param.getValue());
        sysDictData.setCode(param.getCode());
        sysDictData.setSort(param.getSort());
        sysDictData.setRemark(param.getRemark());
        sysDictData.setStatus(param.getStatus());
        sysDictData.setCssClass(param.getCssClass());
        sysDictData.setListClass(param.getListClass());
        return ServiceResult.success(sysDictDataMapper.insert(sysDictData));
    }

    @Override
    public ServiceResult<?> deleteSysDictData(SysDictDataParam param) {
        SysDictData dictData = sysDictDataMapper.selectById(param.getId());
        if (Boolean.TRUE.equals(redisTemplate.hasKey(dictData.getCode()))){
            redisTemplate.delete(dictData.getCode());
        }
        return ServiceResult.success(sysDictDataMapper.deleteById(param.getId()));
    }

    @Override
    public ServiceResult<?> updataSysDictData(SysDictDataParam param) {
        SysDictData dictData = sysDictDataMapper.selectById(param.getId());
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
        return ServiceResult.success(sysDictDataMapper.updateById(sysDictData));
    }
}
