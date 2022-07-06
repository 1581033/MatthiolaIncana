package com.matth.entity.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Matthiola incana
 * @create 2022/6/16 17:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_page")
@EqualsAndHashCode(callSuper = true)
public class SysPage extends BaseModel {

    @TableField("title")
    private String title;

    @TableField("align")
    private String align;

    @TableField("ellipsis")
    private String ellipsis;

    @TableField("col_span")
    private String colSpan;

    @TableField("data_index")
    private String dataIndex;

    @TableField("width")
    private String width;

    @TableField("sort_order")
    private String sortOrder;

    @TableField("code")
    private String code;

    @TableField("table_name")
    private String tableName;

}
