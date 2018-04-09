package com.moyou.moyouRms.model.user;

import java.io.Serializable;
import java.util.Date;

import com.moyou.moyouRms.model.BaseModel;

public class UserRegCondition extends BaseModel  implements Serializable {
	private static final long serialVersionUID = -8209652710632652122L;
	private Integer acountType;
	public Integer getAcountType() {
		return acountType;
	}

	public void setAcountType(Integer acountType) {
		this.acountType = acountType;
	}

	private String smsCaptcha;
	// 昵称
	private String nickname;
	// 手机号
	private String phoneNumber;
	
	private String accountId;
	// 密码
	private String password;
	// 经度
	private Double longitude;
	// 纬度
	private Double latitude;
	// 用户id
	private String userId;
	// 头像
	private String avatar;
	// 个性签名
	private String sig="";
	// 性别：1.男 2.女
	private Integer sex;
	private Integer gender;
	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	// 出生日期
	private Date birthday;
	// 星座
	private String constellation;
	// 地址
	private String address;
	// 1前台生成 2 后台生成
	private Integer type;
	// 第三方id
	private String openId;
	// 设备号
	private String device;
	// ip
	private String ip;
	
	private String moyouId;
	
	private String age;
	private String province;
	private String city;
	
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getMoyouId() {
		return moyouId;
	}

	public void setMoyouId(String moyouId) {
		this.moyouId = moyouId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getConstellation() {
		return constellation;
	}

	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getSmsCaptcha() {
		return smsCaptcha;
	}

	public void setSmsCaptcha(String smsCaptcha) {
		this.smsCaptcha = smsCaptcha;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getSig() {
		return sig;
	}

	public void setSig(String sig) {
		this.sig = sig;
	}
	
}
