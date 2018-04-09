package com.moyou.moyouRms.model.gerrting;

import java.util.Date;

import com.moyou.moyouRms.model.BaseModel;

public class GreetingAbstract extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5639742118243046959L;
	private Integer blessingId;// 卡牌id
	public Integer getBlessingId() {
		return blessingId;
	}

	public void setBlessingId(Integer blessingId) {
		this.blessingId = blessingId;
	}
	private String festivalTitle;// 标题
	private String festivalContent;
	private String festivalPicture;
	private Date festivalDate;
	private int useCount;
	private int fromId;
	public int getFromId() {
		return fromId;
	}

	public void setFromId(int fromId) {
		this.fromId = fromId;
	}

	public int getUseCount() {
		return useCount;
	}

	public void setUseCount(int useCount) {
		this.useCount = useCount;
	}

	public Date getFestivalDate() {
		return festivalDate;
	}

	public void setFestivalDate(Date festivalDate) {
		this.festivalDate = festivalDate;
	}
private int state;
	public int getState() {
	return state;
}

public void setState(int state) {
	this.state = state;
}
private Date createTime;
	public Date getCreateTime() {
	return createTime;
}

public void setCreateTime(Date createTime) {
	this.createTime = createTime;
}
	private String festivalDateText;
   private Integer id;
	private String greetingAppellation;//称呼
	private String greetingContent;//内容
	private String greetingBackground;//背景
	private String greetingInscribe;//落款
	public String getGreetingAppellation() {
		return greetingAppellation;
	}

	public void setGreetingAppellation(String greetingAppellation) {
		this.greetingAppellation = greetingAppellation;
	}

	public String getGreetingContent() {
		return greetingContent;
	}

	public void setGreetingContent(String greetingContent) {
		this.greetingContent = greetingContent;
	}

	public String getGreetingBackground() {
		return greetingBackground;
	}

	public void setGreetingBackground(String greetingBackground) {
		this.greetingBackground = greetingBackground;
	}

	public String getGreetingInscribe() {
		return greetingInscribe;
	}

	public void setGreetingInscribe(String greetingInscribe) {
		this.greetingInscribe = greetingInscribe;
	}

	public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

	public String getFestivalDateText() {
		return festivalDateText;
	}

	public void setFestivalDateText(String festivalDateText) {
		this.festivalDateText = festivalDateText;
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
}
