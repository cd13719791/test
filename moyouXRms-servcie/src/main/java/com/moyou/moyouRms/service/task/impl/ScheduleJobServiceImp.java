package com.moyou.moyouRms.service.task.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.quartz.CronTrigger;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.dao.task.base.ScheduleJobDao;
import com.moyou.moyouRms.manager.schedule.ScheduleManager;
import com.moyou.moyouRms.model.po.task.TaskSeting;
import com.moyou.moyouRms.model.po.task.base.ScheduleJob;
import com.moyou.moyouRms.response.Msg;
import com.moyou.moyouRms.service.BaseServiceImp;
import com.moyou.moyouRms.service.task.ScheduleJobService;
import com.moyou.moyouRms.service.task.TaskLogService;
import com.moyou.moyouRms.service.task.TaskSetingService;
import com.moyou.moyouRms.util.JsonUtil;
import com.moyou.moyouRms.util.schedule.CronExpressionUtil;

@Service("scheduleJobService")
public class ScheduleJobServiceImp extends BaseServiceImp<ScheduleJob> implements ScheduleJobService {

	/** 调度工厂Bean */
	@Autowired
	private Scheduler scheduler;
	@Autowired
	private TaskSetingService taskSetingService;
	@Resource
	TaskLogService taskLogService;
    /** 日志对象 */
    private static final Logger LOG = LoggerFactory.getLogger(ScheduleJobServiceImp.class);
	@Override
	public void initScheduleJob() {
		//查找启用的任务
		ScheduleJob aj=new ScheduleJob();
		aj.setStatus(1);	
		List<com.moyou.moyouRms.model.po.task.base.ScheduleJob> scheduleJobList = baseDao.find(aj);	
		if (scheduleJobList !=null && scheduleJobList.size() > 0) {
			for (ScheduleJob scheduleJob : scheduleJobList) {
				CronTrigger cronTrigger = ScheduleManager.getCronTrigger(scheduler, scheduleJob.getJobName(),scheduleJob.getJobGroup());
				try {
					TaskSeting ts = taskSetingService.selectByScheduleId(scheduleJob.getScheduleJobId());
					if (ts != null && ts.getIntervalSecond() != null) {
						int intervalSecond = ts.getIntervalSecond();
						// /10 * * * * ?
						String cronExpression=null;
						if(ts.getModeType()==ScheduleJob.TALK_MODE_DAY||ts.getModeType()==ScheduleJob.TALK_MODE_NIGHT){
							cronExpression = CronExpressionUtil.getCronExpressionBySecondForTalk(intervalSecond,ts.getModeType());
						}else{
							cronExpression = CronExpressionUtil.getCronExpressionBySecond(intervalSecond);
						}
						scheduleJob.setCronExpression(cronExpression);
					}
					scheduleJob.setExtend(ts);
					LOG.info(scheduleJob.getJobClass()+"【初始化成功】");
					if (cronTrigger == null) {
						// 不存在，创建一个
						ScheduleManager.createScheduleJob(scheduler, scheduleJob);	
					} else {
						// 已存在，那么更新相应的定时设置
						ScheduleManager.updateScheduleJob(scheduler, scheduleJob);
					}
				} catch (Exception e) {
					logger.error("创建定时任务失败",e);
				}
			}
		}
	}

	@Override
	@Transactional
	public int createScheduleJob(ScheduleJob o) {
		int res=0;
		try {
			o.setCreateTime(new Date());	
			//当状态为启用时
			if(o.getStatus()!=null && o.getStatus()==1){
				ScheduleManager.createScheduleJob(scheduler,o);		
			}
			//更新数据库
			super.insert(o);
			res=1;
		} catch (Exception e) {
			logger.error("创建任务失败",e);
		}	
		return res;
	}
	@Override
	@Transactional
	public int updateScheduleJob(ScheduleJob o) {
		int res=0;
		try {
			ScheduleJobDao dao=(ScheduleJobDao)baseDao;
			//从数据库查找原信息
			ScheduleJob scheduleJob=dao.getScheduleJobById(o.getScheduleJobId());
			//先删除
			ScheduleManager.deleteScheduleJob(scheduler,scheduleJob.getJobName(),scheduleJob.getJobGroup());
			//当状态为启用时
			if(o.getStatus()!=null && o.getStatus()==1){
				ScheduleManager.createScheduleJob(scheduler, o);		
			}
			//更新数据库
			o.setUpdateTime(new Date());
			dao.update(o);
			res=1;
		} catch (Exception e) {
			logger.error("创建任务失败",e);
		}	
		return res;
	}

	@Override
	@Transactional
	public int deleteScheduleJob(ScheduleJob o) {
		int res=0;
		try {
			ScheduleJobDao dao=(ScheduleJobDao)baseDao;
			//从数据库查找原信息
			ScheduleJob scheduleJob=dao.getScheduleJobById(o.getScheduleJobId());
			//先删除
			ScheduleManager.deleteScheduleJob(scheduler, scheduleJob.getJobName(),scheduleJob.getJobGroup());
			//更新数据库
			dao.delete(o);
			res=1;
		}catch (Exception e) {
			logger.error("删除任务失败", e);
		}
		return res;
	}

