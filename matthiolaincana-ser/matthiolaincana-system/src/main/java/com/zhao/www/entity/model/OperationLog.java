package com.zhao.www.entity.model;

import com.zhao.www.base.entity.model.BaseModel;
import lombok.Data;

import java.util.Date;

/**
 * @author Matthiola incana
 * @create 2022/6/8 14:42
 */
@Data
public class OperationLog extends BaseModel {

    private String name;

    private String ip;

    private String browser;

    private String mac;

    private String url;

    private String method;

    private String params;

    private Long spendTime;

    private Integer status;

    private Date startTime;

    private Date endTime;

}
