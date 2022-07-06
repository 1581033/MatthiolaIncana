package com.matth.entity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Matthiola incana
 * @create 2022/6/17 18:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SchedulerQuartz extends BaseModel {

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
