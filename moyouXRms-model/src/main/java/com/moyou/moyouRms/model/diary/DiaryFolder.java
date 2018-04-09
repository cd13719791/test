package com.moyou.moyouRms.model.diary;

import java.util.Date;
import java.util.List;

/**
 * 描述:t_diary_folder表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2017-02-28
 */
public class DiaryFolder {
	/**
	 * 头像url
	 */
	private String avatar;
    public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	/**
     * 
     */
    private Integer id;

    /**
     * 日记本名称
     */
    private String diaryFolderName;

    /**
     * 日记本封面
     */
    private String diaryFolderUrl;

    /**
     * 创建者 id
     */
    private Integer creatorId;

    /**
     * 包含日记总数
     */
    private Integer diaryTotal;

    /**
     * 是否为默认日记本 1.默认日记本  2.自定义日记本
     */
    private Integer whetherDefault;

    /**
     * 0.已删除  1.未删除
     */
    private Integer state;

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
     * 日记本名称
     * @return diary_folder_name 日记本名称
     */
    public String getDiaryFolderName() {
        return diaryFolderName;
    }

    /**
     * 日记本名称
     * @param diaryFolderName 日记本名称
     */
    public void setDiaryFolderName(String diaryFolderName) {
        this.diaryFolderName = diaryFolderName == null ? null : diaryFolderName.trim();
    }

    /**
     * 日记本封面
     * @return diary_folder_url 日记本封面
     */
    public String getDiaryFolderUrl() {
        return diaryFolderUrl;
    }

    /**
     * 日记本封面
     * @param diaryFolderUrl 日记本封面
     */
    public void setDiaryFolderUrl(String diaryFolderUrl) {
        this.diaryFolderUrl = diaryFolderUrl == null ? null : diaryFolderUrl.trim();
    }

    /**
     * 创建者 id
     * @return creator_id 创建者 id
     */
    public Integer getCreatorId() {
        return creatorId;
    }

    /**
     * 创建者 id
     * @param creatorId 创建者 id
     */
    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * 包含日记总数
     * @return diary_total 包含日记总数
     */
    public Integer getDiaryTotal() {
        return diaryTotal;
    }

    /**
     * 包含日记总数
     * @param diaryTotal 包含日记总数
     */
    public void setDiaryTotal(Integer diaryTotal) {
        this.diaryTotal = diaryTotal;
    }

    /**
     * 是否为默认日记本 1.默认日记本  2.自定义日记本
     * @return whether_default 是否为默认日记本 1.默认日记本  2.自定义日记本
     */
    public Integer getWhetherDefault() {
        return whetherDefault;
    }

    /**
     * 是否为默认日记本 1.默认日记本  2.自定义日记本
     * @param whetherDefault 是否为默认日记本 1.默认日记本  2.自定义日记本
     */
    public void setWhetherDefault(Integer whetherDefault) {
        this.whetherDefault = whetherDefault;
    }

    /**
     * 0.已删除  1.未删除
     * @return state 0.已删除  1.未删除
     */
    public Integer getState() {
        return state;
    }

    /**
     * 0.已删除  1.未删除
     * @param state 0.已删除  1.未删除
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
     * 推荐状态
     */
    private Integer recommend;
      
    public Integer getRecommend() {
		return recommend;
	}

	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}
	/**
	 * 推荐状态
	 */
	private DiaryFolderRecommend diaryFolderRecommend;

    public DiaryFolderRecommend getDiaryFolderRecommend() {
		return diaryFolderRecommend;
	}

	public void setDiaryFolderRecommend(DiaryFolderRecommend diaryFolderRecommend) {
		this.diaryFolderRecommend = diaryFolderRecommend;
	}
	/**
	 * 发布人的昵称
	 */
	private String nickname;
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
     * 日记集合
     */
    private List<Diary> diarys;

	public List<Diary> getDiarys() {
		return diarys;
	}

	public void setDiarys(List<Diary> diarys) {
		this.diarys = diarys;
	}
}