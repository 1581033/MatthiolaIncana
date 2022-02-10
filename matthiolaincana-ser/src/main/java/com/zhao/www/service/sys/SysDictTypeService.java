package com.zhao.www.service.sys;

import com.zhao.www.entity.ServiceResult;
import com.zhao.www.entity.model.sys.SysDictType;
import com.zhao.www.entity.param.sys.SysDictTypeParam;

/**
 * @author Matthiola incana
 * @create 2022/1/22 10:57
 */
public interface SysDictTypeService {
    ServiceResult<?> inquireSysDictTypeList(SysDictTypeParam param);

    ServiceResult<?> inquireSysDictTypeHeadList(SysDictTypeParam param);

    ServiceResult<?> increaseSysDictType(SysDictTypeParam param);

    ServiceResult<?> deleteSysDictType(SysDictTypeParam param);

    ServiceResult<?> updataSysDictType(SysDictTypeParam param);

    ServiceResult<?> getDictData(SysDictTypeParam param);
}
