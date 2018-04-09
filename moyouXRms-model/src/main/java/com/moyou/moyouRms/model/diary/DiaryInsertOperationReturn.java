package com.moyou.moyouRms.model.diary;

/**
 * 发布日记成功返回实体
 * 
 * @author PzC.
 * @time 2017年2月28日 上午11:05:08
 * 
 */
public class DiaryInsertOperationReturn {
	private Integer diaryId;// 说说 id

	public DiaryInsertOperationReturn() {
		super();
	}

	public DiaryInsertOperationReturn(Integer diaryId) {
		this.diaryId = diaryId;
	}

	public Integer getDiaryId() {
		return diaryId;
	}

	public void setDiaryId(Integer diaryId) {
		this.diaryId = diaryId;
	}

}
