package com.matth.entity.param;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Matthiola incana
 * @create 2021/12/2 10:43
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysMenuParam extends BaseParam {

    private String id;

    private String path;

    private String name;

    private String hidden;

    private String parentId;

    private String hideChildrenInMenu;

    private String permission;

}
