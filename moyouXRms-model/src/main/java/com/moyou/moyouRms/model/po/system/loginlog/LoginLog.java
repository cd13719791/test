package com.moyou.moyouRms.model.po.system.loginlog;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.moyou.moyouRms.model.BaseModel;
import com.moyou.moyouRms.util.JsonUtil;
@Alias("LoginLog")
public class LoginLog extends BaseModel{

	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String accountId;
	
	private String aName;
	
	private String loginIP;
	
	private String beginTime;
	
	private Date loginTime;
	
	private String endTime;
	
	private String keyWord;
    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	// 用户id
    private String userId;
    // IP地址
    private String ip;
    // 设备号
    private String device;
    // 经度
    private Double longitude;
    // 纬度
    private Double latitude;
    // 创建时间
    private Date createTime;
	public LoginLog(){}
	
	public LoginLog(String accountId, String loginIP) {
		super();
		this.accountId = accountId;
		this.loginIP = loginIP;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getLoginIP() {
		return loginIP;
	}

	public void setLoginIP(String loginIP) {
		this.loginIP = loginIP;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getaName() {
		return aName;
	}

	public void setaName(String aName) {
		this.aName = aName;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}


	/**
	 * LoginLog 对象
	 * 
	 * @param object
	 * @param userId
	 * @return
	 */
	public static LoginLog instanceOfLoginLog(Object object, String userId, Date now) {
		LoginLog loginLog = JsonUtil.toObject(JsonUtil.toJson(object), LoginLog.class);
		loginLog.setUserId(userId);
		loginLog.setCreateTime(now);
		return loginLog;
	}
	
}
