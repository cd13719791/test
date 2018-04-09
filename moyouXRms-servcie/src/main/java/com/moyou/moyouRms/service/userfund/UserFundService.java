package com.moyou.moyouRms.service.userfund;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.systemFund.SystemFund;
import com.moyou.moyouRms.model.userfund.SystemRewardModel;
import com.moyou.moyouRms.model.userfund.UserFund;
import com.moyou.moyouRms.model.userfund.UserFundLog;
import com.moyou.moyouRms.pingxx.entity.ChargeEntity;
import com.moyou.moyouRms.response.ApiResult;

/**
 * @describe 用户资金管理
 * @author liuxinyi
 * @date 2017年2月8日
 * @email liuxinyi@mousns.com
 * @version 1.0.0
 */
public interface UserFundService {
	/**
	 * 用户支付成功后回调
	 * 
	 * @param callbackData
	 */
	void createWebhooksPaySuccess(Map<String, Object> callbackData);

	/**
	 * 用户支付成功后回调
	 * 
	 * @param callbackData
	 *            version : 新版支付成功回调 只用于系统打赏
	 */
	void createWebhooksPaySuccessV2(Map<String, Object> callbackData);

	/**
	 * 添加或更新修改用户资金
	 * 
	 * @param userId
	 * @param money
	 *            待修改的金额 参数传递 正负数 单位元
	 */
	boolean addAndUpdateUserFund(int userId, BigDecimal yuan);

	/**
	 * 添加或更新修改用户资金
	 * 
	 * @param userId
	 * @param penny
	 *            待修改的金额 参数传递 正负数 单位分
	 */
	boolean addAndUpdateUserFund(int userId, int penny);

	/**
	 * 获取实体
	 * 
	 * @param id
	 * @return
	 */
	UserFund getUserFundById(int id);

	UserFund getUserFundByUserId(int userId);

	UserFund getUserFundByUserIdLock(int userId);

	/**
	 * 添加交易
	 * 
	 * @param log
	 */
	boolean addFundLog(UserFundLog log);

	/**
	 * 发起交易
	 * 
	 * @param log
	 * @return
	 */
	ChargeEntity createCharge(UserFundLog log);

	/**
	 * (根据用户Id获取用户的金币和余额)
	 * 
	 * @param userId
	 * @return
	 */
	UserFund queryUserFundByUserId(Integer userId);

	/**
	 * 用户提现
	 * 
	 * @param userFundlog
	 * @return
	 */
	ApiResult insertWithdrawDeposit(UserFundLog userFundlog, HttpServletRequest request);

	/**
	 * 系统打赏
	 * 
	 * @param systemRewardModel
	 * @return
	 */
	ApiResult insertSystemReward(SystemRewardModel systemRewardModel) throws Exception;

	/**
	 * 查询系统统计信息
	 * 
	 * @returngetIdentifyingCode
	 */
	Map<String, Object> selectRewardSystemInfoCount();

	Map<String, Object> getIdentifyingCode(String phone, String accountName, Integer money);

	/**
	 * 充值
	 * 
	 * @param param
	 * @param httpServletRequest
	 * @param loginName
	 * @return
	 */
	int insertSystemFund(Map<String, Object> param, HttpServletRequest httpServletRequest,
			String loginName);

	/**
	 * 初始化充值记录
	 * 
	 * @param pb
	 * @return
	 */
	List<SystemFund> selectSystemFundLog(PageBean pb);

	/**
	 * 获取公司系统账户的所有金额
	 * 
	 * @return
	 */
	BigDecimal getCompanySysFund();

	/**
	 * 系统返回金币
	 * 
	 * @param userFund
	 * @param s
	 * @param integer
	 * @return
	 */
	int updateUserGold(UserFund userFund, short s, Integer integer, HttpServletRequest req);

	/**
	 * 消耗余额 购买金币
	 * 
	 * @param userId
	 *            用户id
	 * @param modeId
	 *            规则id
	 * @return
	 */
	ApiResult inserUserGold(Integer userId, Integer modeId);

	/**
	 * 执行系统打赏
	 * 
	 * @DATE 2017年8月29日14:05:20
	 * 
	 * @return
	 */
	int insertSystemRewardV2ForSchedule() throws Exception;

	/**
	 * 金币兑换
	 * 
	 * @param userId
	 * @param gold
	 * @param httpServletRequest
	 * @return
	 */
	ApiResult updateGoldToMoney(Integer userId, Integer gold, HttpServletRequest httpServletRequest);

	/**
	 * 获取充值总额
	 * 
	 * @return
	 */
	Map<String, Object> selectSystemFundSum();

}
