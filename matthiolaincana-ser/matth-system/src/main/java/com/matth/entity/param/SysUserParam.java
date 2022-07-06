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

    private String id;

    private String name;

    private String username;

    private String avatar;

    private String status;

    private String telephone;

    private List<String> dateTime;

}
