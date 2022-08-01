package com.matth.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.matth.entity.model.SysPagination;
import com.matth.entity.model.User;
import com.matth.entity.param.SysPaginationParam;
import com.matth.handler.SecurityDetailsHolder;
import com.matth.mapper.PaginationMapper;
import com.matth.utils.aspose.cells.AsposeCellUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Matthiola incana
 * @create 2022/7/14 16:12
 */
@Log4j2
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PaginationService extends ServiceImpl<PaginationMapper, SysPagination> {

    public List<SysPagination> headerList(String routeName) {
        LambdaQueryWrapper<SysPagination> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysPagination::getTableName,routeName);
        wrapper.orderByAsc(SysPagination::getSortOrder);
        return list(wrapper);
    }

    public List<SysPagination> formList(String routeName) {
        LambdaQueryWrapper<SysPagination> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysPagination::getTableName,routeName);
        wrapper.isNotNull(SysPagination::getInquiryMode);
        wrapper.orderByAsc(SysPagination::getSortOrder);
        return list(wrapper);
    }

    public IPage<Object> page(SysPaginationParam param) {
        Object[] roles = SecurityDetailsHolder.roles();

        System.out.println(SecurityDetailsHolder.roles());
        Map<String, Object> map = param.getMap();
        if (!ObjectUtils.isEmpty(param.getRouteName())){
            LambdaQueryWrapper<SysPagination> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(SysPagination::getTableName,param.getRouteName());
            QueryWrapper<Object> queryWrapper = new QueryWrapper<>();
            String[] stringList;
            if (CollectionUtils.isEmpty(map)){
                stringList = list(wrapper).stream().map(s -> StrUtil.toUnderlineCase(s.getColKey())).toArray(String[]::new);
            } else {
                stringList = list(wrapper).stream().peek(s -> {
                    if (StringUtils.hasLength(s.getInquiryMode()) && !ObjectUtils.isEmpty(map.get(s.getColKey()))) {
                        queryWrapper.eq(StrUtil.toUnderlineCase(s.getColKey()), map.get(s.getColKey()));
                    }
                }).map(s -> StrUtil.toUnderlineCase(s.getColKey())).toArray(String[]::new);
            }
            queryWrapper.select(stringList);
            return baseMapper.selectObjsByPage(new Page<>(param.getPage(), param.getSize()), queryWrapper, "sys_user");
        }
        return null;
    }

    public List<Object> list(SysPaginationParam param) {
        Map<String, Object> map = param.getMap();
        if (!ObjectUtils.isEmpty(param.getRouteName())){
            LambdaQueryWrapper<SysPagination> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(SysPagination::getTableName,param.getRouteName());
            QueryWrapper<Object> queryWrapper = new QueryWrapper<>();
            String[] stringList;
            if (CollectionUtils.isEmpty(map)){
                stringList = list(wrapper).stream().map(s -> StrUtil.toUnderlineCase(s.getColKey())).toArray(String[]::new);
            } else {
                stringList = list(wrapper).stream().peek(s -> {
                    if (StringUtils.hasLength(s.getInquiryMode()) && !ObjectUtils.isEmpty(map.get(s.getColKey()))) {
                        queryWrapper.eq(StrUtil.toUnderlineCase(s.getColKey()), map.get(s.getColKey()));
                    }
                }).map(s -> StrUtil.toUnderlineCase(s.getColKey())).toArray(String[]::new);
            }
            queryWrapper.select(stringList);
            log.info(queryWrapper.getSqlSelect());
            log.info(queryWrapper.getCustomSqlSegment());
            return baseMapper.selectObjsByList(queryWrapper, "sys_user");
        }
        return null;
    }

    public void upload(MultipartFile file, SysPaginationParam param) throws Exception {
        AsposeCellUtil<Object> asposeCellUtil = new AsposeCellUtil<>(file.getInputStream());
        Object[][] objects = asposeCellUtil.exportObject(1, 0, 0);
        for (int i = 1; i < objects.length; i++) {
            //HashMap<String, Object> hashMap = new HashMap<>();
            /*for (TableLabel label : labelList) {
                for (int i1 = 0; i1 < objects[0].length; i1++) {
                    if (label.getTabelName().equals(objects[0][i1]) && StringUtils.hasLength(objects[0][i1].toString())){
                        hashMap.put(label.getTabelCode(),objects[i][i1]);
                    }
                }
            }
            arrayList.add(hashMap);*/
        }
    }

    public void download(SysPaginationParam param) {
        AsposeCellUtil<Object> asposeCellUtil = new AsposeCellUtil<>();

    }
}
