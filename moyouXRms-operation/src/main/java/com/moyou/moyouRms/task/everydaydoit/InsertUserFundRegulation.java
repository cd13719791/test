package com.moyou.moyouRms.task.everydaydoit;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.moyou.moyouRms.model.statistics.UserFundRegulation;
import com.moyou.moyouRms.model.userfund.UserFundLog;
import com.moyou.moyouRms.service.statistics.UserFundRegulationService;
import com.moyou.moyouRms.service.userfund.UserFundLogService;

/**
 * @author created by Chenxv
 * @date 2017年9月18日 下午2:21:32 每日更新用户的流水数据
 */
@Component
public class InsertUserFundRegulation {
	@Resource
	private UserFundLogService userFundLogService;
	@Resource
	private UserFundRegulationService userFundRegulationService;

	@Scheduled(cron = "0 22 0 * * ?")
	public void start() {
		List<UserFundLog> userFundLogList = userFundLogService.selectUserFundLogForRegulation();
		if (userFundLogList == null || userFundLogList.size() == 0) {
			return;
		}
		userFundLogList.parallelStream().forEach(s -> {
			UserFundRegulation userFundRegulation = new UserFundRegulation();
			userFundRegulation.setCreateTime(s.getCreateTime());
			userFundRegulation.setModeId(s.getId());
			userFundRegulation.setModeType((int) s.getModeType());
			userFundRegulation.setNumber(s.getUserFund());
			userFundRegulationService.insertNonEmptyUserFundRegulation(userFundRegulation);
		});
	}
}