package com.moyou.moyouRms.qiniu.service;

import java.io.File;

import com.moyou.moyouRms.response.Msg;

/** 
 * 说明： 七牛服务接口
 * 创建人：lxy
 * 创建时间：2016-06-02
 * @version 1.0.0
 */
public interface QiNiuUploadService {
	/**
	 * 上传文件
	 * @param file
	 * @param fileName 同一空间下文件名唯一
	 * @return
	 */
	Msg uploadFile(File file, String fileName) ;
	/**
	 * 上传图片
	 * @param file
	 * @param fileName
	 * @param thumbnail 返回缩略图 缩放比例
	 * @return
	 */
	Msg uploadImageFile(File file, String fileName, int thumbnail) ;
	Msg uploadImageFile(File file, String fileName) ;
	/**
	 * 上传视频不加水印
	 * @param file
	 * @param fileName
	 * @return
	 */
	Msg uploadVideoFile(File file, String fileName) ;
	/**
	 * 上传视频转码标清加水印
	 * @param file
	 * @param fileName
	 * @return
	 */
	Msg uploadVideooFile(File file, String fileName) ;
	/**
	 * 上传音频
	 * @param file
	 * @param fileName
	 * @return
	 */
	Msg uploadAudioFile(File file, String fileName) ;
	/**
	 * 删除图片文件
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	Msg deleteImageFile(String fileName) ;
	/**
	 * 删除音频文件
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	Msg deleteAudioFile(String fileName) ;
	/**
	 * 删除转码视频文件
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	Msg deleteVideooFile(String fileName) ;
}
