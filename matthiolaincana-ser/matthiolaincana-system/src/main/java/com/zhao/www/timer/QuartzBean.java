package com.zhao.www.timer;

import lombok.Data;

/**
 * @author Matthiola incana
 * @create 2022/6/17 17:54
 */
@Data
public class QuartzBean {
    /** 任务id */
    private String  id;

    /** 任务名称 */
    private String jobName;

    /** 任务执行类 全路径*/
    private String jobClass;

    /** 任务状态 启动还是暂停*/
    private Integer status;

    /** 任务运行时间表达式 */
    private String cronExpression;
}
