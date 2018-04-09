package com.moyou.moyouRms.service.notification.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.moyou.moyouRms.dao.notification.UserNotificationMapper;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.notification.CommentData;
import com.moyou.moyouRms.model.notification.DataCondition;
import com.moyou.moyouRms.model.notification.DataModel;
import com.moyou.moyouRms.model.notification.PraiseAndRewardData;
import com.moyou.moyouRms.model.notification.RecommentData;
import com.moyou.moyouRms.model.notification.RepublishTalkData;
import com.moyou.moyouRms.model.notification.UserNotification;
import com.moyou.moyouRms.model.notification.UserNotificationQuery;
import com.moyou.moyouRms.model.notification.UserNotificationReturnItem;
import com.moyou.moyouRms.service.notification.UserNotificationService;
import com.moyou.moyouRms.util.JsonUtil;

/**
 * 用户通知
 * 
 * @author PzC.
 * @time 2017年3月21日 下午3:08:04
 * 
 */
@Service
public class UserNotificationServiceImpl implements UserNotificationService {
	@Resource
	private UserNotificationMapper notificationMapper;

	/**
	 * insert userNotification
	 * 
	 * @param userNotification
	 * @return
	 */
	@Override
	public int insertUserNotification(UserNotification userNotification) {
		return notificationMapper.insert(userNotification);
	}

	/**
	 * 用户通知列表
	 * 
	 * @param <DataModel>
	 * 
	 * @param pageBean
	 * @return
	 */
	@Override
	public List<UserNotificationReturnItem> queryUserNotificationList(PageBean pageBean) {
		LinkedList<UserNotificationReturnItem> returnItems = Lists.newLinkedList();
		List<UserNotificationQuery> queryResult = notificationMapper.queryUserNotificationList(pageBean);
		queryResult.parallelStream().peek(query -> {
			UserNotificationReturnItem returnItem = new UserNotificationReturnItem();
			DataCondition<DataModel> dataCondition = new DataCondition<>();
			/*
			 * actionType : 操作分类 1评论说说或专辑故事 2点赞 3打赏 4转发 5回复评论
			 * businessType : 业务分类 1说说相关 2专辑故事相关
			 */
			Integer actionType = query.getActionType(), businessType = query.getBusinessType();
			
			returnItem.setActionType(actionType);
			dataCondition.setBusinessType(businessType);
			if (actionType == 1) {
				dataCondition.setData(JsonUtil.toObject(query, CommentData.class));
			} else if (actionType == 4) {
				Integer talkId = query.getBusinessId();
				RepublishTalkData republishTalkData = notificationMapper.queryTalkRelatedInfoByTalkId(talkId);
				dataCondition.setData(republishTalkData.setFields(republishTalkData, query));
			} else if (actionType == 5) {
				dataCondition.setData(JsonUtil.toObject(query, RecommentData.class));
			} else {
				dataCondition.setData(JsonUtil.toObject(query, PraiseAndRewardData.class));
			}
			returnItems.addLast(returnItem);
		});
		return returnItems;
	}

}
