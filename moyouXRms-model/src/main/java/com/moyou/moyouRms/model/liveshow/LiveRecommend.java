package com.moyou.moyouRms.model.liveshow;

import java.util.Date;

import com.moyou.moyouRms.model.BaseModel;

public class LiveRecommend extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id; // 主键
	private String nickName; // 主播昵称
	private String moyouId; // 主播id
	private Integer sex; // 性别 '性别 0男 1女'
	private String realName; // 真实姓名
	private Integer age; // 年龄
	private Integer levelGrade; // 等级
	private String realPhone; // 联系电话
	private Date createTime; // 推荐时间
	private Date updateTime; // 修改时间
	private Integer allEarnings; // 直播总收益金币
	private Integer LiveSeconds; // 累计直播时长 /秒
	private String onlineTime; // 累计直播时长 /分
	private Date birthday;
	private Integer userId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	private Date recommendTime;

	public Date getRecommendTime() {
		return recommendTime;
	}

	public void setRecommendTime(Date recommendTime) {
		this.recommendTime = recommendTime;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the nickName
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * @param nickName
	 *            the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/**
	 * @return the moyouId
	 */
	public String getMoyouId() {
		return moyouId;
	}

	/**
	 * @param moyouId
	 *            the moyouId to set
	 */
	public void setMoyouId(String moyouId) {
		this.moyouId = moyouId;
	}

	/**
	 * @return the sex
	 */
	public Integer getSex() {
		return sex;
	}

	/**
	 * @param sex
	 *            the sex to set
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}

	/**
	 * @return the realName
	 */
	public String getRealName() {
		return realName;
	}

	/**
	 * @param realName
	 *            the realName to set
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}

	/**
	 * @return the age
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * @return the levelGrade
	 */
	public Integer getLevelGrade() {
		return levelGrade;
	}

	/**
	 * @param levelGrade
	 *            the levelGrade to set
	 */
	public void setLevelGrade(Integer levelGrade) {
		this.levelGrade = levelGrade;
	}

	/**
	 * @return the realPhone
	 */
	public String getRealPhone() {
		return realPhone;
	}

	/**
	 * @param realPhone
	 *            the realPhone to set
	 */
	public void setRealPhone(String realPhone) {
		this.realPhone = realPhone;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	/**
	 * @return the allEarnings
	 */
	public Integer getAllEarnings() {
		return allEarnings;
	}

	/**
	 * @param allEarnings
	 *            the allEarnings to set
	 */
	public void setAllEarnings(Integer allEarnings) {
		this.allEarnings = allEarnings;
	}

	/**
	 * @return the liveSeconds
	 */
	public Integer getLiveSeconds() {
		return LiveSeconds;
	}

	/**
	 * @param liveSeconds
	 *            the liveSeconds to set
	 */
	public void setLiveSeconds(Integer liveSeconds) {
		LiveSeconds = liveSeconds;
	}

	/**
	 * @return the onlineTime
	 */
	public String getOnlineTime() {
		return onlineTime;
	}

	/**
	 * @param onlineTime
	 *            the onlineTime to set
	 */
	public void setOnlineTime(String onlineTime) {
		this.onlineTime = onlineTime;
	}

	public LiveRecommend(Integer id, String nickName, String moyouId, Integer sex, String realName,
			Integer age, Integer levelGrade, String realPhone, Date createTime, Date updateTime,
			Integer allEarnings, Integer liveSeconds, String onlineTime) {
		super();
		this.id = id;
		this.nickName = nickName;
		this.moyouId = moyouId;
		this.sex = sex;
		this.realName = realName;
		this.age = age;
		this.levelGrade = levelGrade;
		this.realPhone = realPhone;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.allEarnings = allEarnings;
		LiveSeconds = liveSeconds;
		this.onlineTime = onlineTime;
	}

	public LiveRecommend() {
		super();
		// TODO Auto-generated constructor stub
	}

}
