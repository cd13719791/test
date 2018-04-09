package com.moyou.moyouRms.model.userSignLog;

import java.util.Date;

/**
 * 描述:t_user_sign_log表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2017-02-21
 */
public class UserSignLog {
	/**
	 * 签到来源1h5、2ios、3android
	 */
	public static final  Integer USER_SIGN_LOG_H5=1;

	public static final  Integer USER_SIGN_LOG_IOS=2;

	public static final  Integer USER_SIGN_LOG_ANDROID=3;
	private Integer signSource;
    public Integer getSignSource() {
		return signSource;
	}

	public void setSignSource(Integer signSource) {
		this.signSource = signSource;
	}
	/**
	 * 签到时间
	 */
	private String signDate;
	public String getSignDate() {
		return signDate;
	}

	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}
	/**
     * 主键
     */
    private Integer id;

    /**
     * 签到用户
     */
    private Integer userId;

    /**
     * 获取的物品，避免联表查询
     */
    private String signGet;

    /**
     * 签到时间
     */
    private Date createTime;

    /**
     * 主键
     * @return id 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 主键
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 签到用户
     * @return user_id 签到用户
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 签到用户
     * @param userId 签到用户
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取的物品，避免联表查询
     * @return sign_get 获取的物品，避免联表查询
     */
    public String getSignGet() {
        return signGet;
    }

    /**
     * 获取的物品，避免联表查询
     * @param signGet 获取的物品，避免联表查询
     */
    public void setSignGet(String signGet) {
        this.signGet = signGet == null ? null : signGet.trim();
    }

    /**
     * 签到时间
     * @return create_time 签到时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 签到时间
     * @param createTime 签到时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}