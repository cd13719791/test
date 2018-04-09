package com.moyou.moyouRms.dao.diary.resultmodel;

import java.io.Serializable;
import java.util.Map;

public class DirayH5ResultModel implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String extendData;
	/**
	 * 日记标题
	 */
	private String diaryTitle;
	/**
	 * 扩展数据
	 */
	private Map<String, Object> extend;
	/**
	 * 分享媒体
	 */
	private String shareAppName;
	/**
	 * @return the extendData
	 */
	public String getExtendData() {
		return extendData;
	}
	/**
	 * @param extendData the extendData to set
	 */
	public void setExtendData(String extendData) {
		this.extendData = extendData;
	}
	/**
	 * @return the diaryTitle
	 */
	public String getDiaryTitle() {
		return diaryTitle;
	}
	/**
	 * @param diaryTitle the diaryTitle to set
	 */
	public void setDiaryTitle(String diaryTitle) {
		this.diaryTitle = diaryTitle;
	}
	/**
	 * @return the extend
	 */
	public Map<String, Object> getExtend() {
		return extend;
	}
	/**
	 * @param extend the extend to set
	 */
	public void setExtend(Map<String, Object> extend) {
		this.extend = extend;
	}
	/**
	 * @return the shareAppName
	 */
	public String getShareAppName() {
		return shareAppName;
	}
	/**
	 * @param shareAppName the shareAppName to set
	 */
	public void setShareAppName(String shareAppName) {
		this.shareAppName = shareAppName;
	}
	
	
	
}
