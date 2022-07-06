package com.zhao.www.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhao.www.antui.AntTabelColumn;
import com.zhao.www.base.entity.result.ServiceResult;
import com.zhao.www.entity.model.SysDictData;
import com.zhao.www.entity.model.SysDictType;
import com.zhao.www.entity.param.SysDictTypeParam;
import com.zhao.www.mapper.SysDictDataMapper;
import com.zhao.www.mapper.SysDictTypeMapper;
import com.zhao.www.utils.aspose.cells.AsposeCellUtil;
import com.zhao.www.utils.aspose.cells.EntityDataTable;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Matthiola incana
 * @create 2022/1/22 10:56
 */
@Log4j2
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysDictTypeService extends ServiceImpl<SysDictTypeMapper,SysDictType> {

    private final SysDictDataMapper sysDictDataMapper;

    private final RedisTemplate<String,SysDictData> redisTemplate;

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
        /*if (!CollectionUtils.isEmpty(param.getDateTime()) && param.getDateTime().size() > 1){
            queryWrapper.between(SysDictType::getCreateTime,param.getDateTime().get(0),param.getDateTime().get(1));
        }*/
        return ServiceResult.success(list(queryWrapper));
    }

    public ServiceResult<?> inquireSysDictTypeHeadList(SysDictTypeParam param) {
        return ServiceResult.success(AntTabelColumn.getTableLabel(SysDictType.class));
    }

    public ServiceResult<?> increaseSysDictType(SysDictTypeParam param) {
        SysDictType sysDictType = new SysDictType();
        sysDictType.setCode(param.getCode());
        sysDictType.setName(param.getName());
        sysDictType.setStatus(param.getStatus());
        sysDictType.setRemark(param.getRemark());
        return ServiceResult.success(save(sysDictType));
    }

    @Transactional(rollbackFor = Exception.class)
    public ServiceResult<?> deleteSysDictType(SysDictTypeParam param) {
        SysDictType dictType = getById(param.getId());
        if (Boolean.TRUE.equals(redisTemplate.hasKey(dictType.getCode()))){
            redisTemplate.delete(dictType.getCode());
        }
        LambdaQueryWrapper<SysDictData> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysDictData::getTypeId,param.getId());
        sysDictDataMapper.delete(queryWrapper);
        return ServiceResult.success(removeById(param.getId()));
    }

    public ServiceResult<?> updataSysDictType(SysDictTypeParam param) {
        SysDictType dictType = getById(param.getId());
        if (Boolean.TRUE.equals(redisTemplate.hasKey(dictType.getCode()))){
            redisTemplate.delete(dictType.getCode());
        }
        SysDictType sysDictType = new SysDictType();
        sysDictType.setId(param.getId());
        sysDictType.setCode(param.getCode());
        sysDictType.setName(param.getName());
        sysDictType.setStatus(param.getStatus());
        sysDictType.setRemark(param.getRemark());
        return ServiceResult.success(removeById(sysDictType));
    }

    public ServiceResult<?> getDictData(SysDictTypeParam param) {
        if (Boolean.TRUE.equals(redisTemplate.hasKey(param.getCode()))) {
            return ServiceResult.success(redisTemplate.opsForList().range(param.getCode(), 0, -1));
        }
        LambdaQueryWrapper<SysDictType> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysDictType::getCode,param.getCode());
        SysDictType sysDictType = getOne(queryWrapper);
        LambdaQueryWrapper<SysDictData> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysDictData::getTypeId,sysDictType.getId());
        List<SysDictData> sysDictData = sysDictDataMapper.selectList(lambdaQueryWrapper);
        redisTemplate.opsForList().rightPushAll(sysDictType.getCode(),sysDictData);
        return ServiceResult.success(sysDictData);
    }

    public ServiceResult<?> uploadSysDictType(SysDictTypeParam param) {
        return null;
    }

    public void downloadSysDictType(SysDictTypeParam param, HttpServletResponse response) throws Exception {
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
        /*if (!CollectionUtils.isEmpty(param.getDateTime()) && param.getDateTime().size() > 1){
            queryWrapper.between(SysDictType::getCreateTime,param.getDateTime().get(0),param.getDateTime().get(1));
        }*/
        List<SysDictType> sysDictTypes = list(queryWrapper);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment; filename=" + "测试.xlsx");

        AsposeCellUtil<SysDictType> cellUtil = new AsposeCellUtil<>();
        EntityDataTable<SysDictType> dataTable = new EntityDataTable<>(sysDictTypes);
        cellUtil.importTabel(SysDictType.class,0,0, dataTable);

        //cellUtil.setDataSource(dataTable);
        cellUtil.save(response.getOutputStream());
        //EasyExcel.write(response.getOutputStream(),SysDictType.class).sheet("测试").doWrite(sysDictTypes);

    }
}
