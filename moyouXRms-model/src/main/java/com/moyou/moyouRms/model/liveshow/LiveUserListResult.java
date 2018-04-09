package com.moyou.moyouRms.model.liveshow;

import java.math.BigDecimal;
import java.util.Date;

import cn.jiguang.common.utils.StringUtils;

import com.moyou.moyouRms.model.BaseModel;

/**
 * @author created by Chenxv
 * @date 2017年9月8日 下午2:45:45
 */
public class LiveUserListResult extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;// 用户id
	private String nickName;// 昵称
	private String moyouId;// 陌友id
	private String realName;// 真实姓名
	private Integer sex;// 性别 '性别 0男 1女'
	private Date birthyDay;// 生日
	private String phone;// 电话
	private Integer levelGrade;// 等级
	private BigDecimal userFund;// 钱包余额
	private Integer gold;// 金币数量
	private Date createTime;// 申请直播时间
	private Integer onlineTime;// 直播时间 单位秒
	private Integer state;// 0不可用1可用 限制状态
	private Integer age;// 年龄
	private String avatar;
	private Integer allEarnings;// 直播总收益金币
	private String operationUser;// 操作人
	private String operationRemark;// 备注
	private Date operationTime;// 操作时间
	private Integer recommendState;
	private Integer recommendSort;
	private Date recommendTime;

	public Integer getRecommendState() {
		return recommendState;
	}

	public void setRecommendState(Integer recommendState) {
		this.recommendState = recommendState;
	}

	public Integer getRecommendSort() {
		return recommendSort;
	}

	public void setRecommendSort(Integer recommendSort) {
		this.recommendSort = recommendSort;
	}

	public Date getRecommendTime() {
		return recommendTime;
	}

	public void setRecommendTime(Date recommendTime) {
		this.recommendTime = recommendTime;
	}

	public Date getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}

	public String getOperationUser() {
		return operationUser;
	}

	public void setOperationUser(String operationUser) {
		this.operationUser = operationUser;
	}

	public String getOperationRemark() {
		return operationRemark;
	}

	public void setOperationRemark(String operationRemark) {
		this.operationRemark = operationRemark;
	}

	public Integer getAllEarnings() {
		return allEarnings;
	}

	public void setAllEarnings(Integer allEarnings) {
		this.allEarnings = allEarnings;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getPhone() {
		if (StringUtils.isNotEmpty(phone) && phone.indexOf("*") < 0) {
			phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
		}
		return phone;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getMoyouId() {
		return moyouId;
	}

	public void setMoyouId(String moyouId) {
		this.moyouId = moyouId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Date getBirthyDay() {
		return birthyDay;
	}

	public void setBirthyDay(Date birthyDay) {
		this.birthyDay = birthyDay;
	}

	public Integer getLevelGrade() {
		return levelGrade;
	}

	public void setLevelGrade(Integer levelGrade) {
		this.levelGrade = levelGrade;
	}

	public BigDecimal getUserFund() {
		return userFund;
	}

	public void setUserFund(BigDecimal userFund) {
		this.userFund = userFund;
	}

	public Integer getGold() {
		return gold;
	}

	public void setGold(Integer gold) {
		this.gold = gold;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getOnlineTime() {
		return onlineTime;
	}

	public void setOnlineTime(Integer onlineTime) {
		this.onlineTime = onlineTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
