package com.ocean.util;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @author yangjingsheng
 * @version 1.0
 * @date 2021/5/7 16:54
 */
//@Component
public class CronTask {

//    @PostConstruct
    public static void main(String[] args) throws SchedulerException, InterruptedException {
//    public void init() throws SchedulerException, InterruptedException {
        //创建调度器
//        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
//
//        //定义一个触发器
//        Trigger trigger = newTrigger().withIdentity("trigger1", "group1") //定义名称和所属的组
//                .startNow()
//                .withSchedule(simpleSchedule()
//                        .withIntervalInSeconds(2) //每隔2秒执行一次
//                        .withRepeatCount(10)) //总共执行11次(第一次执行不基数)
//                .build();
//
//        //定义一个JobDetail
//        JobDetail job = newJob(MyJob.class) //指定干活的类MailJob
//                .withIdentity("mailjob1", "mailgroup") //定义任务名称和分组
//                .usingJobData("email", "admin@10086.com") //定义属性
//                .build();
//
//        //调度加入这个job
//        scheduler.scheduleJob(job, trigger);
//
//        //启动
//        scheduler.start();
//
//        //等待20秒，让前面的任务都执行完了之后，再关闭调度器
//        Thread.sleep(20000);
//        scheduler.shutdown(true);
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        Date startTime = DateBuilder.nextGivenSecondDate(null, 8);

        JobDetail job = newJob(MyJob.class).withIdentity("mailJob", "mailGroup").build();

        CronTrigger trigger = newTrigger().withIdentity("trigger1", "group1").withSchedule(cronSchedule("0/2 * * * * ?"))
                .build();

        // schedule it to run!
        Date ft = scheduler.scheduleJob(job, trigger);

        System.out.println("使用的Cron表达式是："+trigger.getCronExpression());
//            System.out.printf("%s 这个任务会在 %s 准时开始运行，累计运行%d次，间隔时间是%d毫秒%n", job.getKey(), ft.toLocaleString(), trigger.getRepeatCount()+1, trigger.getRepeatInterval());

        scheduler.start();

        //等待200秒，让前面的任务都执行完了之后，再关闭调度器
        Thread.sleep(200000);
        scheduler.shutdown(true);
    }
}