	@Override
	@Transactional
	public int runOnce(ScheduleJob o) {
		int res=0;
		try {
			ScheduleJobDao dao=(ScheduleJobDao)baseDao;
			//从数据库查找原信息
			ScheduleJob scheduleJob=dao.getScheduleJobById(o.getScheduleJobId());
			if(scheduleJob.getStatus()!=null && scheduleJob.getStatus()==1){
				//运行一次任务
				ScheduleManager.runOnce(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup());
				res=2;
			}else{
				//当任务没启动，必须先创建
				ScheduleManager.createScheduleJob(scheduler, scheduleJob);
				//时间短可能促发多次
				//ScheduleUtils.pauseJob(scheduler,scheduleJob.getJobName(), scheduleJob.getJobGroup());
				//然后立刻运行一次任务
				ScheduleManager.runOnce(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup());
				try {
					//休眠3秒，等任务完成，完成不了就加长休眠时间吧...
			        Thread.sleep(3000);
			    } catch (InterruptedException e) {
			        e.printStackTrace();
			    }
				//再删除任务
				ScheduleManager.deleteScheduleJob(scheduler,scheduleJob.getJobName(), scheduleJob.getJobGroup());
				res=1;
			}			
		} catch (Exception e) {
			logger.error("运行一次定时任务失败", e);
		}
		return res;
	}

	@Override
	@Transactional
	public int pauseJob(ScheduleJob o) {
		int res=0;
		try {
			ScheduleJobDao dao=(ScheduleJobDao)baseDao;
			//从数据库查找原信息
			ScheduleJob scheduleJob=dao.getScheduleJobById(o.getScheduleJobId());
			if(scheduleJob.getStatus()!=null && scheduleJob.getStatus()==1){
				//判断jobKey为不为空，如为空，任务已停止
				//先暂停任务
				//ScheduleUtils.pauseJob(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup());		
				ScheduleManager.deleteScheduleJob(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup());
				//更新数据库
				scheduleJob.setStatus(0);
				scheduleJob.setUpdateTime(new Date());
//				logger.info(JsonUtil.toJson(scheduleJob));
				dao.update(scheduleJob);
				res=1;
			}else{	
				//任务没启动，谈何暂停...
				res=2;			
			}
		} catch (Exception e) {
			logger.error("暂停定时任务失败", e);
		}
		return res;
	}

	@Override
	@Transactional
	public int resumeJob(ScheduleJob o) {
		int res=0;
		try {
			ScheduleJobDao dao=(ScheduleJobDao)baseDao;
			//从数据库查找原信息
			ScheduleJob scheduleJob=dao.getScheduleJobById(o.getScheduleJobId());
			if(scheduleJob.getStatus()!=null && scheduleJob.getStatus()==0){
				TaskSeting ts = taskSetingService.selectByScheduleId(scheduleJob.getScheduleJobId());
				if (ts != null && ts.getIntervalSecond() != null) {
					int intervalSecond = ts.getIntervalSecond();
					// /10 * * * * ?
					String cronExpression=null;
					if(ts.getModeType()==ScheduleJob.TALK_MODE_DAY||ts.getModeType()==ScheduleJob.TALK_MODE_NIGHT){
						cronExpression = CronExpressionUtil.getCronExpressionBySecondForTalk(intervalSecond,ts.getModeType());
					}else{
						cronExpression = CronExpressionUtil.getCronExpressionBySecond(intervalSecond);
					}
					scheduleJob.setCronExpression(cronExpression);
				}
				scheduleJob.setExtend(ts);
				LOG.info(scheduleJob.getJobClass()+"【初始化成功】");
				logger.info("【重启定时任务】"+JsonUtil.toJson(scheduleJob));
				Msg msg=ScheduleManager.createScheduleJob(scheduler, scheduleJob);
				if(!msg.isSuccess()){
					logger.error("恢复定时任务失败");
					return RESPONSE.ERROR;
				}
				//更新数据库
				scheduleJob.setStatus(1);
				scheduleJob.setUpdateTime(new Date());
				dao.update(scheduleJob);
				res=1;
			}else{
				res=2;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("恢复定时任务失败", e);
		}
		return res;
	}

	@Override
	public int updateStartOrStop(ScheduleJob record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean check(String str) {
		// TODO Auto-generated method stub
		ScheduleJobDao dao=(ScheduleJobDao)baseDao;
		ScheduleJob scheduleJob=dao.getScheduleJobByName(str);
//		TaskSeting taskSeting= taskSetingService.selectByScheduleId(scheduleJob.getScheduleJobId());
//		Integer intervalSecond = taskSeting.getIntervalSecond();
//		Long time=Long.valueOf(System.currentTimeMillis()+"")-(intervalSecond*1000);//上一次运行的时间
//		 Map<String, Object> map= new HashMap<String,Object>();
//		 map.put("time",new Date(time));
//		 map.put("name", scheduleJob.getJobClass());
//		List<TaskLog> list=taskLogService.queryNeerTaskLog(map);
		try {
			JobKey jobKey = new JobKey(scheduleJob.getJobName(),scheduleJob.getJobGroup());
//			JobDetail jb = scheduler.getJobDetail(jobKey);
//			System.out.println("==============:"+scheduler.checkExists(jobKey));
			if(!scheduler.checkExists(jobKey)){
				return false;
			}else{
				return true;
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
			return false;
		}
	}
	

}
