package com.moyou.moyouRms.model.userfund;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述:t_user_fund_log表的实体类
 * 
 * @version
 * @author: liuxinyi
 * @创建时间: 2017-01-16
 */
public class UserFundLog {
	public static final Integer AUDIT_DEFAULT = 0;
	public static final Integer AUDIT_ASK_FOR = 1;// 申请提现
	public static final Integer AUDIT_SUCCESS = 2;// 提现成功
	public static final Integer AUDIT_ERRO = 3;// 提现失败
	public static final Integer PINGXX_ERRO = 1;// 提现失败
	public static final Integer PINGXX_SUCCESS = 2;// 提现成功
	private String nickname;
	private String avatar;
	private String payUserName;// 支付人名字
	private String receiveUserName;// 接收人名字
	private String month;// 数据当前月份
	private String monthDay;// 数据当前月日
	private String hourMinSecond;// 数据当前时分秒
	private String extnd;// 扩展字段 存取json数据
	private Map<String, Object> param;// 封装json数据
	private String moyouId;
	private Integer payType;// 1微信app2支付宝app
	private Integer searchUserid;// 用于检索，收入和支出会生成2条记录，收入时为收入用户id支出时为支出用户id
	private Integer searchCategory;// 搜索模块分类1提现2打赏3红包4金币
	private Integer balance;// 余额
	private Integer rewardId;// 系统打赏记录id
	private Integer modePlusType;// '1活动2直播',

	public Integer getModePlusType() {
		return modePlusType;
	}

	public void setModePlusType(Integer modePlusType) {
		this.modePlusType = modePlusType;
	}

	public Integer getRewardId() {
		return rewardId;
	}

	public void setRewardId(Integer rewardId) {
		this.rewardId = rewardId;
	}

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	public Integer getSearchCategory() {
		return searchCategory;
	}

