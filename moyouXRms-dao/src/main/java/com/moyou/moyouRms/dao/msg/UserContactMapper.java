package com.moyou.moyouRms.dao.msg;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.msg.UserContact;
import com.moyou.moyouRms.model.msg.UserContactSqlResult;

public interface UserContactMapper {
	int deleteByPrimaryKey(String id);

	int insert(UserContact record);

	int insertSelective(UserContact record);

	UserContact selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(UserContact record);

	int updateByPrimaryKey(UserContact record);

	/**
	 * 查询消息联系人列表
	 * 
	 * @param pageBean
	 * @return
	 */
	List<UserContactSqlResult> queryUserContactList(PageBean pageBean);

	/**
	 * 查询聊天相关信息
	 * 
	 * @param userId
	 * @param queryUserIds
	 * @return
	 */
	List<Map<String, Object>> queryUserChatInfo(
			@Param(value = "userId") String userId,
			@Param(value = "queryUserIds") List<String> queryUserIds);

	/**
	 * 查询手机联系人
	 * 
	 * @param userId
	 * @param queryUserIds
	 * @return
	 */
	List<Map<String, Object>> queryPhoneContact(
			@Param(value = "userId") String userId,
			@Param(value = "phoneList") List<String> phoneList);
}