package com.moyou.moyouRms.dao.notification;

import java.util.List;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.notification.RepublishTalkData;
import com.moyou.moyouRms.model.notification.UserNotification;
import com.moyou.moyouRms.model.notification.UserNotificationQuery;

public interface UserNotificationMapper {
	/**
	 * insert userNotification
	 * 
	 * @param userNotification
	 * @return
	 */
	int insert(UserNotification userNotification);

	/**
	 * 根据故事 id 查询 userNotification
	 * 
	 * @param diaryId
	 * @return
	 */
	UserNotification queryUserNotificationByDiaryId(Integer diaryId);

	/**
	 * 根据故事评论 id 查询 userNotification
	 * 
	 * @param parentId
	 * @return
	 */
	UserNotification queryUserNotificationByDiaryCommentId(Integer parentId);

	/**
	 * 根据说说 id 查询 userNotification
	 * 
	 * @param talkId
	 * @return
	 */
	UserNotification queryUserNotificationByTalkId(Integer talkId);

	/**
	 * 根据说说评论 id 查询 userNotification
	 * 
	 * @param parentId
	 * @return
	 */
	UserNotification queryUserNotificationByTalkCommentId(Integer parentId);

	/**
	 * 用户通知
	 * 
	 * @param pageBean
	 * @return
	 */
	List<UserNotificationQuery> queryUserNotificationList(PageBean pageBean);

	/**
	 * 根据转发说说的id查询创建者的id
	 * 
	 * @param relateTalkId
	 * @return
	 */
	Integer queryUserId(Integer relateTalkId);

	/**
	 * 查询转发说说相关信息
	 * 
	 * @param talkId
	 * @return
	 */
	RepublishTalkData queryTalkRelatedInfoByTalkId(Integer talkId);

}