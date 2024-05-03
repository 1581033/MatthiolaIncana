package com.matth.entity.param;

import com.matth.entity.model.SysDepartment;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

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

    private String name;

    private List<SysDepartment> children;

}
