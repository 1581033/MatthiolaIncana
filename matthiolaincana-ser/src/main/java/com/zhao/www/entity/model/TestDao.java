package com.zhao.www.entity.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Matthiola incana
 * @create 2021/10/14 14:48
 */
@Data
@TableName(value = "sys_user")
public class TestDao {
    @TableId(value = "id")
    private String id;

    @TableField("user_name")
    private String userName;

    @TableField("pass_word")
    private String passWord;
}
