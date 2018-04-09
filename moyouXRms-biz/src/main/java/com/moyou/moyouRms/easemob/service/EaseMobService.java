package com.moyou.moyouRms.easemob.service;

import java.util.List;
import java.util.Map;

import com.easemob.server.comm.body.ChatGroupBody;
import com.easemob.server.comm.body.TextMessageBody;
import com.moyou.moyouRms.easemob.entity.EaseMobUserInfo;
import com.moyou.moyouRms.easemob.entity.SingleChatMsg;
import com.moyou.moyouRms.easemob.entity.SingleChatMsgExtend;

/**
 * @describe 环信服务接口
 * @author liuxinyi
 * @date 2017年1月12日
 * @email liuxinyi@mousns.com
 * @version 1.0.0
 */
public interface EaseMobService {
	/**
	 * 获取token
	 * 
	 * @param type
	 *            手机类型
	 * @param ip
	 * @return
	 */
	String getEaseMobToken(int mobiletype, String ip);

	/**
	 * 添加用户
	 * 
	 * @param username
	 * @param password
	 *            暗文密码
	 * @param nickname
	 * @return
	 */
	boolean addUser(String username, String password, String nickname);

	/**
	 * 获取用户
	 * 
	 * @param username
	 * @return
	 */
	Map<String, Object> getUser(String username);

	/**
	 * 获取环信自定义规则的用户名、密码
	 * 
	 * @param username
	 * @param password
	 *            默认规则用户名MD5
	 * @return
	 */
	EaseMobUserInfo getUsernamePassword(String username, String password);

	/**
	 * 用户是否存在
	 * 
	 * @param username
	 * @return
	 */
	boolean isExistUser(String username);

	/**
	 * 删除用户 注:删除用户会同事删除该用户创建的所有群组
	 * 
	 * @param username
	 * @return
	 */
	Map<String, Object> removeUser(String username);

	/**
	 * 创建群组
	 * 
	 * @param cc
	 * @return 群组id
	 */
	String createChatGroup(ChatGroupBody cc);

	/**
	 * 加入群组
	 * 
	 * @param roomId
	 * @param username
	 * @return
	 */
	boolean addUserToChatGroup(String roomId, String username);

	boolean bacthUserToChatGroup(String roomId, List<String> usernames);

	/**
	 * 退出群组
	 * 
	 * @param roomId
	 * @param username
	 * @return
	 */
	boolean removeUserFromChatGroup(String roomId, String username);

	/**
	 * 群组减人[批量]
	 * 
	 * @param roomId
	 * @param username
	 * @return
	 */
	boolean removeBatchUsersFromChatGroup(String roomId, List<String> usernames);

	/**
	 * 用户账号禁用 <br>
	 * POST
	 * 
	 * @param userName
	 *            用戶名或用戶ID
	 * @return
	 */
	Object deactivateIMUser(String userName);

	/**
	 * 用户账号解禁 <br>
	 * POST
	 * 
	 * @param userName
	 *            用戶名或用戶ID
	 * @return
	 */
	Object activateIMUser(String userName);

	/**
	 * 强制用户下线 <br>
	 * GET
	 * 
	 * @param userName
	 *            用戶名或用戶ID
	 * @return
	 */
	Object disconnectIMUser(String userName);

	/**
	 * 向用户发送消息
	 * 
	 * @param message
	 * @return
	 */
	boolean sendMessage(TextMessageBody textMessageBody);

	/**
	 * 修改群信息
	 * 
	 * @param message
	 * @return
	 */
	boolean updateGroup(String owerId, String groupId, String GroupName, String desc, Integer max);

	/**
	 * 修改群信息
	 * 
	 * @param message
	 * @return
	 */
	boolean transferChatGroupOwner(String groupId, String onewer);

	/**
	 * 发送单聊消息
	 * 
	 * @param userId
	 *            发送的环信用户id
	 * @param singleChatMsg
	 *            消息内容
	 * @param singleChatMsgExtend
	 *            消息扩展字段
	 * @param targetUserId
	 *            接收的环信用户id 最好不大于20个
	 * @return
	 */
	boolean sendSingleChatMsg(String userId, SingleChatMsg singleChatMsg, SingleChatMsgExtend singleChatMsgExtend,
			String... targetUserId);

}
