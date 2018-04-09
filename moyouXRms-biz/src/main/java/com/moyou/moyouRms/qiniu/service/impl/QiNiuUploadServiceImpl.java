package com.moyou.moyouRms.qiniu.service.impl;

import java.io.File;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.moyou.moyouRms.qiniu.entity.QiNiuUploadFileConfing;
import com.moyou.moyouRms.qiniu.service.QiNiuUploadService;
import com.moyou.moyouRms.response.Msg;
import com.moyou.moyouRms.util.JsonUtil;
import com.qiniu.common.Config;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

@Service
public class QiNiuUploadServiceImpl implements QiNiuUploadService {
	protected Logger logger = Logger.getLogger(getClass());
	@Autowired
	private QiNiuUploadFileConfing qiNiuUploadFileConfing;
	@Value("#{configProperties['QINIU_HTTP_IMAGE_URL']}")
	private String QINIU_HTTP_IMAGE_URL;
	@Value("#{configProperties['QINIU_IMAGE_PREFIX']}")
	private String QINIU_IMAGE_PREFIX;
	@Value("#{configProperties['QINIU_VIDEO_PREFIX']}")
	private String QINIU_VIDEO_PREFIX;
	@Value("#{configProperties['QINIU_HTTP_VIDEO_URL']}")
	private String QINIU_HTTP_VIDEO_URL;
	@Value("#{configProperties['QINIU_HTTP_VIDEOO_URL']}")
	private String QINIU_HTTP_VIDEOO_URL;
	@Value("#{configProperties['QINIU_VIDEOO_PREFIX']}")
	private String QINIU_VIDEOO_PREFIX;
	@Value("#{configProperties['QINIU_AUDIO_PREFIX']}")
	private String QINIU_AUDIO_PREFIX;
	@Value("#{configProperties['QINIU_HTTP_AUDIO_URL']}")
	private String QINIU_HTTP_AUDIO_URL;
	@Value("#{configProperties['QINIU_HTTP_IMAGE_THUMBNAIL']}")
	private String QINIU_HTTP_IMAGE_THUMBNAIL;

	@Value("#{configProperties['QINIU_FILE_PREFIX']}")
	private String QINIU_FILE_PREFIX;

