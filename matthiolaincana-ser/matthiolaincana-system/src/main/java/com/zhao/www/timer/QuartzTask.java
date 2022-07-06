package com.zhao.www.timer;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * @author Matthiola incana
 * @create 2022/6/17 17:51
 */
public class QuartzTask extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("定时任务执行时间："+new Date().toLocaleString());
    }

}
