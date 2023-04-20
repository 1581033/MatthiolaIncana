package com.matth.entity.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Matthiola incana
 * @create 2022/6/6 11:48
 */
public class BaseModel implements Serializable {

    @TableId(type = IdType.ASSIGN_UUID)
    @Schema(title = "唯一标识")
    protected String id;

    @TableField(value = "deleted", select = false)
    @Schema(title = "删除标志", hidden = true)
    protected Integer deleted = 0;

    @TableField(fill = FieldFill.INSERT)
    @Schema(title = "创建人", hidden = true)
    protected String creatorId;

    @TableField(fill = FieldFill.INSERT)
    @Schema(title = "创建单位", hidden = true)
    protected String createUnit;

    @TableField(fill = FieldFill.INSERT)
    @Schema(title = "创建时间", hidden = true)
    protected Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Schema(title = "最后更新人", hidden = true)
    protected String updateId;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Schema(title = "最后更新单位", hidden = true)
    protected String updateUnit;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Schema(title = "最后更新时间", hidden = true)
    protected Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreateUnit() {
        return createUnit;
    }

    public void setCreateUnit(String createUnit) {
        this.createUnit = createUnit;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public String getUpdateUnit() {
        return updateUnit;
    }

    public void setUpdateUnit(String updateUnit) {
        this.updateUnit = updateUnit;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
