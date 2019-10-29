package com.masaiqi.calendar;

import com.masaiqi.job.Job1;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.AnnualCalendar;
import java.util.Calendar;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author masaiqi.com
 * @date 2019/10/29 5:10 下午
 */
public class CalendarDemo {

    public static void main(String[] args) throws SchedulerException {
        //排除日
        AnnualCalendar holidays = new AnnualCalendar();
        Calendar excludeDay = new GregorianCalendar(2019, 10, 29 );
        holidays.setDayExcluded(excludeDay, true);

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
                .modifiedByCalendar("excludeDay")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(2)
                        .repeatForever())
                .build();

        //SchedulerFactory
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();

        //Scheduler
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.addCalendar("excludeDay", holidays, false, false);

        //将jobDetail和trigger与scheduler绑定
        //jobDetail和trigger的绑定关系是1：N
        Date firstRunTime = scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
    }
}
