package com.moyou.moyouRms.model.user;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.moyou.moyouRms.model.BaseModel;

public class UserList extends BaseModel implements Serializable {
	private Integer userGold;
	private BigDecimal userFund;
	private Integer userSecretCount;// 秘密统计
	private Integer modeType;
	private Integer recommedStatus;// 推荐状态0否1是
	private String modelType;
	private String versionNumber;
	private Integer isLiveUser; // 0不是主播 1是主播
	private Integer continuousSignCount;// 活跃天数
	private Integer userFans;// 粉丝数
	private Integer userInterestCount;// 关注数量

	public Integer getUserFans() {
		return userFans;
	}

	public void setUserFans(Integer userFans) {
		this.userFans = userFans;
	}

	public Integer getUserInterestCount() {
		return userInterestCount;
	}

	public void setUserInterestCount(Integer userInterestCount) {
		this.userInterestCount = userInterestCount;
	}

	public Integer getContinuousSignCount() {
		return continuousSignCount;
	}

	public void setContinuousSignCount(Integer continuousSignCount) {
		this.continuousSignCount = continuousSignCount;
	}

	public Integer getIsLiveUser() {
		return isLiveUser;
	}

	public void setIsLiveUser(Integer isLiveUser) {
		this.isLiveUser = isLiveUser;
	}

	public String getModelType() {
		return modelType;
	}

	public void setModelType(String modelType) {
		this.modelType = modelType;
	}

	public String getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(String versionNumber) {
		this.versionNumber = versionNumber;
	}

	public Integer getRecommedStatus() {
		return recommedStatus;
	}

	public void setRecommedStatus(Integer recommedStatus) {
		this.recommedStatus = recommedStatus;
	}

	public Integer getModeType() {
		return modeType;
	}

	public void setModeType(Integer modeType) {
		this.modeType = modeType;
	}

	public Integer getUserGold() {
		return userGold;
	}

	public Integer getUserSecretCount() {
		return userSecretCount;
	}

	public void setUserSecretCount(Integer userSecretCount) {
		this.userSecretCount = userSecretCount;
	}

	public void setUserGold(Integer userGold) {
		this.userGold = userGold;
	}

	public BigDecimal getUserFund() {
		return userFund;
	}

	public void setUserFund(BigDecimal userFund) {
		this.userFund = userFund;
	}

	public Integer getUserStoryFolderCount() {
		return userStoryFolderCount;
	}

	public void setUserStoryFolderCount(Integer userStoryFolderCount) {
		this.userStoryFolderCount = userStoryFolderCount;
	}

	public Integer getUserTalkCount() {
		return userTalkCount;
	}

	public void setUserTalkCount(Integer userTalkCount) {
		this.userTalkCount = userTalkCount;
	}

	private Integer userStoryFolderCount; // 故事发布数
	private Integer userTalkCount;// 说说发布数量
	private static final long serialVersionUID = 882274592570972144L;
	// 注册方式 '1.手机号注册 2.微信注册 3.qq注册',
	private int accountType;
	// 用户ID
	private String userId;
	// 昵称
	private String nickname;
	// 陌友ID
	private long moyouId;
	// 手机号
	private String phoneNumber;
	// 手机号
	private String account;
	// 用户状态 1.可用2.禁用
	private Integer state;
	// 创建时间
	private String createTime;
	// 性别：1.男 2.女
	private Integer gender;
	// 地址
	private String province;
	private String city;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	// 出生日期
	private Date birthday;
	// 积分总数
	private Integer scoreTotal;
	// 用户头像
	private String avatar;
	// 用户类型
	private Integer type;
	// 运营人员id
	private String accountId;
	// 运营人员名称
	private String accountName;
	// age
	private String age;
	// 在线状态 0离线1在线'
	private int onlineState;

	public int getOnlineState() {
		return onlineState;
	}

	public void setOnlineState(int onlineState) {
		this.onlineState = onlineState;
	}

	private Date updateTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public void setAge(String age) {
		this.age = age;
	}

	// 经度
	private Double longitude;
	// 纬度
	private Double latitude;
	private String userFriendsCount;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getConstellation() {
		return constellation;
	}

	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}

	// 开始年龄
	private int startage;
	// 结束年龄
	private int endage;
	// 星座
	private String constellation;

	public int getStartage() {
		return startage;
	}

	public void setStartage(int startage) {
		this.startage = startage;
	}

	public int getEndage() {
		return endage;
	}

	public void setEndage(int endage) {
		this.endage = endage;
	}

	// 好友数
	private String countFriend;
	// 话题数
	private String countTopic;
	// 动态数
	private String countDynamic;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getCountFriend() {
		return countFriend;
	}

	public void setCountFriend(String countFriend) {
		this.countFriend = countFriend;
	}

	public String getCountTopic() {
		return countTopic;
	}

	public void setCountTopic(String countTopic) {
		this.countTopic = countTopic;
	}

	public String getCountDynamic() {
		return countDynamic;
	}

	public void setCountDynamic(String countDynamic) {
		this.countDynamic = countDynamic;
	}

	public String getAge() {
		return age;
	}

	public void user(String age) {
		this.age = age;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public long getMoyouId() {
		return moyouId;
	}

	public void setMoyouId(long moyouId) {
		this.moyouId = moyouId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	// @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getUserFriendsCount() {
		return userFriendsCount;
	}

	public void setUserFriendsCount(String userFriendsCount) {
		this.userFriendsCount = userFriendsCount;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getScoreTotal() {
		return scoreTotal;
	}

	public void setScoreTotal(Integer scoreTotal) {
		this.scoreTotal = scoreTotal;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
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

	public int getAccountType() {
		return accountType;
	}

	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}
}
