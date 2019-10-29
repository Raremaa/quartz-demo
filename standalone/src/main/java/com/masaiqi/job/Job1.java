package com.masaiqi.job;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author masaiqi.com
 * @date 2019/10/29 11:13 上午
 */
public class Job1 implements Job {
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Date now = Calendar.getInstance().getTime();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        System.err.println(sf.format(now) + " job1干活啦：" + jobDataMap.get("steamedBreadOfCorn"));
    }
}
