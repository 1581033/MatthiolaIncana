package com.zhao.www.entity.param.sys;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Matthiola incana
 * @create 2021/11/16 14:45
 */
@Data
public class SysUserParam implements Serializable {

    private String id;

    private String name;

    private String username;

    private String avatar;

    private String status;

    private String telephone;

}
