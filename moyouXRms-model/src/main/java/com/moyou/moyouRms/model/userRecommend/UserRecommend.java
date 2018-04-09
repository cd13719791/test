package com.moyou.moyouRms.model.userRecommend;

import java.util.Date;

/**
 * 描述:t_user_recommend表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2017-03-28
 */
public class UserRecommend {
	public UserRecommend(Integer userId, Short recommedStatus) {
		super();
		this.userId = userId;
		this.recommedStatus = recommedStatus;
	}

	public UserRecommend() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserRecommend(Integer id, Short modeType, Integer userId,
			Short recommedStatus, Date createTime, Date updateTime,
			Short recommendSort) {
		super();
		this.id = id;
		this.modeType = modeType;
		this.userId = userId;
		this.recommedStatus = recommedStatus;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.recommendSort = recommendSort;
	}

	public static final short USERRECOMMEND_LIVE_RING=1;//生活圈推荐
	public static final short STATE_YES=1;//推荐状态 1 是
	public static final short STATE_NO=0;//推荐状态  0 否
    /**
     * 主键
     */
    private Integer id;

    /**
     * 1生活圈推荐
     */
    private Short modeType;

    /**
     * 用户主键id
     */
    private Integer userId;

    /**
     * 推荐状态0否1是
     */
    private Short recommedStatus;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 推荐多条数据时排序，正序
     */
    private Short recommendSort;

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
     * 1生活圈推荐
     * @return mode_type 1生活圈推荐
     */
    public Short getModeType() {
        return modeType;
    }

    /**
     * 1生活圈推荐
     * @param modeType 1生活圈推荐
     */
    public void setModeType(Short modeType) {
        this.modeType = modeType;
    }

    /**
     * 用户主键id
     * @return user_id 用户主键id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 用户主键id
     * @param userId 用户主键id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 推荐状态0否1是
     * @return recommed_status 推荐状态0否1是
     */
    public Short getRecommedStatus() {
        return recommedStatus;
    }

    /**
     * 推荐状态0否1是
     * @param recommedStatus 推荐状态0否1是
     */
    public void setRecommedStatus(Short recommedStatus) {
        this.recommedStatus = recommedStatus;
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新时间
     * @return update_time 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 推荐多条数据时排序，正序
     * @return recommend_sort 推荐多条数据时排序，正序
     */
    public Short getRecommendSort() {
        return recommendSort;
    }

    /**
     * 推荐多条数据时排序，正序
     * @param recommendSort 推荐多条数据时排序，正序
     */
    public void setRecommendSort(Short recommendSort) {
        this.recommendSort = recommendSort;
    }
}