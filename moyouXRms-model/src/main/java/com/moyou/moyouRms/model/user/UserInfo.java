package com.moyou.moyouRms.model.user;

import java.io.Serializable;
import java.util.Date;

import com.moyou.moyouRms.model.userGold.UserGold;
import com.moyou.moyouRms.model.userfund.UserFund;
import com.moyou.moyouRms.util.JsonUtil;

public class UserInfo implements Serializable {
	private static final long serialVersionUID = -6267399721581802738L;
	private Integer recommedStatus;// '推荐状态0否1是'
	private String account;
	private String modelType;
	private String versionNumber;
	private String ip;
	private Integer regType;// 注册类型0旧账号状态1安卓2ios3h5端4pc端
	public static final Integer ANDROID = 1;
	public static final Integer IOS = 2;
	private Integer state;
	private String weixin;// 主播绑定的微信账号

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getRegType() {
		return regType;
	}

	public void setRegType(Integer regType) {
		this.regType = regType;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public UserInfo(Integer userId, String avatar) {
		super();
		this.userId = userId;
		this.avatar = avatar;
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

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Integer getRecommedStatus() {
		return recommedStatus;
	}

	public void setRecommedStatus(Integer recommedStatus) {
		this.recommedStatus = recommedStatus;
	}

	private Integer accountType;
	private Integer type;// 账号类型。0真用户 1假用户

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	private Integer userId;

	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	private UserFund userFund;// 账户资金
	private UserGold userGold;// 账户金币
	private Double latitude;// 纬度
	private Double longitude;// 经度

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public UserFund getUserFund() {
		return userFund;
	}

	public void setUserFund(UserFund userFund) {
		this.userFund = userFund;
	}

	public UserGold getUserGold() {
		return userGold;
	}

	public void setUserGold(UserGold userGold) {
		this.userGold = userGold;
	}

	private String nickname;

	private String avatar;

	private Integer gender;

	private String province;

	private String city;

	private Date birthday;

	private Integer height;

	private Integer weight;

	private String pushChatId;

	private String sig;

	private String moyouId;

	private Date createTime;

	private Date updateTime;
	private String age;
	private int roleType;// 群成员类型

	public int getRoleType() {
		return roleType;
	}

	public void setRoleType(int roleType) {
		this.roleType = roleType;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname == null ? null : nickname.trim();
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar == null ? null : avatar.trim();
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
		this.province = province == null ? null : province.trim();
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city == null ? null : city.trim();
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getPushChatId() {
		return pushChatId;
	}

	public void setPushChatId(String pushChatId) {
		this.pushChatId = pushChatId == null ? null : pushChatId.trim();
	}

	public String getSig() {
		return sig;
	}

	public void setSig(String sig) {
		this.sig = sig == null ? null : sig.trim();
	}

	public String getMoyouId() {
		return moyouId;
	}

	public void setMoyouId(String moyouId) {
		this.moyouId = moyouId == null ? null : moyouId.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserInfo other = (UserInfo) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	public UserInfo() {

	}

	/**
	 * UserInfo 对象初始化
	 * 
	 * @param object
	 * @param userId
	 * @return
	 * @return
	 */
	public static UserInfo instanceOfUserInfo(Object object, String userId, Date now, String moyouId) {
		UserInfo userInfo = JsonUtil.toObject(JsonUtil.toJson(object), UserInfo.class);
		userInfo.setMoyouId(moyouId);
		userInfo.setUserId(Integer.valueOf(userId));
		userInfo.setCreateTime(now);
		userInfo.setUpdateTime(now);
		return userInfo;
	}
}
