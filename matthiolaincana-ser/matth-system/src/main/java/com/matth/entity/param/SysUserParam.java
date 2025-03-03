package com.matth.entity.param;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author Matthiola incana
 * @create 2021/11/16 14:45
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysUserParam extends BaseParam {

    private String deptId;

    private String id;

    private String name;

    private String username;

    private String avatar;

    private Integer status;

    private String telephone;

    private String password;

    private boolean enabled;

    private List<String> dateTime;

    private String test;

}
