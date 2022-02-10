package com.zhao.www.entity.param.sys;

import com.baomidou.mybatisplus.annotation.TableField;
import com.zhao.www.annotation.component.AntTabel;
import com.zhao.www.entity.param.BaseParam;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author Matthiola incana
 * @create 2022/1/22 14:58
 */
@Data
public class SysDictTypeParam extends BaseParam {

    private String id;

    private String name;

    private String code;

    private String remark;

    private String status;

    private List<String> dateTime;

}
