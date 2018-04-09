package com.moyou.moyouRms.model.userSign;

import java.util.Date;

/**
 * 描述:t_user_sign表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2017-02-21
 */
public class UserSign {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 签到用户id
     */
    private Integer userId;

    /**
     * 连续签到次数
     */
    private Integer continuousSignCount;

    /**
     * 总次数
     */
    private Integer signCount;

    /**
     * 最后签到日期
     */
    private Date lastDate;


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
     * 签到用户id
     * @return user_id 签到用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 签到用户id
     * @param userId 签到用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 连续签到次数
     * @return continuous_sign_count 连续签到次数
     */
    public Integer getContinuousSignCount() {
        return continuousSignCount;
    }

    /**
     * 连续签到次数
     * @param continuousSignCount 连续签到次数
     */
    public void setContinuousSignCount(Integer continuousSignCount) {
        this.continuousSignCount = continuousSignCount;
    }

    /**
     * 总次数
     * @return sign_count 总次数
     */
    public Integer getSignCount() {
        return signCount;
    }

    /**
     * 总次数
     * @param signCount 总次数
     */
    public void setSignCount(Integer signCount) {
        this.signCount = signCount;
    }

    /**
     * 最后签到日期
     * @return last_date 最后签到日期
     */
    public Date getLastDate() {
        return lastDate;
    }

    /**
     * 最后签到日期
     * @param lastDate 最后签到日期
     */
    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }

}