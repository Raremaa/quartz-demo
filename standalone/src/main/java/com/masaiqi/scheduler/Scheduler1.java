package com.masaiqi.scheduler;

import com.masaiqi.job.Job1;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author masaiqi.com
 * @date 2019/10/29 11:19 上午
 */
public class Scheduler1 {
    public static void main(String[] args) throws SchedulerException {

        //JobDetail
        JobDetail jobDetail = JobBuilder.newJob(Job1.class)
                .withIdentity("job1", "group1")
                .usingJobData("steamedBreadOfCorn", "窝窝头，一块钱四个，嘿嘿！")
                .usingJobData("millet", "谁xx买小米！")
                .build();

        //Trigger
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(2)
                        .repeatForever())
                .build();

        //SchedulerFactory
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();

        //Scheduler
        Scheduler scheduler = schedulerFactory.getScheduler();

        //将jobDetail和trigger与scheduler绑定
        //jobDetail和trigger的绑定关系是1：N
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
    }
}
