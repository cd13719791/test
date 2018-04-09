package com.moyou.moyouRms.task.schedule.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.moyou.moyouRms.manager.schedule.ScheduleManager;
import com.moyou.moyouRms.manager.schedule.TaskLogManager;
import com.moyou.moyouRms.model.po.task.base.ScheduleJob;
/* 案例 同步和不同步的区别  非同步需要加@DisallowConcurrentExecution */
public class Test10MJob implements Job{
	
	   /* 日志对象 */
    private static final Logger LOG = LoggerFactory.getLogger(Test10MJob.class);
    
	@Override
	public void execute(JobExecutionContext context){	
	        ScheduleJob scheduleJob = (ScheduleJob)context.getMergedJobDataMap().get(ScheduleManager.JOB_PARAM_KEY);  		   	     
	        String jobName=scheduleJob.getJobName();
			String jobGroup=scheduleJob.getJobGroup();
			String jobClass=scheduleJob.getJobClass();
			LOG.info("任务"+getClass()+"[" + jobName + "]成功运行");
	    	try {
				// 保存日志
	    		TaskLogManager.saveTaskLog(jobGroup + ":" + jobName, jobClass,TaskLogManager.NORMAL, "测试任务正常运行");
			} catch (Exception e) { 
				LOG.error("任务[" + jobName + "]异常",e);
				// 保存异常日志
				TaskLogManager.saveTaskLog(jobGroup + ":" + jobName, jobClass,TaskLogManager.EXCEPTION,e.toString());
			}
	}

	
}
