package com.moyou.moyouRms.model.user;

import java.util.Date;

import com.moyou.moyouRms.model.BaseModel;

public class User extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8892344004911534999L;

	private Integer userId;

	private String account;// 账号
	private String deviceId;// 设备号

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(Integer userId, Integer state) {
		super();
		this.userId = userId;
		this.state = state;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	private String password;// 密码

	private Integer fundState;// 资金状态资金权限 0不可用 1可用

	private Integer state;// 账号状态
	private Integer type;// '0 真实用户 1 假用户',
	public static final int REALY_USER = 0;
	public static final int UNREALY_USER = 1;
	public static final int FRONT_CREATE = 1;// 前台创建
	public static final int ADMIN_CREATE = 2;// 后台创建
	private String avatar;// 用户头像

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	private Integer accountType;// 注册类型
	private String nickname;// 用户昵称
	private String pushChatId;// 环信Id
	private String city;// 城市

	public String getPushChatId() {
		return pushChatId;
	}

	public void setPushChatId(String pushChatId) {
		this.pushChatId = pushChatId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	private Date updateTime;

	private Date createTime;
	public static final Integer FUND_STATE_YES = 1;// 资金可用
	public static final Integer FUND_STATE_NO = 0;// 资金不可用
	public static final Integer STATE_YES = 1;// 账户状态 可用
	public static final Integer STATE_NO = 0;// 账户状态 不可用
	public static final Integer WEIXIN = 1;// 账户状态 不可用
	public static final Integer SHOUJI = 2;// 账户状态 不可用
	public static final Integer QQ = 3;// 账户状态 不可用

	// 在线状态 0离线1在线'
	private int onlineState;

	public int getOnlineState() {
		return onlineState;
	}

	public void setOnlineState(int onlineState) {
		this.onlineState = onlineState;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account == null ? null : account.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public Integer getFundState() {
		return fundState;
	}

	public void setFundState(Integer fundState) {
		this.fundState = fundState;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof User) {
			User u = (User) obj;
			return (userId.equals(u.userId));
		}
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return userId.hashCode();
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

}
