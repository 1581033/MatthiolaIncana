package com.matth.entity.param;

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

    private Integer status;
    private String roleDescribe;
    private String[] menus;

    private List<String> dateTime;

}
