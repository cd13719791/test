package com.moyou.moyouRms.model.user;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.moyou.moyouRms.util.DateTimeUtil;
import com.moyou.moyouRms.util.JsonUtil;

/**
 * 用户基本信息
 * 
 * @author yubing
 * @date 2016年9月28日 下午3:33:40
 */
public class UserCacheInfo implements Serializable {
	private static final long serialVersionUID = -401593079606925100L;
	// 用户id
	private String userId;
	// 昵称
	private String nickname;
	// 陌友id
	private Long moyouId;
	// 运营人员id
	private String accountId;
	// 手机号
	private String phoneNumber;
	// 用户状态 1.可用 2.不可用
	private Integer state;
	// 头像
	private String avatar;
	// 性别：1.男 2.女
	private Integer sex;
	// 年龄
	private String age;
	// 星座
	private String constellation;
	// 地址
	private String address;
	// 个性签名
	private String sig;
	// 推送id
	private String pushId;
	// 生日
	private Date birthday;
  //用户好友
	private String contactUserId;
	public String getContactUserId() {
		return contactUserId;
	}

	public void setContactUserId(String contactUserId) {
		this.contactUserId = contactUserId;
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

	public Long getMoyouId() {
		return moyouId;
	}

	public void setMoyouId(Long moyouId) {
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

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
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

	public String getSig() {
		return sig;
	}

	public void setSig(String sig) {
		this.sig = sig;
	}

	public String getPushId() {
		return pushId;
	}

	public void setPushId(String pushId) {
		this.pushId = pushId;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * 缓存对象初始化
	 * 
	 * @param user
	 * @param userInfo
	 * @return
	 */
	public static UserCacheInfo instanceOfUserCacheInfo(User user, UserInfo userInfo) {
		UserCacheInfo userCacheInfo = JsonUtil.toObject(userInfo, UserCacheInfo.class);
		BeanUtils.copyProperties(user, userCacheInfo);
		userCacheInfo.setUserId(user.getUserId()+"");
		if(userCacheInfo.getBirthday() == null) {
			userCacheInfo.setAge("20");
		} else {
			userCacheInfo.setAge(
					DateTimeUtil.getPersonAgeByBirthDate(userCacheInfo.getBirthday()));
		}
		return userCacheInfo;
	}
}
