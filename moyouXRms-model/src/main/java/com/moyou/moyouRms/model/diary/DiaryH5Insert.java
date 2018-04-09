package com.moyou.moyouRms.model.diary;

import java.io.Serializable;
import java.util.Date;

/** 
 * @author 作者:陈旭 
 * @version 创建时间：2017年6月26日 上午11:27:50 
 * 类说明   h5格式发布
 */
public class DiaryH5Insert implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String diaryTitle;//  日志标题
	private String searchContent;  //h5内容
	private Integer pirTotal; //图片数量
	private String h5Url; // 前端展示用的url
	private Integer sex;//发布假用户的性别
	private Date pushTime;//发布时间
	private String firstContent;//前端展示用的第一段内容
	private String firstPicUrl;//第一张图片url
	private String extendData;//图片扩展字段  图片尺寸
	public String getExtendData() {
		return extendData;
	}
	public void setExtendData(String extendData) {
		this.extendData = extendData;
	}
	@Override
	public String toString() {
		return "DiaryH5Insert [diaryTitle=" + diaryTitle + ", searchContent="
				+ searchContent + ", pirTotal=" + pirTotal + ", h5Url=" + h5Url
				+ ", sex=" + sex + ", pushTime=" + pushTime + ", firstContent="
				+ firstContent + ", firstPicUrl=" + firstPicUrl + "]";
	}
	public String getDiaryTitle() {
		return diaryTitle;
	}
	public void setDiaryTitle(String diaryTitle) {
		this.diaryTitle = diaryTitle;
	}
	public String getSearchContent() {
		return searchContent;
	}
	public void setSearchContent(String searchContent) {
		this.searchContent = searchContent;
	}
	public Integer getPirTotal() {
		return pirTotal;
	}
	public void setPirTotal(Integer pirTotal) {
		this.pirTotal = pirTotal;
	}
	public String getH5Url() {
		return h5Url;
	}
	public void setH5Url(String h5Url) {
		this.h5Url = h5Url;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Date getPushTime() {
		return pushTime;
	}
	public void setPushTime(Date pushTime) {
		this.pushTime = pushTime;
	}
	public String getFirstContent() {
		return firstContent;
	}
	public void setFirstContent(String firstContent) {
		this.firstContent = firstContent;
	}
	public String getFirstPicUrl() {
		return firstPicUrl;
	}
	public void setFirstPicUrl(String firstPicUrl) {
		this.firstPicUrl = firstPicUrl;
	}
}
