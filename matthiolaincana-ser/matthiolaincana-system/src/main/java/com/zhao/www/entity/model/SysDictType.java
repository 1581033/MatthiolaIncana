package com.zhao.www.entity.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhao.www.antui.AntTabel;
import com.zhao.www.base.entity.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Matthiola incana
 * @create 2022/1/22 10:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_dict_type")
@EqualsAndHashCode(callSuper = true)
public class SysDictType extends BaseModel {

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

}
