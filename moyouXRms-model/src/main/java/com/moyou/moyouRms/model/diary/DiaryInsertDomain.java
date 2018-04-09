package com.moyou.moyouRms.model.diary;

import java.util.Date;
import java.util.List;

/**
 * insert Diary 实体
 * 
 * @author PzC.
 * @time 2017年2月28日 上午10:36:09
 * 
 */
public class DiaryInsertDomain {
	private Integer diaryId;// 日记 id
	private String diaryTitle;// 日记标题
	private String city;// 城市定位信息
	private String searchContent;// 拼接所有的文本信息用于检索
	private Integer picTotal;// 图片数量统计
	private Integer creatorId;// 创建用户 id
	private Integer gender;
	private Date pushTime;//发送时间
	private int state;
	private int sex;
	private Date createTime;
	 public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	/**
     * 发布状态  0未发布 1已发布
     */
    private Integer pushState;

	public Integer getPushState() {
		return pushState;
	}

	public void setPushState(Integer pushState) {
		this.pushState = pushState;
	}


	public Date getPushTime() {
		return pushTime;
	}

	public void setPushTime(Date pushTime) {
		this.pushTime = pushTime;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	private List<DiaryContentInsertDomain> contents;// 文字和内容

	public String getDiaryTitle() {
		return diaryTitle;
	}

	public void setDiaryTitle(String diaryTitle) {
		this.diaryTitle = diaryTitle;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	public List<DiaryContentInsertDomain> getContents() {
		return contents;
	}

	public void setContents(List<DiaryContentInsertDomain> contents) {
		this.contents = contents;
	}

	public Integer getDiaryId() {
		return diaryId;
	}

	public void setDiaryId(Integer diaryId) {
		this.diaryId = diaryId;
	}

	public String getSearchContent() {
		return searchContent;
	}

	public void setSearchContent(String searchContent) {
		this.searchContent = searchContent;
	}

	public Integer getPicTotal() {
		return picTotal;
	}

	public void setPicTotal(Integer picTotal) {
		this.picTotal = picTotal;
	}

}
