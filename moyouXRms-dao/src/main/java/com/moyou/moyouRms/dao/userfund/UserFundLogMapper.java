package com.moyou.moyouRms.dao.userfund;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.liveshow.UserRecordListResult;
import com.moyou.moyouRms.model.userfund.UserFundLog;

public interface UserFundLogMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(UserFundLog record);

	int insertSelective(UserFundLog record);

	UserFundLog selectByPrimaryKey(Integer id);

	/**
	 * 查询数据加行级锁
	 * 
	 * @param id
	 * @return
	 */
	UserFundLog selectByIdForLock(Integer id);

	int updateByPrimaryKeySelective(UserFundLog record);

	int updateByPrimaryKey(UserFundLog record);

	/**
	 * 修改订单状态
	 * 
	 * @param record
	 * @return
	 */
	int updateTradeState(UserFundLog record);

	int updateTradeStateyByPingId(UserFundLog record);

	/**
	 * 查询用户订单
	 * 
	 * @param userFundLog
	 * @return List<UserFundLog>
	 */
	List<UserFundLog> selectUserFundLogBySelective(PageBean userFundLog);

	/**
	 * 根据用户id查询用户订单
	 * 
	 * @param userFundLog
	 *            pay_user_id=receive_user_id
	 * @return List<UserFundLog>
	 */
	List<UserFundLog> selectUserFundLogByUser(PageBean userFundLog);

	/**
	 * 根据用户id查询用户 1 提现 2打赏 3 红包 4金币
	 * 
	 * @param userFundLog
	 *            pay_user_id=receive_user_id state
	 * @return List<UserFundLog>
	 */
	List<UserFundLog> selectUserFundLogByParam(PageBean userFundLog);

	List<UserFundLog> selectUserFundLogBySystemUpdate(PageBean pb);

	/**
	 * 查询用户打赏与被打赏记录
	 * 
	 * @param pageBean
	 * @return
	 */
	List<UserFundLog> selectUserFundLogReward(PageBean pageBean);

	List<UserFundLog> selectUserFundLogByState(PageBean pb);

	/**
	 * 后台交易记录初始化
	 * 
	 * @param pb
	 * @return
	 */
	List<UserFundLog> queryUserFundLogByPageBean(PageBean pb);

	Map<String, Object> queryGoldAndFundCount();

	/**
	 * 提现统计
	 * 
	 * @return
	 */
	Map<String, Object> queryUserFundLogCount();

	UserFundLog selectUserFundLogByTypeAndId(@Param("value") short value, @Param("id") Integer id);

	List<UserFundLog> selectActivityLog(PageBean pb);

	List<UserFundLog> selectUserFundLogForZhiBo(PageBean pageBean);

	List<UserFundLog> selectUserFundLogForRegulationChongZhi();

	List<UserFundLog> selectUserFundLogForRegulationTiXian();

	List<UserFundLog> selectUserFundLogForRegulationHongBao();

	List<UserFundLog> selectUserFundLogForRegulationDaShang();

	List<UserFundLog> selectUserFundLogForRegulationHuodong();
	
	List<UserRecordListResult> selectUserFundLogServiceByLiveModeId(@Param("modeId")Integer modeId);


	Integer selectUserFundLogByTypeAndIdAndUserId(@Param("value")short value,@Param("id") Integer id, @Param("userId") Integer userId);
}