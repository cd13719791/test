package com.moyou.moyouRms.task.systemreward;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.moyou.moyouRms.service.userfund.UserFundService;

/**
 * @author created by Chenxv
 * @date 2017年8月17日 下午4:39:26
 */
@Component
public class SystemRewardSchedule {
	private static final Logger LOG = LoggerFactory.getLogger(SystemRewardSchedule.class);
	@Resource
	private UserFundService userFundService;

	@Scheduled(cron = "0 0 */1 * * ?")
	// 每天18点执行
	public void start() {
		try {
			LOG.info("执行系统打赏");
			userFundService.insertSystemRewardV2ForSchedule();
		} catch (Exception e) {
			LOG.error("系统打赏异常  :[" + e.getMessage() + "]");
		}

	}
}
