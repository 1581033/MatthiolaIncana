package com.zhao.www.service.impl.sys;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhao.www.entity.ServiceResult;
import com.zhao.www.entity.component.AntTabelColumn;
import com.zhao.www.entity.model.sys.SysDictData;
import com.zhao.www.entity.model.sys.SysDictType;
import com.zhao.www.entity.param.sys.SysDictTypeParam;
import com.zhao.www.mapper.sys.SysDictDataMapper;
import com.zhao.www.mapper.sys.SysDictTypeMapper;
import com.zhao.www.service.sys.SysDictTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author Matthiola incana
 * @create 2022/1/22 10:56
 */
@Log4j2
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysDictTypeServiceImpl implements SysDictTypeService {

    private final SysDictTypeMapper sysDictTypeMapper;

    private final SysDictDataMapper sysDictDataMapper;

    private final RedisTemplate<String,SysDictData> redisTemplate;

    @Override
    public ServiceResult<?> inquireSysDictTypeList(SysDictTypeParam param) {
        LambdaQueryWrapper<SysDictType> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasLength(param.getName())){
            queryWrapper.like(SysDictType::getName,param.getName());
        }
        if (StringUtils.hasLength(param.getCode())){
            queryWrapper.like(SysDictType::getCode,param.getCode());
        }
        if (StringUtils.hasLength(param.getStatus())){
            queryWrapper.eq(SysDictType::getStatus,param.getStatus());
        }
        if (!CollectionUtils.isEmpty(param.getDateTime()) && param.getDateTime().size() > 1){
            queryWrapper.between(SysDictType::getCreateTime,param.getDateTime().get(0),param.getDateTime().get(1));
        }
        return ServiceResult.success(sysDictTypeMapper.selectList(queryWrapper));
    }

    @Override
    public ServiceResult<?> inquireSysDictTypeHeadList(SysDictTypeParam param) {
        return ServiceResult.success(AntTabelColumn.getTableLabel(SysDictType.class));
    }

    @Override
    public ServiceResult<?> increaseSysDictType(SysDictTypeParam param) {
        SysDictType sysDictType = new SysDictType();
        sysDictType.setCode(param.getCode());
        sysDictType.setName(param.getName());
        sysDictType.setStatus(param.getStatus());
        sysDictType.setRemark(param.getRemark());
        return ServiceResult.success(sysDictTypeMapper.insert(sysDictType));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ServiceResult<?> deleteSysDictType(SysDictTypeParam param) {
        SysDictType dictType = sysDictTypeMapper.selectById(param.getId());
        if (Boolean.TRUE.equals(redisTemplate.hasKey(dictType.getCode()))){
            redisTemplate.delete(dictType.getCode());
        }
        LambdaQueryWrapper<SysDictData> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysDictData::getTypeId,param.getId());
        sysDictDataMapper.delete(queryWrapper);
        return ServiceResult.success(sysDictTypeMapper.deleteById(param.getId()));
    }

    @Override
    public ServiceResult<?> updataSysDictType(SysDictTypeParam param) {
        SysDictType dictType = sysDictTypeMapper.selectById(param.getId());
        if (Boolean.TRUE.equals(redisTemplate.hasKey(dictType.getCode()))){
            redisTemplate.delete(dictType.getCode());
        }
        SysDictType sysDictType = new SysDictType();
        sysDictType.setId(param.getId());
        sysDictType.setCode(param.getCode());
        sysDictType.setName(param.getName());
        sysDictType.setStatus(param.getStatus());
        sysDictType.setRemark(param.getRemark());
        return ServiceResult.success(sysDictTypeMapper.updateById(sysDictType));
    }

    @Override
    public ServiceResult<?> getDictData(SysDictTypeParam param) {
        if (Boolean.TRUE.equals(redisTemplate.hasKey(param.getCode()))) {
            return ServiceResult.success(redisTemplate.opsForList().range(param.getCode(), 0, -1));
        }
        LambdaQueryWrapper<SysDictType> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysDictType::getCode,param.getCode());
        SysDictType sysDictType = sysDictTypeMapper.selectOne(queryWrapper);
        LambdaQueryWrapper<SysDictData> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysDictData::getTypeId,sysDictType.getId());
        List<SysDictData> sysDictData = sysDictDataMapper.selectList(lambdaQueryWrapper);
        redisTemplate.opsForList().rightPushAll(sysDictType.getCode(),sysDictData);
        return ServiceResult.success(sysDictData);
    }

}
