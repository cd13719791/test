package com.moyou.moyouRms.model.diary;

import java.util.Date;

/**
 * 描述:t_diary_folder_recommend表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2017-02-28
 */
public class DiaryFolderRecommend {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 1生活圈推荐
     */
    private Short modeType;

    /**
     * 日记本主键id
     */
    private Integer diaryFolderId;

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
    private Integer recommendSort;

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
     * 日记本主键id
     * @return diary_folder_id 日记本主键id
     */
    public Integer getDiaryFolderId() {
        return diaryFolderId;
    }

    /**
     * 日记本主键id
     * @param diaryFolderId 日记本主键id
     */
    public void setDiaryFolderId(Integer diaryFolderId) {
        this.diaryFolderId = diaryFolderId;
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
    public Integer getRecommendSort() {
        return recommendSort;
    }

    /**
     * 推荐多条数据时排序，正序
     * @param recommendSort 推荐多条数据时排序，正序
     */
    public void setRecommendSort(Integer recommendSort) {
        this.recommendSort = recommendSort;
    }
}