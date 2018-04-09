package com.moyou.moyouRms.model.secret;

/**
 * SecretContentInsertModel
 * 
 * @author PzC.
 * @time 2017年3月29日 上午11:46:41
 * 
 */
public class SecretContentInsertModel {
	private Integer secretId;// 秘密 id
	private String textOrPicture;// 文字内容或者图片 URL
	private Integer sorting;// 文字和图片的排序 从 1 开始
	private Integer contentType;// 0表示文字 1表示图片
	private String extendData;// 附加数据

	public Integer getSecretId() {
		return secretId;
	}

	public void setSecretId(Integer secretId) {
		this.secretId = secretId;
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

	public String getExtendData() {
		return extendData;
	}

	public void setExtendData(String extendData) {
		this.extendData = extendData;
	}

	public Integer getContentType() {
		return contentType;
	}

	public void setContentType(Integer contentType) {
		this.contentType = contentType;
	}

}
