package com.moyou.moyouRms.service.user;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.moyou.moyouRms.model.report.CommonResource;
import com.moyou.moyouRms.model.talkrobot.TalkRobot;
import com.moyou.moyouRms.qiniu.service.QiNiuUploadService;
import com.moyou.moyouRms.response.Msg;
import com.moyou.moyouRms.service.BaseJunit4;
import com.moyou.moyouRms.service.report.CommonResourceService;
import com.moyou.moyouRms.service.talkrobot.TalkRobotService;
import com.moyou.moyouRms.util.JsonUtil;
import com.moyou.moyouRms.util.StringUtil;
import com.moyou.moyouRms.util.UUIDUtil;
import com.moyou.moyouRms.util.data.DataframeInDC;
import com.moyou.moyouRms.util.excel.ExcelReadX;

public class TalkVideoAddFromExcelTest extends BaseJunit4 {
	@Autowired
	private QiNiuUploadService qiNiuUploadService;
	@Resource
	private CommonResourceService commonResourceService;
	@Resource
	private TalkRobotService talkRobotService;

	@SuppressWarnings({ "rawtypes"})
	@Test
	@Rollback(false)
	public void insert() {
		ExcelReadX er = new ExcelReadX();
		DataframeInDC dc;
		try {
			/**
			 * start 读取excel 向数据库里插入数据
			 */
			dc = er.converter("E:\\视频说说\\视频说说.xlsx", "Sheet1");
			Map<String, ArrayList<Object>> dataMap = dc.getColValues();

			List number = dataMap.get("序号");
			List contentList = dataMap.get("正文");
			List sexList = dataMap.get("性别");
			List videoList = dataMap.get("视频");
			for(int i=0;i<number.size();i++){
			Object	numb=number.get(i);
			if(StringUtil.isNotEmpty(numb)){
				/**
				 * start 封装说说内容 用excel的序号作为循环次数控制，并且取出数据都为序号-1的值
				 * 例:list.get(numb-1);
				 */
				TalkRobot talkRobot = new TalkRobot();
//				List<File> videoFilelisi t = getFileList("E:\\视频测试数据\\"
//						+ videoList.get(0) + ".mp4");
				File file=new File("E:\\视频说说\\"
						+ videoList.get(i) + ".mp4");
				System.out.println(numb);
				talkRobot.setContent(contentList.get(
						Integer.valueOf(numb + "") - 1).toString());
				int sex = 0;
				if (sexList.get(Integer.valueOf(numb + "") - 1).toString()
						.equals("男"))
					sex = 1;
				talkRobot.setSex(sex);
				talkRobot.setCreateTime(new Date());
				talkRobotService.insertTalkRobot(talkRobot);
				/**
				 * start 封装说说内容
				 */
				List<CommonResource> commonResourceList = new ArrayList<CommonResource>();
				/**
				 * start 封装公共资源
				 */
				if(file!=null){
					Msg msg = qiNiuUploadService.uploadVideoFile(file,
							UUIDUtil.getUUID() + ".mp4");
					if (msg.isSuccess()) {
						CommonResource commonResource = new CommonResource();
						Map<String, Object> map = JsonUtil.toMap(msg.getData());
						commonResource.setUrl(StringUtil.getStr(map
								.get("QINIU_HTTP_URL")));
						commonResource
								.setObjectType(CommonResource.OBJECT_ROBOT_TALK);
						commonResource
								.setMediaType(CommonResource.MEDIATYPE_VIDEO);
						commonResource.setObjectId(talkRobot.getId());
						short picOrder = Short.valueOf(1+"");
						commonResource.setPicOrder(picOrder);
						commonResource.setCreateTime(new Date());
						commonResourceList.add(commonResource);
						commonResourceService
								.insertcommonResource(commonResource);
					}
				}
//				for (int i1=0;i1<videoFilelist.size();i1++) {
//					File	videoFile=	videoFilelist.get(i1);
//					
//				}
//				picFilelist.forEach(picFile -> {
//					Msg msg = qiNiuUploadService.uploadImageFile(picFile,
//							UUIDUtil.getUUID() + ".jpg");
//					if (msg.isSuccess()) {
//						CommonResource commonResource = new CommonResource();
//						Map<String, Object> map = JsonUtil.toMap(msg.getData());
//						commonResource.setUrl(StringUtil.getStr(map
//								.get("QINIU_HTTP_URL")));
//						commonResource
//								.setObjectType(CommonResource.OBJECT_ROBOT_TALK);
//						commonResource
//								.setMediaType(CommonResource.MEDIATYPE_PICTURE);
//						commonResource.setObjectId(talkRobot.getId());
//						short picOrder = Short.valueOf((picFile.getName()
//								.toString().split("\\.")[0]));
//						commonResource.setPicOrder(picOrder);
//						commonResource.setCreateTime(new Date());
//						commonResourceList.add(commonResource);
//						commonResourceService
//								.insertcommonResource(commonResource);
//					}
//				});
				/**
				 * end 封装公共资源
				 */
			}
			}
			/**
			 * end 读取excel 向数据库里插入数据
			 */
			System.err.println("******************************导入完成******************************");
			System.err.println("******************************导入完成******************************");
			System.err.println("******************************导入完成******************************");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<File> getFileList(String strPath) {
		System.out.println(strPath);
		File dir = new File(strPath);
		File[] files = dir.listFiles(); // 该文件目录下文件全部放入数组
		List<File> filelist = new ArrayList<File>();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				String fileName = files[i].getName();
				if (files[i].isDirectory()) { // 判断是文件还是文件夹
					getFileList(files[i].getAbsolutePath()); // 获取文件绝对路径
				} else if (fileName.endsWith("mp4")) { // 判断文件名是否以.avi结尾
					String strFileName = files[i].getAbsolutePath();
					System.out.println("---" + strFileName);
					filelist.add(files[i]);
				} else {
					continue;
				}
			}

		}
		return filelist;
	}

	public static void main(String[] args) {
	}
}
