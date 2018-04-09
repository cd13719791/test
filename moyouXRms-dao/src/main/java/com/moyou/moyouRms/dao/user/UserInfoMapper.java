package com.moyou.moyouRms.dao.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.liveshow.UserGoldConvertModel;
import com.moyou.moyouRms.model.notification.PushObject;
import com.moyou.moyouRms.model.user.UserInfo;
import com.moyou.moyouRms.model.user.UserMsgInfo;

public interface UserInfoMapper {
	int insert(UserInfo record);

	int insertSelective(UserInfo record);

	UserInfo selectUserInfoByUserId(UserInfo userInfo);

	/**
	 * 根据条件参数获取用户Id
	 * 
	 * @param paramMap
	 * @return
	 */
	List<UserInfo> selectUserIdByMap(Map<String, Object> paramMap);

	/**
	 * 查询假用户的ID以及环信ID
	 * 
	 * @return
	 */
	List<UserInfo> queryUserIdAndPushChatId(Integer userId);

	/**
	 * 根据用户ID获取用户的环信ID
	 * 
	 * @param userId
	 * @return
	 */
	String queryPushChatId(Integer userId);

	List<UserInfo> queryCrowdUserAndPushChatId(Integer userId);

	/**
	 * 推送信息查询
	 * 
	 * @param sendUserId
	 *            发送者用户 id
	 * @param receiveUserId
	 *            接受者用户 id
	 * @return
	 */
	PushObject queryPushObject(@Param("sendUserId") Integer sendUserId,
			@Param("receiveUserId") Integer receiveUserId);

	/**
	 * 根据用户Id查询用户昵称
	 * 
	 * @param userId
	 * @return
	 */
	UserInfo queryUserNickName(Integer userId);

	Integer updateUserLanLatByUserId(UserInfo userInfo);

	/**
	 * 查询用户头像和昵称
	 * 
	 * @param userId
	 * @return
	 */
	UserInfo selectUsreNickNameAndAvatar(Integer userId);

	/**
	 * 修改用户头像
	 * 
	 * @return
	 */
	int updateUserAvatar(UserInfo userInfo);

	/**
	 * 查询所有被推荐用户
	 * 
	 * @param pb
	 * @return
	 */
	List<UserInfo> queryUserRecommend(PageBean pb);

	String queryContaninsMoyouId(@Param("moyouId") String moyouId);

	/**
	 * 查询所有真用户的环信ID
	 * 
	 * @return
	 */
	List<UserInfo> queryUserPushChatId();

	/**
	 * 查询所有假用户性别女以及ID
	 * 
	 * @return
	 */
	List<UserInfo> querySexWoman();

	/**
	 * 查询所有假用户性别男以及ID
	 * 
	 * @return
	 */
	List<UserInfo> queryMan();

	List<UserInfo> queryUserMoyouIdCount2();

	List<UserInfo> queryByMoyouId(String moyouId);

	int updateMoyouId(UserInfo u);

	List<UserInfo> selectFakeUserNotInRelat();

	UserMsgInfo selectUsreDetailInfo(Integer userId);

	/**
	 * 查询假用户的ID以及环信ID 群专用假用户 群用户 1000 个的id 正式服 28394 -27392 测试服 10975 -11974 and
	 * u.user_id <= 11974 and u.user_id >= 10975
	 * 
	 * @param sql
	 * @return
	 */
	List<UserInfo> queryUserIdAndPushChatId2(@Param("id") Integer id, @Param("sql") String sql);

	List<UserInfo> queryReallyUserIdAndPushChatId();

	UserInfo queryUserCity(Integer id);

	List<UserInfo> selectFakeUserNotInRelatLimit(@Param("userNumberStart") Integer userNumberStart,
			@Param("userNumberEnd") Integer userNumberEnd, @Param("sql") String sql);

	/**
	 * 
	 * @param deviceId
	 * @return
	 */
	List<UserInfo> selectAllUserByDeviceId(@Param("deviceId") String deviceId);

	List<String> queryReallyUserPushChatId();

	List<UserInfo> selectFakeUserForBiuBiuBiu();

	/**
	 * 查询主播兑换金币必需数据
	 * 
	 * @param userId
	 * @return
	 */
	UserGoldConvertModel selectGoldConvertModel(@Param("userId") Integer userId);
}
