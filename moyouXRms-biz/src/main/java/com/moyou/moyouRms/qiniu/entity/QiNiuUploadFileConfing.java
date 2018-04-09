package com.moyou.moyouRms.qiniu.entity;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.qiniu.util.UrlSafeBase64;

/**
 * @title 文件上传配置
 * @author liuxinyi
 * @date 2016年06月02日
 * @version 1.0.0
 */
@Component
public class QiNiuUploadFileConfing {
	// http://o83miyz90.bkt.clouddn.com/dd-d-d1.png
	// 设置好账号的ACCESS_KEY和SECRET_KEY
	@Value("#{configProperties['QINIU_ACCESS_KEY']}")
	private String QINIU_ACCESS_KEY;
	@Value("#{configProperties['QINIU_SECRET_KEY']}")
	private String QINIU_SECRET_KEY;
	
	
	@Value("#{configProperties['QINIU_BUCKET_IMAGE_NAME']}")
	private String QINIU_BUCKET_IMAGE_NAME;
	@Value("#{configProperties['QINIU_BUCKET_VIDEO_NAME']}")
	private String QINIU_BUCKET_VIDEO_NAME;
	@Value("#{configProperties['QINIU_BUCKET_VIDEOO_NAME']}")
	private String QINIU_BUCKET_VIDEOO_NAME;
	@Value("#{configProperties['QINIU_BUCKET_AUDIO_NAME']}")
	private String QINIU_BUCKET_AUDIO_NAME;
	@Value("#{configProperties['QINIU_WATERMARK_URL']}")
	private String QINIU_WATERMARK_URL;
	
	private String accessKey = "";
	private String secretKey = "";

	// 要上传的空间
	private String imageBucketname = "";// 图片空间
	private String videoBucketname = "";// 视频未转码空间
	private String videooBucketname = "";// 视频转码后空间
	private String audioBucketname = "";//音频空间
	@SuppressWarnings("unused")
	private String watermarkURL = "";//水印地址

	/**
	 * 文件上传token
	 * @return
	 */
	public String getUpToken() {
		Auth auth = Auth.create(accessKey, secretKey);
		return auth.uploadToken(imageBucketname);
	}
	/**
	 * 图片上传token
	 * @return
	 */
	public String getImageUpToken() {
		Auth auth = Auth.create(accessKey, secretKey);
		return auth.uploadToken(imageBucketname);
	}
	/**
	 * 音频上传
	 * @return
	 */
	public String getAudioUpToken() {
		Auth auth = Auth.create(accessKey, secretKey);
		return auth.uploadToken(audioBucketname);
	}
	/**
	 * 视频上传
	 * @return
	 */
	public String getVideoUpToken() {
		Auth auth = Auth.create(accessKey, secretKey);
		return auth.uploadToken(videoBucketname);
	}
	  /**
	   * 视频转码加水印token 标清
	   * @param fileName
	   * @return
	   */
		  //设置转码操作参数
	public String getVideooUpToken(String fileName){
//		  String watermark = UrlSafeBase64.encodeToString(watermarkURL);
		  // vframe/jpg/offset/7/w/480/h/360
//		  String fops = "avthumb/mp4/ab/128k/ar/44100/acodec/libfaac/r/30/vb/1200k/vcodec/libx264/s/854x480/autoscale/1/stripmeta/0/wmImage/"+watermark+"/wmGravity/NorthEast";
		  String fops = "avthumb/mp4/ab/128k/ar/44100/acodec/libfaac/r/30/vb/1200k/vcodec/libx264/s/854x480/autoscale/1/stripmeta/0";
//		  String fops = "avthumb/mp4/s/640x360/vb/1.25m/wmImage/"+watermark+"/wmGravity/NorthEast";
		  //设置转码的队列
		  String pipeline = "moyouavpipeline";
		  //可以对转码后的文件进行使用saveas参数自定义命名，当然也可以不指定文件会默认命名并保存在当前空间。
		  String urlbase64 = UrlSafeBase64.encodeToString(videooBucketname+":"+fileName);

		  String pfops = fops + "|saveas/"+ urlbase64;
		  Auth auth = Auth.create(accessKey, secretKey);
	      return auth.uploadToken(videooBucketname,null,3600,new StringMap()
	          .putNotEmpty("persistentOps", pfops)
	          .putNotEmpty("persistentPipeline", pipeline), true);
	  }
	  public String getVideooVframeToken(String fileName){
		  //设置转码操作参数
		  //设置转码的队列
		  //可以对转码后的文件进行使用saveas参数自定义命名，当然也可以不指定文件会默认命名并保存在当前空间。
		  
		  String persistentOps = "vframe/jpg/offset/7/w/480/h/360";
		  Auth auth = Auth.create(accessKey, secretKey);
		  return auth.uploadToken(videooBucketname,null,3600,new StringMap()
		  .putNotEmpty("persistentOps", persistentOps), true);
	  }
	/**
	 * 获取认证实体
	 * @return
	 */
	public Auth getAuth() {
		Auth auth = Auth.create(accessKey, secretKey);
		return auth;
	}

	@PostConstruct
	public void init() {
		accessKey = QINIU_ACCESS_KEY;
		secretKey = QINIU_SECRET_KEY;
		imageBucketname = QINIU_BUCKET_IMAGE_NAME;
		videoBucketname = QINIU_BUCKET_VIDEO_NAME;
		videooBucketname = QINIU_BUCKET_VIDEOO_NAME;
		audioBucketname = QINIU_BUCKET_AUDIO_NAME;
		watermarkURL = QINIU_WATERMARK_URL;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	public String getImageBucketname() {
		return imageBucketname;
	}
	public void setImageBucketname(String imageBucketname) {
		this.imageBucketname = imageBucketname;
	}
	public String getVideoBucketname() {
		return videoBucketname;
	}
	public void setVideoBucketname(String videoBucketname) {
		this.videoBucketname = videoBucketname;
	}
	public String getVideooBucketname() {
		return videooBucketname;
	}
	public void setVideooBucketname(String videooBucketname) {
		this.videooBucketname = videooBucketname;
	}
	public String getAudioBucketname() {
		return audioBucketname;
	}
	public void setAudioBucketname(String audioBucketname) {
		this.audioBucketname = audioBucketname;
	}
}