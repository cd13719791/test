package com.moyou.moyouRms.dao.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.statistics.NewUser;
import com.moyou.moyouRms.model.statistics.NewUserResult;
import com.moyou.moyouRms.model.user.FakeUser;
import com.moyou.moyouRms.model.user.User;

public interface UserMapper {
	int deleteByPrimaryKey(Integer userId);

	int insert(User record);

	int insertSelective(User record);

	User selectByPrimaryKey(Integer userId);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);

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
	 * 查询微信 qq 注册比例
	 * 
	 * @return
	 */
	Map<String, Object> getUserCountByQQWEIXIN();

	/**
	 * 查询假用户，用于群运营，选择群主
	 */
	List<User> queryFakeUser();

	/**
	 * 查询单聊假用户数量
	 * 
	 * @return
	 */
	Integer queryFakeUserForIM();

	List<User> queryFakeUserByCity(@Param("city") String city);

	/**
	 * 查询用户详细统计数据
	 */
	Map<String, String> queryUserDetailDate(Integer userId);

	/**
	 * 查询假用户，用于群运营，选择群主分页
	 */
	List<FakeUser> queryFakeUserList(PageBean pagebean);

	/**
	 * 查询所有真实用户的userId
	 * 
	 * @return
	 */
	List<Integer> queryReallyUserId();

	User queryFakeUserLimit1();

	/**
	 * 查询所有假用户
	 * 
	 * @return
	 */
	List<User> queryAllFakeUser();

	String selectDeviceIdByUserId(@Param("userId") Integer userId);

	int updateByDeviceId(User userCondition);

	List<NewUserResult> queryUserCountList(NewUser record);

	/**
	 * 查询所有的假用户
	 */
	List<User> selectAllFakeUserForPush();

	/**
	 * 根据性别查询假用户
	 * 
	 * @param sex
	 * @return
	 */
	User queryFakeUserBySex(@Param("sex") Integer sex);

	/**
	 * 检查是否是主播 0不是 1是
	 * 
	 * @param userId
	 * @return
	 */
	Integer checkIsLiveShower(Integer userId);
}
