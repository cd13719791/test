package com.moyou.moyouRms.task.user;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.moyou.moyouRms.task.BaseTask;
import com.moyou.moyouRms.util.DateTimeUtil;
import com.moyou.moyouRms.util.StringUtil;

/**
 * 修改用户活跃时间
 * @author liuxinyi
 * @date 2016年6月2日
 * @version 1.0.0
 */
@Component
public class UserTestTask extends BaseTask {
//	@Autowired
//	private UserService  service;
	
//	@Scheduled(cron = "0 0 23 * * ?")
//	@Scheduled(cron="0/5 * * * * ? ") //间隔5秒执行
	public void taskCycle() {
		logger.info(StringUtil.getTraceInfo()+":[task start]");
		try {
			System.out.println("task===================================");
			System.out.println(DateTimeUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
		} catch (Exception e) {
			logger.error(StringUtil.getTraceInfo()+":["+ e.getMessage() +"]", e);
		} finally {
			logger.info(StringUtil.getTraceInfo()+":[task end]");
		}
	}
}