	@SuppressWarnings("all")
	@Override
	public Msg uploadFile(File file, String fileName) {
		fileName = QINIU_FILE_PREFIX + fileName;
		Msg msg = Msg.makeMsg();
		Config.zone = Zone.zone1();
		// 创建上传对象
		UploadManager uploadManager = new UploadManager();
		String token = qiNiuUploadFileConfing.getUpToken();
		try {
			Response res = uploadManager.put(file, fileName, token);
			String resJson = res.bodyString();
			Map dataMap = JsonUtil.toMap(resJson);
			dataMap.put("QINIU_HTTP_URL", QINIU_HTTP_IMAGE_URL + fileName);
			msg.setData(JsonUtil.toJson(dataMap));
		} catch (QiniuException e) {
			Response r = e.response;
			// 请求失败时打印的异常的信息
			msg.setMsg(r.error);
			msg.setSuccess(false);
			logger.error("七牛上传 QiNiuUploadResult 失败", e);
		}
		return msg;
	}
	@SuppressWarnings("all")
	@Override
	public Msg uploadAudioFile(File file, String fileName) {
		fileName = QINIU_AUDIO_PREFIX + fileName;
		Msg msg = Msg.makeMsg();
		Config.zone = Zone.zone1();
		// 创建上传对象
		UploadManager uploadManager = new UploadManager();
		String token = qiNiuUploadFileConfing.getAudioUpToken();
		try {
			Response res = uploadManager.put(file, fileName, token);
			String resJson = res.bodyString();
			Map dataMap = JsonUtil.toMap(resJson);
			dataMap.put("QINIU_HTTP_URL", QINIU_HTTP_AUDIO_URL + fileName);
			msg.setData(JsonUtil.toJson(dataMap));
		} catch (QiniuException e) {
			Response r = e.response;
			// 请求失败时打印的异常的信息
			msg.setMsg(r.error);
			msg.setSuccess(false);
			logger.error("七牛上传 QiNiuUploadResult 失败", e);
		}
		return msg;
	}
	@SuppressWarnings("all")
	@Override
	public Msg uploadImageFile(File file, String fileName, int thumbnail) {
		fileName = QINIU_IMAGE_PREFIX + fileName;
		// http://imageqn.51ts.cn/44bf78bc7e7a4bc7be792010b9fe5598.png?imageMogr2/thumbnail/!50p
		Msg msg = Msg.makeMsg();
		Config.zone = Zone.zone1();
		// 创建上传对象
		UploadManager uploadManager = new UploadManager();
		String token = qiNiuUploadFileConfing.getImageUpToken();
		try {
			Response res = uploadManager.put(file, fileName, token);
			String resJson = res.bodyString();
			Map dataMap = JsonUtil.toMap(resJson);
			dataMap.put("QINIU_HTTP_URL", QINIU_HTTP_IMAGE_URL + fileName);
			dataMap.put("QINIU_THUMBNAIL_HTTP_URL", QINIU_HTTP_IMAGE_URL + fileName + QINIU_HTTP_IMAGE_THUMBNAIL);
			msg.setData(JsonUtil.toJson(dataMap));
		} catch (QiniuException e) {
			Response r = e.response;
			// 请求失败时打印的异常的信息
			msg.setMsg(r.error);
			msg.setSuccess(false);
			logger.error("七牛上传 QiNiuUploadResult 失败", e);
		}
		return msg;
	}
	@SuppressWarnings("all")
	@Override
	public Msg uploadImageFile(File file, String fileName) {
		fileName = QINIU_IMAGE_PREFIX + fileName;
		// http://imageqn.51ts.cn/44bf78bc7e7a4bc7be792010b9fe5598.png?imageMogr2/thumbnail/!50p
		Msg msg = Msg.makeMsg();
		Config.zone = Zone.zone1();
		// 创建上传对象
		UploadManager uploadManager = new UploadManager();
		String token = qiNiuUploadFileConfing.getImageUpToken();
		try {
			Response res = uploadManager.put(file, fileName, token);
			String resJson = res.bodyString();
			Map dataMap = JsonUtil.toMap(resJson);
			dataMap.put("QINIU_HTTP_URL", QINIU_HTTP_IMAGE_URL + fileName);
			msg.setData(JsonUtil.toJson(dataMap));
		} catch (QiniuException e) {
			Response r = e.response;
			// 请求失败时打印的异常的信息
			msg.setMsg(r.error);
			msg.setSuccess(false);
			logger.error("七牛上传 QiNiuUploadResult 失败", e);
		}
		return msg;
	}
	@SuppressWarnings("all")
	@Override
	public Msg uploadVideooFile(File file, String fileName) {
		Config.zone = Zone.zone1();
		fileName = QINIU_VIDEOO_PREFIX + fileName+".mp4";

		Msg msg = Msg.makeMsg();
		Config.zone = Zone.zone1();
		// 创建上传对象
		UploadManager uploadManager = new UploadManager();
		try {
			String upToken = qiNiuUploadFileConfing.getVideooUpToken(fileName);
			Response res = uploadManager.put(file, fileName, upToken);
			String resJson = res.bodyString();
			Map dataMap = JsonUtil.toMap(resJson);
			dataMap.put("QINIU_HTTP_URL", QINIU_HTTP_VIDEOO_URL + fileName);
			msg.setData(JsonUtil.toJson(dataMap));
		} catch (QiniuException e) {
			Response r = e.response;
			// 请求失败时打印的异常的信息
			msg.setMsg(r.error);
			msg.setSuccess(false);
			logger.error("七牛上传 QiNiuUploadResult 失败", e);
		}
		return msg;
	}
	@SuppressWarnings("all")
	@Override
	public Msg uploadVideoFile(File file, String fileName) {
		Config.zone = Zone.zone1();
		fileName = QINIU_VIDEO_PREFIX + fileName+".mp4";

		Msg msg = Msg.makeMsg();
		Config.zone = Zone.zone1();
		// 创建上传对象
		UploadManager uploadManager = new UploadManager();
		try {
			String upToken = qiNiuUploadFileConfing.getVideoUpToken();
			Response res = uploadManager.put(file, fileName, upToken);
			String resJson = res.bodyString();
			Map dataMap = JsonUtil.toMap(resJson);
			dataMap.put("QINIU_HTTP_URL", QINIU_HTTP_VIDEO_URL + fileName);
			msg.setData(JsonUtil.toJson(dataMap));
		} catch (QiniuException e) {
			Response r = e.response;
			// 请求失败时打印的异常的信息
			msg.setMsg(r.error);
			msg.setSuccess(false);
			logger.error("七牛上传 QiNiuUploadResult 失败", e);
		}
		return msg;
	}

	@Override
	public Msg deleteImageFile(String fileName) {
		Config.zone = Zone.zone1();
		Auth auth = qiNiuUploadFileConfing.getAuth();
		// 实例化一个BucketManager对象
		BucketManager bucketManager = new BucketManager(auth);
		Msg msg = Msg.makeMsg();
		try {
			// 调用delete方法移动文件
			bucketManager.delete(qiNiuUploadFileConfing.getImageBucketname(), fileName);
		} catch (QiniuException e) {
			// 捕获异常信息
			Response r = e.response;
			msg.setMsg(r.error);
			msg.setSuccess(false);
		}
		return msg;
	}

	@Override
	public Msg deleteAudioFile(String fileName) {
		Config.zone = Zone.zone1();
		Auth auth = qiNiuUploadFileConfing.getAuth();
		// 实例化一个BucketManager对象
		BucketManager bucketManager = new BucketManager(auth);
		Msg msg = Msg.makeMsg();
		try {
			// 调用delete方法移动文件
			bucketManager.delete(qiNiuUploadFileConfing.getAudioBucketname(), fileName);
		} catch (QiniuException e) {
			// 捕获异常信息
			Response r = e.response;
			msg.setMsg(r.error);
			msg.setSuccess(false);
		}
		return msg;
	}

	@Override
	public Msg deleteVideooFile(String fileName) {
		Config.zone = Zone.zone1();
		Auth auth = qiNiuUploadFileConfing.getAuth();
		// 实例化一个BucketManager对象
		BucketManager bucketManager = new BucketManager(auth);
		Msg msg = Msg.makeMsg();
		try {
			// 调用delete方法移动文件
			bucketManager.delete(qiNiuUploadFileConfing.getVideooBucketname(), fileName);
		} catch (QiniuException e) {
			// 捕获异常信息
			Response r = e.response;
			msg.setMsg(r.error);
			msg.setSuccess(false);
		}
		return msg;
	}
	

}
