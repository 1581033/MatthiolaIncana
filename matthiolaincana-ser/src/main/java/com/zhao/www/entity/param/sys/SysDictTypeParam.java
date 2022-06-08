package com.zhao.www.entity.param.sys;

import com.zhao.www.base.entity.param.BaseParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author Matthiola incana
 * @create 2022/1/22 14:58
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysDictTypeParam extends BaseParam {

    private String id;

    private String name;

    private String code;

    private String remark;

    private String status;

    private List<String> dateTime;

}
