package com.moyou.moyouRms.model.diary;

public class DiaryContentInsertDomain {
	private Integer diaryId;// 日记 id
	private String textOrPicture;// 文字内容或者图片 URL
	private Integer sorting;// 文字和图片的排序 从 1 开始
	private Integer contenType;// 0表示文字 1表示图片
	private String extendData;// 扩展属性json，不参与检索

	public Integer getDiaryId() {
		return diaryId;
	}

	public void setDiaryId(Integer diaryId) {
		this.diaryId = diaryId;
	}

	public String getTextOrPicture() {
		return textOrPicture;
	}

	public void setTextOrPicture(String textOrPicture) {
		this.textOrPicture = textOrPicture;
	}

	public Integer getSorting() {
		return sorting;
	}

	public void setSorting(Integer sorting) {
		this.sorting = sorting;
	}

	public Integer getContenType() {
		return contenType;
	}

	public void setContenType(Integer contenType) {
		this.contenType = contenType;
	}

	public String getExtendData() {
		return extendData;
	}

	public void setExtendData(String extendData) {
		this.extendData = extendData;
	}

}
