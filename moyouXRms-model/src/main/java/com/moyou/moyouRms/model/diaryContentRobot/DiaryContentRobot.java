package com.moyou.moyouRms.model.diaryContentRobot;

import java.util.Date;

/**
 * 描述:t_diary_content_robot表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2017-03-28
 */
public class DiaryContentRobot {
	private String extendData;//扩展字段
	public String getExtendData() {
		return extendData;
	}

	public void setExtendData(String extendData) {
		this.extendData = extendData;
	}

	/**
	 * 图片
	 */
	private String picture;
	/**
	 * 文本
	 */
	private String text;
    public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	/**
     * 
     */
    private Integer id;

    /**
     * 日记 id
     */
    private Integer diaryRobotId;

    /**
     * 文字内容或者图片 URL
     */
    private String textOrPicture;

    /**
     * 文字和图片的排序 从 1 开始
     */
    private Integer sorting;
    public static final Integer TEXT=0;
    public static final Integer PIC=1;
    
    /**
     * 0表示文字 1表示图片
     */
    private Integer contenType;

    /**
     * 
     */
    private Date createTime;

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
     * @return diary_robot_id 日记 id
     */
    public Integer getDiaryRobotId() {
        return diaryRobotId;
    }

    /**
     * 日记 id
     * @param diaryRobotId 日记 id
     */
    public void setDiaryRobotId(Integer diaryRobotId) {
        this.diaryRobotId = diaryRobotId;
    }

    /**
     * 文字内容或者图片 URL
     * @return text_or_picture 文字内容或者图片 URL
     */
    public String getTextOrPicture() {
        return textOrPicture;
    }

    /**
     * 文字内容或者图片 URL
     * @param textOrPicture 文字内容或者图片 URL
     */
    public void setTextOrPicture(String textOrPicture) {
        this.textOrPicture = textOrPicture == null ? null : textOrPicture.trim();
    }

    /**
     * 文字和图片的排序 从 1 开始
     * @return sorting 文字和图片的排序 从 1 开始
     */
    public Integer getSorting() {
        return sorting;
    }

    /**
     * 文字和图片的排序 从 1 开始
     * @param sorting 文字和图片的排序 从 1 开始
     */
    public void setSorting(Integer sorting) {
        this.sorting = sorting;
    }

    /**
     * 0表示文字 1表示图片
     * @return conten_type 0表示文字 1表示图片
     */
    public Integer getContenType() {
        return contenType;
    }

    /**
     * 0表示文字 1表示图片
     * @param contenType 0表示文字 1表示图片
     */
    public void setContenType(Integer contenType) {
        this.contenType = contenType;
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
}