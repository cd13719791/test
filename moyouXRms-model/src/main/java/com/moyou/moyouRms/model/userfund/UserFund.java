package com.moyou.moyouRms.model.userfund;

import java.math.BigDecimal;
import java.util.Date;

import com.moyou.moyouRms.model.BaseModel;
import com.moyou.moyouRms.model.user.BindData;

/**
 * 描述:t_user_fund表的实体类
 * 
 * @version
 * @author: liuxinyi
 * @创建时间: 2017-01-14
 */
public class UserFund extends BaseModel {
	public UserFund() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -972208207444699642L;

	private BindData bindData;

	public BindData getBindData() {
		return bindData;
	}

	public void setBindData(BindData bindData) {
		this.bindData = bindData;
	}

	private String time;

	public UserFund(Integer userId, BigDecimal userFund) {
		super();
		this.userId = userId;
		this.userFund = userFund;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	private String state;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	/**
	 * 主键自增
	 */
	private Integer id;

	/**
	 * 用户id
	 */
	private Integer userId;

	/**
	 * 用户资金
	 */
	private BigDecimal userFund;
	/**
	 * 用户金币
	 */
	private BigDecimal gold;
	private Date updateTime;
	private Integer userType;

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getUserGold() {
		return userGold;
	}

	public void setUserGold(Integer userGold) {
		this.userGold = userGold;
	}

	private String WeiXinNickName;

	/**
	 * 用户金币
	 */
	private Integer userGold;
	private String dateTimes;

	public String getDateTimes() {
		return dateTimes;
	}

	public void setDateTimes(String dateTimes) {
		this.dateTimes = dateTimes;
	}

	public String getWeiXinNickName() {
		return WeiXinNickName;
	}

	public void setWeiXinNickName(String weiXinNickName) {
		WeiXinNickName = weiXinNickName;
	}

	public BigDecimal getGold() {
		return gold;
	}

	public void setGold(BigDecimal gold) {
		this.gold = gold;
	}

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
	 * 用户id
	 * 
	 * @return user_id 用户id
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * 用户id
	 * 
	 * @param userId
	 *            用户id
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * 用户资金
	 * 
	 * @return user_fund 用户资金
	 */
	public BigDecimal getUserFund() {
		return userFund;
	}

	/**
	 * 用户资金
	 * 
	 * @param userFund
	 *            用户资金
	 */
	public void setUserFund(BigDecimal userFund) {
		this.userFund = userFund;
	}

	/**
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime
	 *            the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}