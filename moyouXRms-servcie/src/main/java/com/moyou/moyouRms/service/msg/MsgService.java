package com.moyou.moyouRms.service.msg;

import java.util.List;
import java.util.Map;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.jpush.PushEntity;
import com.moyou.moyouRms.model.msg.MsgIndexReturn;
import com.moyou.moyouRms.model.msg.MsgSystem;
import com.moyou.moyouRms.model.msg.MsgUserComment;
import com.moyou.moyouRms.model.msg.MsgUserConcern;
import com.moyou.moyouRms.model.msg.MsgUserContact;
import com.moyou.moyouRms.model.msg.PhoneContactInfo;
import com.moyou.moyouRms.model.msg.UserContact;
import com.moyou.moyouRms.model.msg.UserContactInfo;
import com.moyou.moyouRms.model.msg.UserContactReturn;

public interface MsgService {

	/**
	 * 判断是否需要覆盖推送
	 * 
	 * @param pushEntity
	 * @return 数据库查询的极光推送回调 id , 如果未查询到则返回 ""
	 */
	boolean checkWetherNeedOverride(PushEntity pushEntity);

	/**
	 * 查询极光推送回调 id
	 * 
	 * @param pushEntity
	 * @return 数据库查询的极光推送回调 id , 如果未查询到则返回 ""
	 */
	String queryJpushMsgId(PushEntity pushEntity);

	/**
	 * 插入关注推送消息
	 * 
	 * @param msgEntity
	 * @param jpushMsgId
	 * @param aliases
	 * @return
	 */
	int insertMsgUserConcernList(MsgUserConcern msgEntity, List<String> aliases);

	/**
	 * 插入评论推送消息
	 * 
	 * @param msgEntity
	 * @param jpushMsgId
	 * @param aliases
	 * @return
	 */
	int insertMsgUserCommentList(MsgUserComment msgEntity, List<String> aliases);

	/**
	 * 插入添加联系人好友消息
	 * 
	 * @param msgEntity
	 * @param aliases
	 * @return
	 */
	int insertMsgUserContactList(MsgUserContact msgEntity, List<String> aliases);

	/**
	 * 更新关注覆盖推送
	 * 
	 * @param pushEntity
	 * @param jpushMsgId
	 * @return
	 */
	int updateMsgConcernByJpushMsgIdAndSendUserId(MsgUserConcern msgUserConcern);

	/**
	 * 更新联系人好友覆盖推送
	 * 
	 * @param pushEntity
	 * @param jpushMsgId
	 * @return
	 */
	int updateMsgContactByJpushMsgIdAndSendUserId(MsgUserContact msgUserContact);

	/**
	 * 插入系统消息
	 * 
	 * @param msgSystem
	 * @return
	 */
	int insertMsgSystem(MsgSystem msgSystem);

	/**
	 * 插入推送消息操作
	 * 
	 * @param pushEntity
	 * @return
	 */
	int insertMsgOperation(PushEntity pushEntity);

	/**
	 * 更新推送消息操作
	 * 
	 * @param pushEntity
	 * @return
	 */
	int updateMsgOperation(PushEntity pushEntity);

	/**
	 * 确认添加消息联系人好友
	 * 
	 * @param userContact
	 * @return
	 */
	int addUserContact(UserContact userContact);

	/**
	 * 消息联系人列表
	 * 
	 * @param params
	 * @return
	 */
	List<UserContactReturn> queryUserContactList(Map<String, Object> params);

	/**
	 * 消息首页
	 * 
	 * @param userId
	 * @return
	 */
	MsgIndexReturn queryMsgIndex(String userId);

	/**
	 * 关注消息列表
	 * 
	 * @param pageBean
	 * @return
	 */
	List<Map<String, Object>> queryConcernMsgList(PageBean pageBean);
	
	/**
	 * 评论消息列表
	 * 
	 * @param pageBean
	 * @return
	 */
	List<Map<String, Object>> queryCommentMsgList(PageBean pageBean);
	
	/**
	 * 系统消息列表
	 * 
	 * @param pageBean
	 * @return
	 */
	List<Map<String, Object>> querySystemMsgList(PageBean pageBean);
	
	/**
	 * 查询聊天相关信息
	 * 
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> queryUserChatInfo(UserContactInfo params);
	
	/**
	 * 删除消息联系人好友
	 * 
	 * @param relationId
	 * @return
	 */
	int deleteUserContact(String relationId);
	
	/**
	 * 查询手机联系人
	 * 
	 * @param params
	 * @return
	 */
	Map<String, Object> queryPhoneContact(PhoneContactInfo params);
}
