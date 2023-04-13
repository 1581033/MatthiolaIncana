package com.matth.entity.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fhs.core.trans.vo.TransPojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Matthiola incana
 * @create 2022/7/15 15:09
 */
@Data
@TableName(value = "sys_department")
@EqualsAndHashCode(callSuper = true)
public class SysDepartment extends BaseModel implements TransPojo {

    @TableField
    private String code;

    @TableField
    private String name;

    @TableField
    private Integer status;

    @TableField
    private String parentId;

    @TableField(exist = false)
    private List<SysDepartment> children;

}
