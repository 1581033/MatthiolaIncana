package com.zhao.www.entity.model.sys;

import com.baomidou.mybatisplus.annotation.*;
import com.zhao.www.annotation.component.AntTabel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Matthiola incana
 * @create 2022/1/22 10:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_dict_type")
public class SysDictType implements Serializable {

    @AntTabel(title = "字典编号")
    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    private String id;

    @AntTabel(title = "字典名称")
    @TableField("name")
    private String name;

    @AntTabel(title = "字典类型")
    @TableField("code")
    private String code;

    @TableField("sort")
    private String sort;

    @AntTabel(title = "备注")
    @TableField("remark")
    private String remark;

    @AntTabel(title = "状态")
    @TableField("status")
    private String status;

    @AntTabel(title = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "creator_id", fill = FieldFill.INSERT)
    private String creatorId;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(value = "update_id", fill = FieldFill.INSERT_UPDATE)
    private String updateId;

}
