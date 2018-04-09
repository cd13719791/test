package com.moyou.moyouRms.service.userfund;

import java.util.List;
import java.util.Map;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.liveshow.UserRecordListResult;
import com.moyou.moyouRms.model.po.system.account.Account;
import com.moyou.moyouRms.model.userfund.SystemRewardModel;
import com.moyou.moyouRms.model.userfund.UserFundLog;
import com.moyou.moyouRms.response.ApiResult;

public interface UserFundLogService {
	int deleteByPrimaryKey(Integer id);

	int insert(UserFundLog record);

	int insertSelective(UserFundLog record);

	UserFundLog selectByPrimaryKey(Integer id);

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

	/**
	 * 根据用户id查询用户 被后台修改的金币修改记录
	 * 
	 * @param userFundLog
	 *            pay_user_id=receive_user_id state
	 * @return List<UserFundLog>
	 */
	List<UserFundLog> selectUserFundLogBySystemUpdate(PageBean pb);

	/**
	 * 查询用户打赏记录和被打赏记录
	 * 
	 * @param pageBean
	 * @return
	 */
	List<UserFundLog> selectUserFundLogReward(PageBean pageBean);

	/**
	 * 提现审核表 初始化提现记录
	 * 
	 * @param pb
	 * @return
	 */
	List<UserFundLog> selectUserFundLogByState(PageBean pb);

	/**
	 * 后台交易记录初始化
	 * 
	 * @param pb
	 * @return
	 */
	List<UserFundLog> queryUserFundLogByPageBean(PageBean pb);

	/**
	 * 用户提现审核 通过则开始微信提现
	 * 
	 * @param userFundLogId
	 * @param audit
	 * @param ac
	 * @param string
	 * @return
	 */
	ApiResult updateUserFundState(int userFundLogId, int audit, Account ac, String reasonText);

	/**
	 * 资金池统计
	 * 
	 * @return
	 */
	Map<String, Object> queryGoldAndFundCount();

	int updateUserFundLogRemark(int id, String msg);

	/**
	 * 提现统计
	 * 
	 * @return
	 */
	Map<String, Object> queryUserFundLogCount();

	List<SystemRewardModel> selectSystemRewardLog(PageBean pb);

	Map<String, Object> selectSystemRewardLogCount();

	/**
	 * 根据Id查询系统打赏记录
	 * 
	 * @param map
	 * @return
	 */
	SystemRewardModel selectSystemRewardLogById(Map<String, Object> map);

	int updateSystemRewardLogById(Map<String, Object> map);

	Map<String, Object> selectRewardCountNumber(Map<String, Object> map);

	UserFundLog selectUserFundLogByTypeAndId(short value, Integer id);

	List<UserFundLog> selectActivityLog(PageBean pb);

	/**
	 * 初始化直播收益
	 * 
	 * @param pageBean
	 * @return
	 */
	List<UserFundLog> selectUserFundLogForZhiBo(PageBean pageBean);

	/**
	 * 选择需要插入的用户流水 ===>每日定时执行插入用户流水
	 * 
	 * @return
	 */
	List<UserFundLog> selectUserFundLogForRegulation();

	/**
	 * 查询直播间观众观看总额
	 * 
	 * @param id
	 * @return
	 */
	Integer selectWacthLiveShowEarn(Integer id);
	
	/**
	 * 观众观看主播直播总消费
	 * @param id
	 * @return
	 */
	Integer selectUserWatchLiveAllExpense(Integer id,Integer userId);

	/**
	 * 查看观众详情
	 * @param modeId
	 * @return
	 */
	List<UserRecordListResult> selectUserFundLogServiceByLiveModeId(Integer modeId);
}
