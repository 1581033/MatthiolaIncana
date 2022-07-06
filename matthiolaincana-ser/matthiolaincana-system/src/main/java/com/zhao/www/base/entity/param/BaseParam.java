package com.zhao.www.base.entity.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Matthiola incana
 * @create 2021/12/2 10:45
 */
@Data
public class BaseParam implements Serializable {

    /**
     * 分页: 页面位置
     */
    private Integer page;

    /**
     * 分页: 页面大小
     */
    private Integer size;

    /**
     * 参数校验分组：更新信息
     */
    public @interface update {
    }
    /**
     * 参数校验分组：保存信息
     */
    public @interface save {
    }
    /**
     * 参数校验分组：删除信息
     */
    public @interface delete {
    }
    /**
     * 参数校验分组：查询信息
     */
    public @interface inquire {
    }

}
