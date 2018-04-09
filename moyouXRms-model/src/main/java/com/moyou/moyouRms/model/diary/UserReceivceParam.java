package com.moyou.moyouRms.model.diary;

/**  
 * @Title:  用户接收分享数据实体
 * @Description:    TODO
 * @author: liuxinyi
 * @date:   2017年7月17日 下午2:01:30   
 * @email liuxinyi@mousns.com
 * @version V3.3
 */
public class UserReceivceParam {
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 分享的应用名称
	 */
	private String shareAppName;
	
	/**
	 * 平台类型  @see UserDeviceClientTypeEnum
	 */
	private Integer type;
	/**
	 * 数据内容
	 */
	private String content;
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
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
	/**
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
}
