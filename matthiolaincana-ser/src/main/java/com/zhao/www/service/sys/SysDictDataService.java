package com.zhao.www.service.sys;

import com.zhao.www.entity.ServiceResult;
import com.zhao.www.entity.param.sys.SysDictDataParam;

/**
 * @author Matthiola incana
 * @create 2022/1/22 10:57
 */
public interface SysDictDataService {
    ServiceResult<?> inquireSysDictDataList(SysDictDataParam param);

    ServiceResult<?> inquireSysDictDataHeadList(SysDictDataParam param);

    ServiceResult<?> increaseSysDictData(SysDictDataParam param);

    ServiceResult<?> deleteSysDictData(SysDictDataParam param);

    ServiceResult<?> updataSysDictData(SysDictDataParam param);
}
