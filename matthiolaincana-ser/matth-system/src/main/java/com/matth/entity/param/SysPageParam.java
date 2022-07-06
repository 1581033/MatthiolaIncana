package com.matth.entity.param;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Matthiola incana
 * @create 2022/6/16 17:51
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysPageParam extends BaseParam {

    private String tableName;

}
