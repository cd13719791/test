package com.moyou.moyouRms.constants;

/**
 * 文件常量
 * 
 * @author yubing
 * @date 2016年9月27日 下午7:13:23
 */
public interface FILECONSTANT {
	// 默认上传文件格式
	String[] DEFAULT_ALLOWED_EXTENSION = { "mp3", "mp4", "jpg" };
	// 默认文件名长度
	int DEFAULT_FILE_NAME_LENGTH = 50;
	// 默认文件大小
	int DEFAULT_MAX_SIZE = 100000;
	// 图片
	String IMAGE = "image";
	// 头像图片
	String IMAGE_AVATAR = "image/avatar/";
	// 首页动态图片
	String IMAGE_CIRCLE = "image/circle/";
	// 音频
	String AUDIO = "audio/";
	// 视频
	String VIDEO = "video/";
	// 七牛云存储空间默认域名
	String QINIU_DNS = "http://oe76iuenu.bkt.clouddn.com/";
}