	public void setSearchCategory(Integer searchCategory) {
		this.searchCategory = searchCategory;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	private String reasonText;// 不通过理由

	public String getReasonText() {
		return reasonText;
	}

	public void setReasonText(String reasonText) {
		this.reasonText = reasonText;
	}

	public String getMoyouId() {
		return moyouId;
	}

	public void setMoyouId(String moyouId) {
		this.moyouId = moyouId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Map<String, Object> getParam() {
		return param;
	}

	public void setParam(Map<String, Object> param) {
		this.param = param;
	}

	public String getExtnd() {
		return extnd;
	}

	public void setExtnd(String extnd) {
		this.extnd = extnd;
	}

	public String getHourMinSecond() {
		return hourMinSecond;
	}

	public void setHourMinSecond(String hourMinSecond) {
		this.hourMinSecond = hourMinSecond;
	}

	public String getMonthDay() {
		return monthDay;
	}

	public void setMonthDay(String monthDay) {
		this.monthDay = monthDay;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getPayUserName() {
		return payUserName;
	}

	public void setPayUserName(String payUserName) {
		this.payUserName = payUserName;
	}

	public String getReceiveUserName() {
		return receiveUserName;
	}

	public void setReceiveUserName(String receiveUserName) {
		this.receiveUserName = receiveUserName;
	}

	/**
	 * '审核0申请提现1审核中2审核成功3审核失败',
	 */
	private Integer audit;

	public Integer getAudit() {
		return audit;
	}

	public void setAudit(Integer audit) {
		this.audit = audit;
	}

	/**
	 * 加密用户token
	 */
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * 主键自增
	 */
	private Integer id;

	/**
	 * 支付人id
	 */
	private Integer payUserId;

	/**
	 * 接收人id
	 */
	private Integer receiveUserId;

	/**
	 * 用户收支金额，单位分
	 */
	private Integer userFund;

	/**
	 * 模块类型
	 */
	private Short modeType;

	/**
	 * 模块id
	 */
	private Integer modeId;

	/**
	 * 交易订单号
	 */
	private String tradeNumber;

	/**
	 * 第三方ping++交易数据主键id
	 */
	private String pingxxId;
	/**
	 * userFundLog.setPingxxState(2);//
	 * ping++支付状态0发起订单1手机或pc支付成功，不一定真成功2第三方异步通知成功,真正支付成功或本应用内支付交易成功
	 */
	private int pingxxState;

	/**
	 * 客户端ip
	 */
	private String clientIp;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 主键自增
	 * 
	 * @return id 主键自增
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 主键自增
	 * 
	 * @param id
	 *            主键自增
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 支付人id
	 * 
	 * @return pay_user_id 支付人id
	 */
	public Integer getPayUserId() {
		return payUserId;
	}

	/**
	 * 支付人id
	 * 
	 * @param payUserId
	 *            支付人id
	 */
	public void setPayUserId(Integer payUserId) {
		this.payUserId = payUserId;
	}

	/**
	 * 接收人id
	 * 
	 * @return receive_user_id 接收人id
	 */
	public Integer getReceiveUserId() {
		return receiveUserId;
	}

	/**
	 * 接收人id
	 * 
	 * @param receiveUserId
	 *            接收人id
	 */
	public void setReceiveUserId(Integer receiveUserId) {
		this.receiveUserId = receiveUserId;
	}

	/**
	 * 用户收支金额，单位分
	 * 
	 * @return user_fund 用户收支金额，单位分
	 */
	public Integer getUserFund() {
		return userFund;
	}

	/**
	 * 用户收支金额，单位分
	 * 
	 * @param userFund
	 *            用户收支金额，单位分
	 */
	public void setUserFund(Integer userFund) {
		this.userFund = userFund;
	}

	/**
	 * 模块类型
	 * 
	 * @return mode_type 模块类型
	 */
	public Short getModeType() {
		return modeType;
	}

	/**
	 * 模块类型
	 * 
	 * @param modeType
	 *            模块类型
	 */
	public void setModeType(Short modeType) {
		this.modeType = modeType;
	}

	/**
	 * 模块id
	 * 
	 * @return mode_id 模块id
	 */
	public Integer getModeId() {
		return modeId;
	}

	/**
	 * 模块id
	 * 
	 * @param modeId
	 *            模块id
	 */
	public void setModeId(Integer modeId) {
		this.modeId = modeId;
	}

	/**
	 * 交易订单号
	 * 
	 * @return trade_number 交易订单号
	 */
	public String getTradeNumber() {
		return tradeNumber;
	}

	/**
	 * 交易订单号
	 * 
	 * @param tradeNumber
	 *            交易订单号
	 */
	public void setTradeNumber(String tradeNumber) {
		this.tradeNumber = tradeNumber == null ? null : tradeNumber.trim();
	}

	/**
	 * 第三方ping++交易数据主键id
	 * 
	 * @return pingxx_id 第三方ping++交易数据主键id
	 */
	public String getPingxxId() {
		return pingxxId;
	}

	/**
	 * 第三方ping++交易数据主键id
	 * 
	 * @param pingxxId
	 *            第三方ping++交易数据主键id
	 */
	public void setPingxxId(String pingxxId) {
		this.pingxxId = pingxxId == null ? null : pingxxId.trim();
	}

	/**
	 * 客户端ip
	 * 
	 * @return client_ip 客户端ip
	 */
	public String getClientIp() {
		return clientIp;
	}

	/**
	 * 客户端ip
	 * 
	 * @param clientIp
	 *            客户端ip
	 */
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp == null ? null : clientIp.trim();
	}

	/**
	 * 创建时间
	 * 
	 * @return create_time 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间
	 * 
	 * @param createTime
	 *            创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the pingxxState
	 */
	public int getPingxxState() {
		return pingxxState;
	}

	/**
	 * 审核时间
	 * 
	 * @param createTime
	 *            审核时间
	 */
	private Date auditTime;

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	/**
	 * @param pingxxState
	 *            the pingxxState to set
	 */
	public void setPingxxState(int pingxxState) {
		this.pingxxState = pingxxState;
	}

	/**
	 * @return the payType
	 */
	public Integer getPayType() {
		return payType;
	}

	/**
	 * @param payType
	 *            the payType to set
	 */
	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	/**
	 * @return the searchUserid
	 */
	public Integer getSearchUserid() {
		return searchUserid;
	}

	/**
	 * @param searchUserid
	 *            the searchUserid to set
	 */
	public void setSearchUserid(Integer searchUserid) {
		this.searchUserid = searchUserid;
	}

	public static Map<String, Object> instansToResult(UserFundLog userFundLog) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("time", userFundLog.getCreateTime());
		map.put("gold", userFundLog.getUserFund());
		map.put("modeType", userFundLog.getModeType());
		map.put("money", userFundLog.getParam() == null ? null : userFundLog.getParam()
				.get("money"));
		return map;

	}
}