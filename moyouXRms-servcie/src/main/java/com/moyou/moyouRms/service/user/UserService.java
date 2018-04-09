package com.moyou.moyouRms.service.user;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.po.system.account.Account;
import com.moyou.moyouRms.model.statistics.NewUser;
import com.moyou.moyouRms.model.statistics.NewUserResult;
import com.moyou.moyouRms.model.user.FakeUser;
import com.moyou.moyouRms.model.user.Page;
import com.moyou.moyouRms.model.user.User;
import com.moyou.moyouRms.model.user.UserActiveInfo;
import com.moyou.moyouRms.model.user.UserInfo;
import com.moyou.moyouRms.model.user.UserList;
import com.moyou.moyouRms.model.user.UserRegCondition;
import com.moyou.moyouRms.model.user.UserReturnBaseInfo;
import com.moyou.moyouRms.model.userRecommend.UserRecommend;
import com.moyou.moyouRms.response.ApiResult;

public interface UserService {

	/**
	 * 查询所有用户
	 * 
	 * @param userId
	 * @return
	 */
	List<UserList> queryAllUsers(PageBean pb);

	/**
	 * 初始化所有推荐用户
	 * 
	 * @param pb
	 * @return
	 */
	List<UserList> queryUserRecommend(PageBean pb);

	/**
	 * 每天新增用户
	 * 
	 * @param createTime
	 * @return
	 */
	Integer selectNewUserCount();

	/**
	 * 在线用户数量
	 * 
	 * @param createTime
	 * @return
	 */
	Integer selectOnlineUserCount();

	/**
	 * 根据参数获取用户数量
	 * 
	 * @param paramMap
	 * @return
	 */
	Integer getUserCountByParams(Map<String, Object> paramMap);

	/**
	 * 创建用户
	 * 
	 * @param userCondition
	 * @return
	 */
	Integer addUser(User userCondition);

	/**
	 * 创建用户
	 * 
	 * @param userCondition
	 * @return
	 */
	Integer updateByPrimaryKeySelective(User userCondition);

	/**
	 * 获取用户id
	 * 
	 * @param token
	 *            登录令牌
	 * @return
	 */
	int getUserIdByToken(String token);

	/**
	 * 查询假用户，用于群运营，选择群主 只有1000人
	 */
	List<User> queryFakeUser();

	List<User> queryFakeUserByCity(String city);

	/**
	 * 查询所有的假用户
	 * 
	 * @return
	 */
	List<User> queryAllFakeUser();

	/**
	 * 
	 * @param userConditionList
	 * @param accountId
	 * @param type
	 *            根据不同的类型做处理0随机登录的小时 1随机登录的分钟
	 * @return
	 */
	List<UserReturnBaseInfo> addUsers(List<UserRegCondition> userConditionList, String accountId,
			int type);

	/**
	 * 
	 * @param userId
	 * @param 查询用户详细数据
	 *            包括金币信息
	 * @return
	 */
	Map<String, String> queryUserDetailedData(int userId);

	UserReturnBaseInfo addUser(UserRegCondition regCondition);

	/**
	 * 添加用户活跃表数据
	 * 
	 * @param activeInfo
	 * @return
	 */
	int addUserActiveInfo(UserActiveInfo activeInfo);

	/**
	 * 在数据库中查询出假用户ID集合
	 * 
	 * @param sex
	 *            用户性别 0男 1女 ，rediskey
	 *            用户集合在redis中的key，repeatUserRedisKey用户防重复key
	 * @param userListRepeat
	 *            去除重复的user
	 * @return
	 */
	User queryUserId(int sex, String redisKey, String repeatUserRedisKey);

	/**
	 * 修改用户推荐状态
	 * 
	 * @param userId
	 * @return
	 */
	int updateUserRecommend(UserRecommend userId);

	/**
	 * 查询假用户，用于群运营，选择群主分页
	 */
	List<FakeUser> queryFakeUserList(Page page);

	/**
	 * 判断陌友id是否存在
	 * 
	 * @param moyouId
	 * @return
	 */
	boolean isExistMoyouId(String moyouId);

	/**
	 * 查询微信 qq 注册比例
	 * 
	 * @return
	 */
	Map<String, Object> getUserCountByQQWEIXIN();

	/**
	 * 查询假用户，用于群运营，选择群主分页
	 */
	User queryFakeUserList();

	User queryFakeUserLimit1();

	List<NewUserResult> queryUserCountList(NewUser record) throws ParseException;

	ApiResult updateUserGold(Map<String, Object> map, HttpServletRequest httpServletRequest,
			Account account);

	/**
	 * 查询所有假用户的id 和城市
	 * 
	 * @return
	 */
	List<UserInfo> selectFakeUserForBiuBiuBiu();

	User queryFakeUserBySex(Integer sex);
}
