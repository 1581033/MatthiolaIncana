package com.matth.service;

import com.matth.entity.param.SchedulerQuartzParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Matthiola incana
 * @create 2022/6/17 18:13
 */
@Log4j2
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SchedulerQuartzService {

    private final Scheduler scheduler;

    public void addTask(SchedulerQuartzParam param) throws Exception {
        Class<Job> clzz = (Class<Job>) Class.forName(param.getJobClass());
        //按照实体类参数注释传参即可，执行的任务类需要包全路径
        JobDetail jobDetail = JobBuilder.newJob(clzz).withIdentity(param.getJobName()).build();
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(param.getId()).withSchedule(CronScheduleBuilder.cronSchedule(param.getCronExpression())).build();
        scheduler.scheduleJob(jobDetail, trigger);
    }

    public void pauseTask(SchedulerQuartzParam param) throws Exception {
        scheduler.pauseJob(JobKey.jobKey(param.getJobName()));
    }

    public void resumeTask(SchedulerQuartzParam param) throws Exception {
        scheduler.resumeJob(JobKey.jobKey(param.getJobName()));
    }

    public void runOne(SchedulerQuartzParam param)  throws Exception  {
        scheduler.triggerJob(JobKey.jobKey(param.getJobName()));
    }

    public void editTask(SchedulerQuartzParam param) throws Exception  {
        //获取到对应任务的触发器
        TriggerKey triggerKey = TriggerKey.triggerKey(param.getJobName());
        //设置定时任务执行方式
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(param.getCronExpression());
        //重新构建任务的触发器trigger
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
        //重置对应的job
        scheduler.rescheduleJob(triggerKey, trigger);
    }

    public void deleteTask(SchedulerQuartzParam param) throws Exception  {
        scheduler.deleteJob(JobKey.jobKey(param.getJobName()));
    }

}
