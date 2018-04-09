package com.moyou.moyouRms.model.userGold;

import java.util.Date;

/**
 * 描述:t_user_gold表的实体类
 * 
 * @version
 * @author: Administrator
 * @创建时间: 2017-02-21
 */
public class UserGold {
	private Integer liveGold;
	private String lockVersion;

	public UserGold(Integer id, Integer liveGold, String lockVersion, Date updateDate) {
		super();
		this.id = id;
		this.liveGold = liveGold;
		this.lockVersion = lockVersion;
		this.updateTime = updateDate;
	}

	public UserGold() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserGold(Integer liveGold, String lockVersion, Integer id, Integer userId,
			Integer userGold, Date updateTime) {
		super();
		this.liveGold = liveGold;
		this.lockVersion = lockVersion;
		this.id = id;
		this.userId = userId;
		this.userGold = userGold;
		this.updateTime = updateTime;
	}

	public Integer getLiveGold() {
		return liveGold;
	}

	public void setLiveGold(Integer liveGold) {
		this.liveGold = liveGold;
	}

	public String getLockVersion() {
		return lockVersion;
	}

	public void setLockVersion(String lockVersion) {
		this.lockVersion = lockVersion;
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
	 * 用户金币
	 */
	private Integer userGold;

	/**
     * 
     */
	private Date updateTime;

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
	 * 用户金币
	 * 
	 * @return user_gold 用户金币
	 */
	public Integer getUserGold() {
		return userGold;
	}

	/**
	 * 用户金币
	 * 
	 * @param userGold
	 *            用户金币
	 */
	public void setUserGold(Integer userGold) {
		this.userGold = userGold;
	}

	/**
	 * 
	 * @return update_time
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 
	 * @param updateTime
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}