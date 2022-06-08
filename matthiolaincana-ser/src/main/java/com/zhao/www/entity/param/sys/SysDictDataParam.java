package com.zhao.www.entity.param.sys;

import com.zhao.www.base.entity.param.BaseParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Matthiola incana
 * @create 2022/2/7 10:49
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysDictDataParam extends BaseParam {

    private String id;

    private String typeId;

    private String value;

    private String code;

    private String sort;

    private String remark;

    private String status;

    private String cssClass;

    private String listClass;

}
