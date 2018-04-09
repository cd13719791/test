package com.moyou.moyouRms.model.diaryRecommend;

import java.util.Date;

public class DiaryRecommend {
    private Integer id;

    private Integer modeType;

    private Integer diaryId;

    private Integer recommedStatus;

    private Date createTime;

    private Date updateTime;

    private Integer recommendSort;
    
    public DiaryRecommend(Integer recommedStatus ,Integer diaryId) {
		super();
		this.recommedStatus=recommedStatus;
		this.diaryId = diaryId;
	}

	public DiaryRecommend() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DiaryRecommend(Integer id, Integer modeType, Integer diaryId,
			Integer recommedStatus, Date createTime, Date updateTime,
			Integer recommendSort) {
		super();
		this.id = id;
		this.modeType = modeType;
		this.diaryId = diaryId;
		this.recommedStatus = recommedStatus;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.recommendSort = recommendSort;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getModeType() {
        return modeType;
    }

    public void setModeType(Integer modeType) {
        this.modeType = modeType;
    }

    public Integer getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(Integer diaryId) {
        this.diaryId = diaryId;
    }

    public Integer getRecommedStatus() {
        return recommedStatus;
    }

    public void setRecommedStatus(Integer recommedStatus) {
        this.recommedStatus = recommedStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getRecommendSort() {
        return recommendSort;
    }

    public void setRecommendSort(Integer recommendSort) {
        this.recommendSort = recommendSort;
    }
}