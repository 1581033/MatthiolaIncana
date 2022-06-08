package com.zhao.www.base.entity.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Matthiola incana
 * @create 2021/12/2 10:45
 */
@Data
public class BaseParam implements Serializable {

    private Integer page;

    private Integer size;

}
