package com.moyou.moyouRms.model.user;

import java.util.Date;

import com.moyou.moyouRms.model.BaseModel;

/**
 * 描述:t_user_followers表的实体类
 * 
 * @version
 * @author: Administrator
 * @创建时间: 2017-07-24
 */
public class UserFollowers extends BaseModel
{

    /**
     * 
     */
    private static final long serialVersionUID = 6389167493445737603L;

    public UserFollowers(Integer userId, Integer followersUserId, Short state, Date createTime,
        Date updateTime)
    {
        super();
        this.userId = userId;
        this.followersUserId = followersUserId;
        this.state = state;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public UserFollowers()
    {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 粉丝用户的用户id
     */
    private Integer followersUserId;

    /**
     * 0不是粉丝的状态 1粉丝的状态
     */
    private Short state;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 用户id
     * 
     * @return user_id 用户id
     */
    public Integer getUserId()
    {
        return userId;
    }

    /**
     * 用户id
     * 
     * @param userId 用户id
     */
    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    /**
     * 粉丝用户的用户id
     * 
     * @return followers_user_id 粉丝用户的用户id
     */
    public Integer getFollowersUserId()
    {
        return followersUserId;
    }

    /**
     * 粉丝用户的用户id
     * 
     * @param followersUserId 粉丝用户的用户id
     */
    public void setFollowersUserId(Integer followersUserId)
    {
        this.followersUserId = followersUserId;
    }

    /**
     * 0不是粉丝的状态 1粉丝的状态
     * 
     * @return state 0不是粉丝的状态 1粉丝的状态
     */
    public Short getState()
    {
        return state;
    }

    /**
     * 0不是粉丝的状态 1粉丝的状态
     * 
     * @param state 0不是粉丝的状态 1粉丝的状态
     */
    public void setState(Short state)
    {
        this.state = state;
    }

    /**
     * 
     * @return create_time
     */
    public Date getCreateTime()
    {
        return createTime;
    }

    /**
     * 
     * @param createTime
     */
    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    /**
     * 
     * @return update_time
     */
    public Date getUpdateTime()
    {
        return updateTime;
    }

    /**
     * 
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime)
    {
        this.updateTime = updateTime;
    }
}
