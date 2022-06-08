package com.zhao.www.entity.param.sys;

import com.zhao.www.base.entity.param.BaseParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author Matthiola incana
 * @create 2021/12/15 15:17
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysRoleParam extends BaseParam {

    private String id;
    private String name;
    private String status;
    private String roleDescribe;
    private String[] menus;

    private List<String> dateTime;

}
