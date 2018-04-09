package com.moyou.moyouRms.model.sysAdminRelateUser;

import java.io.Serializable;
import java.util.Date;

/**
 * 描述:sys_admin_relate_user表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2017-05-10
 */
public class SysAdminRelateUser implements Serializable{
	private String  nickname;
	private String avatar;
	private String city;
	private Integer gender;
	private Date birthday;
	private Integer age;
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public SysAdminRelateUser(String accountId) {
		super();
		this.accountId = accountId;
	}
	public SysAdminRelateUser(Integer userId,String accountId) {
		super();
		this.accountId = accountId;
		this.userId=userId;
	}
	public SysAdminRelateUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer offlineMsgCount;
	public Integer getOfflineMsgCount() {
		return offlineMsgCount;
	}

	public void setOfflineMsgCount(Integer offlineMsgCount) {
		this.offlineMsgCount = offlineMsgCount;
	}

	private String  pushChatId;
    public String getPushChatId() {
		return pushChatId;
	}

	public void setPushChatId(String pushChatId) {
		this.pushChatId = pushChatId;
	}

	/**
     * 后台运营管理员id
     */
    private Integer userId;

    /**
     * 用户ID
     */
    private String accountId;

    /**
     * 后台运营管理员id
     * @return user_id 后台运营管理员id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 后台运营管理员id
     * @param userId 后台运营管理员id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 用户ID
     * @return account_id 用户ID
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * 用户ID
     * @param accountId 用户ID
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

}