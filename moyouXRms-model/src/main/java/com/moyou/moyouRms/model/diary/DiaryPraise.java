package com.moyou.moyouRms.model.diary;

import java.util.Date;

/**
 * 描述:t_diary_praise表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2017-02-28
 */
public class DiaryPraise {
    public static final Integer PRAISE = 1;

	/**
     * 
     */
    private Integer id;

    /**
     * 日记 id
     */
    private Integer diaryId;

    /**
     * 用户 id
     */
    private Integer userId;

    /**
     * 0未点赞 1已点赞
     */
    private Integer state;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 
     * @return id 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 日记 id
     * @return diary_id 日记 id
     */
    public Integer getDiaryId() {
        return diaryId;
    }

    /**
     * 日记 id
     * @param diaryId 日记 id
     */
    public void setDiaryId(Integer diaryId) {
        this.diaryId = diaryId;
    }

    /**
     * 用户 id
     * @return user_id 用户 id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 用户 id
     * @param userId 用户 id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 0未点赞 1已点赞
     * @return state 0未点赞 1已点赞
     */
    public Integer getState() {
        return state;
    }

    /**
     * 0未点赞 1已点赞
     * @param state 0未点赞 1已点赞
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 
     * @return create_time 
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 
     * @param createTime 
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
    private String avatar;
    private String nickname;

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}