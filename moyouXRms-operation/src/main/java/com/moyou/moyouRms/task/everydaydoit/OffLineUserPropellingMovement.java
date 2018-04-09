package com.moyou.moyouRms.task.everydaydoit;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.moyou.moyouRms.constants.CONSTANT;
import com.moyou.moyouRms.constants.enums.PushAlertEnum;
import com.moyou.moyouRms.manager.jpush.JpushService;
import com.moyou.moyouRms.model.user.UserInfo;
import com.moyou.moyouRms.service.usersign.UserSignService;

/**
 * @author created by Chenxv
 * @date 2017年8月17日 下午4:39:26
 */
@Component
public class OffLineUserPropellingMovement {
	private static final Logger LOG = LoggerFactory.getLogger(OffLineUserPropellingMovement.class);
	@Resource
	private UserSignService userSignService;
	@Resource
	private JpushService jpushService;
	private static final int day5 = 5;
	private static final int day10 = 10;

	@Scheduled(cron = "0 20 * * * ?")
	// 每天18点执行
	public void start() {
		LOG.info("开始对长期不在线的用户推送了");
		/**
		 * 5天推送
		 */
		List<String> list = userSignService.selectOffLineUser(day5).parallelStream()
				.map(UserInfo::getPushChatId).collect(Collectors.toList());
		int size = list.size();
		String[] pushChatArray = (String[]) list.toArray(new String[size]);
		if (pushChatArray.length > 0)
			jpushService.sendBarPush(pushChatArray, PushAlertEnum.JPUSH_OFFLINE_USER_5_DAY,
					CONSTANT.JPUSH_OFFLINE_USER_5_DAY_TEXT);
		/**
		 * 10天推送
		 */
		list = userSignService.selectOffLineUser(day10).parallelStream()
				.map(UserInfo::getPushChatId).collect(Collectors.toList());
		size = list.size();
		pushChatArray = (String[]) list.toArray(new String[size]);
		if (pushChatArray.length > 0)
			jpushService.sendBarPush(pushChatArray, PushAlertEnum.JPUSH_OFFLINE_USER_10_DAY,
					CONSTANT.JPUSH_OFFLINE_USER_10_DAY_TEXT);
	}
}
