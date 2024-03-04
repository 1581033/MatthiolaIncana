package com.matth.entity.param;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Matthiola incana
 * @create 2023/2/21 17:24
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DepartmentParam extends BaseParam {

    private String id;

    private String currenId;

    private Boolean isRole = false;

    private String code;

}
