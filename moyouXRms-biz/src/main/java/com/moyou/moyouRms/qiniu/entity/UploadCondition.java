package com.moyou.moyouRms.qiniu.entity;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

/**
 * 上传文件数据类型
 * 
 * @author yubing
 * @date 2016年9月28日 下午2:55:04
 */
public class UploadCondition implements Serializable{
	private static final long serialVersionUID = -8836373058697531528L;
	private MultipartFile[] files;
	private String userId;// 用户id
	private String filePath;// 二级域名
	
	
	public MultipartFile[] getFiles() {
		return files;
	}

	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}
