package com.moyou.moyouRms.model.gerrting;

import java.io.Serializable;
import java.util.Date;


public class PicList implements Serializable{
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result
				+ ((festivalContent == null) ? 0 : festivalContent.hashCode());
		result = prime * result
				+ ((festivalDate == null) ? 0 : festivalDate.hashCode());
		result = prime
				* result
				+ ((festivalDateText == null) ? 0 : festivalDateText.hashCode());
		result = prime * result
				+ ((festivalPicture == null) ? 0 : festivalPicture.hashCode());
		result = prime * result
				+ ((festivalTitle == null) ? 0 : festivalTitle.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + state;
		result = prime * result
				+ ((updateTime == null) ? 0 : updateTime.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PicList other = (PicList) obj;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (festivalContent == null) {
			if (other.festivalContent != null)
				return false;
		} else if (!festivalContent.equals(other.festivalContent))
			return false;
		if (festivalDate == null) {
			if (other.festivalDate != null)
				return false;
		} else if (!festivalDate.equals(other.festivalDate))
			return false;
		if (festivalDateText == null) {
			if (other.festivalDateText != null)
				return false;
		} else if (!festivalDateText.equals(other.festivalDateText))
			return false;
		if (festivalPicture == null) {
			if (other.festivalPicture != null)
				return false;
		} else if (!festivalPicture.equals(other.festivalPicture))
			return false;
		if (festivalTitle == null) {
			if (other.festivalTitle != null)
				return false;
		} else if (!festivalTitle.equals(other.festivalTitle))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (state != other.state)
			return false;
		if (updateTime == null) {
			if (other.updateTime != null)
				return false;
		} else if (!updateTime.equals(other.updateTime))
			return false;
		return true;
	}
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 4066375513564217955L;
	private Integer id;
	private String festivalTitle;// 标题
	private String festivalContent;
	private String festivalPicture;
	private String festivalDate;
	private String festivalDateText;
	private Date createTime;//创建的时间
	private Date updateTime;
	private int state;
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
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
	public String getFestivalTitle() {
		return festivalTitle;
	}
	public void setFestivalTitle(String festivalTitle) {
		this.festivalTitle = festivalTitle;
	}
	public String getFestivalContent() {
		return festivalContent;
	}
	public void setFestivalContent(String festivalContent) {
		this.festivalContent = festivalContent;
	}
	public String getFestivalPicture() {
		return festivalPicture;
	}
	public void setFestivalPicture(String festivalPicture) {
		this.festivalPicture = festivalPicture;
	}
	public String getFestivalDate() {
		return festivalDate;
	}
	public void setFestivalDate(String festivalDate) {
		this.festivalDate = festivalDate;
	}
	public String getFestivalDateText() {
		return festivalDateText;
	}
	public void setFestivalDateText(String festivalDateText) {
		this.festivalDateText = festivalDateText;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
