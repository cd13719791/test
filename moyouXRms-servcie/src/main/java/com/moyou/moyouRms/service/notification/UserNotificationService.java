package com.moyou.moyouRms.service.notification;

import java.util.List;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.notification.UserNotification;
import com.moyou.moyouRms.model.notification.UserNotificationReturnItem;

/**
 * 通知
 * 
 * @author PzC.
 * @time 2017年3月22日 上午9:59:18
 * 
 */
public interface UserNotificationService {
	
	/**
	 * insert userNotification
	 * 
	 * @param userNotification
	 * @return
	 */
	int insertUserNotification(UserNotification userNotification);
	
	/**
	 * 用户通知列表
	 * 
	 * @param pageBean
	 * @return
	 */
	List<UserNotificationReturnItem> queryUserNotificationList(PageBean pageBean);
}
