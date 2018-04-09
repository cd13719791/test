package com.moyou.moyouRms.task.usercrowd;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.moyou.moyouRms.model.usercrowd.UserCrowd;
import com.moyou.moyouRms.model.usercrowd.UserCrowdRecommend;
import com.moyou.moyouRms.service.usercrowd.UserCrowdRecommendService;
import com.moyou.moyouRms.service.usercrowd.UserCrowdService;
import com.moyou.moyouRms.task.BaseTask;
import com.moyou.moyouRms.util.StringUtil;

/**
 * 群定时推荐
 * @author liuxinyi
 * @date 2017年4月19日
 * @version 1.0.0
 */
@Component
public class UserCrowdRecommendTaskBak extends BaseTask {
	@Autowired
	private UserCrowdService  userCrowdService;
	@Autowired
	private UserCrowdRecommendService userCrowdRecommendService;
	
//	@Scheduled(cron = "0 0 0/1 * * ?")
//	@Scheduled(cron="0/5 * * * * ? ") //间隔5秒执行
	public void taskCycle() {
		logger.info(StringUtil.getTraceInfo()+":[task start]");
		try {
			UserCrowd userCrowd = new UserCrowd();
			userCrowd.setCrowdCreateType(2);//// 1前台创建，2后台创建默认
			List<UserCrowd> userCrowdList = userCrowdService.queryUserCrowdList(userCrowd);
			if (userCrowdList != null && userCrowdList.size() > 0) {
				UserCrowdRecommend userCrowdRecommend = new UserCrowdRecommend();
				if(userCrowdList.iterator().next().getId()==166){
					userCrowdRecommend.setRecommedStatus((short)0);//推荐状态0否1是
				}else{
				userCrowdRecommend.setRecommedStatus((short)1);//推荐状态0否1是
				List<UserCrowdRecommend> userCrowdRecommendList = userCrowdRecommendService.queryUserCrowdRecommendList(userCrowdRecommend);
				List<Integer> userCrowdRecommendIdList = userCrowdRecommendList.stream().map(UserCrowdRecommend::getCrowedId).collect(Collectors.toList());
				List<UserCrowd> currentUserCrowdList = userCrowdList.stream().filter(ucl -> !userCrowdRecommendIdList.contains(ucl.getId())).collect(Collectors.toList());
				if (currentUserCrowdList != null && currentUserCrowdList.size() > 0) {// 过滤数据///// 
					Collections.shuffle(currentUserCrowdList);
					UserCrowd uc = currentUserCrowdList.get(0);
					UserCrowdRecommend ucr = new UserCrowdRecommend();
					ucr.setCreateTime(new Date());
					ucr.setCrowedId(uc.getId());
					ucr.setModeType((short)1);// 1首页推荐群
					ucr.setRecommedStatus((short)1);// 推荐状态0否1是
					ucr.setRecommendSort(1);
					ucr.setUpdateTime(new Date());
					userCrowdRecommendService.addUserCrowdRecommend(ucr);
				}
			}
		}} catch (Exception e) {
			logger.error(StringUtil.getTraceInfo()+":["+ e.getMessage() +"]", e);
		} finally {
			logger.info(StringUtil.getTraceInfo()+":[task end]");
		}
	}
}
