package com.matth.entity.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Matthiola incana
 * @create 2022/7/14 16:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_pagination")
@EqualsAndHashCode(callSuper = true)
public class SysPagination extends BaseModel {

    @TableField
    private String title;

    @TableField
    private String align;

    @TableField
    private String ellipsis;

    @TableField
    private String colKey;

    @TableField
    private String dataIndex;

    @TableField
    private String width;

    @TableField
    private String sortOrder;

    @TableField
    private String status;

    @TableField
    private String tableName;

    @TableField
    private Integer isExport;

    @TableField
    private Integer isImport;

    @TableField
    private String inquiryMode;

}
