package com.moyou.moyouRms.task.checkSchedule;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.moyou.moyouRms.constants.CONSTANT;
import com.moyou.moyouRms.constants.PHONE;
import com.moyou.moyouRms.constants.enums.ResponseEnum;
import com.moyou.moyouRms.model.po.task.base.ScheduleJob;
import com.moyou.moyouRms.service.task.ScheduleJobService;
import com.moyou.moyouRms.util.SmsSendUtil;


@Component
public class CheckSchedule {
	  /** 日志对象 */
    private static final Logger LOG = LoggerFactory.getLogger(CheckSchedule.class);
    /** 定时任务service */
    @Autowired
    private ScheduleJobService  scheduleJobService;
//    @Resource
//    BaseDao baseDao;
//	@Scheduled(cron="0 */1 * * * ?")
	@Scheduled(cron="0 0 0,6,12,18 * * ? ")
	    public void checkSchedule() {
		ScheduleJob aj=new ScheduleJob();
		aj.setStatus(1);	
		List<ScheduleJob> scheduleJobList = scheduleJobService.find(aj);	
		if(!scheduleJobList.isEmpty()){
			scheduleJobList.stream().map(ScheduleJob::getJobName).forEach(str ->{
				if(scheduleJobService.check(str)){
				}else{
					LOG.error("任务执行异常=====>"+str);
					try {
						SmsSendUtil.send(PHONE.CHEN_XV, str, "陌友", CONSTANT.REG_SCHEDULE,ResponseEnum.SCHEDULE_DOES_NOT_DO.getValue());
					} catch (Exception e) {
						String[] strs={PHONE.CHEN_XV,PHONE.LIU_XIN_YI};
						SmsSendUtil.send(strs, getClass().getName(), "陌友", CONSTANT.REG_SCHEDULE,ResponseEnum.SCHEDULE_ERRO.getValue());
						e.printStackTrace();
					}
				};
			});
		}
		
	    }
	public static void main(String[] args) {
		try {
			String[] strs={PHONE.CHEN_XV};
			SmsSendUtil.send(strs, "test", "陌友", CONSTANT.REG_SCHEDULE,ResponseEnum.SCHEDULE_DOES_NOT_DO.getValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
