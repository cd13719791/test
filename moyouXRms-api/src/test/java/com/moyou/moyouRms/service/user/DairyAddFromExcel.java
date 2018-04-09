package com.moyou.moyouRms.service.user;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.moyou.moyouRms.model.diaryContentRobot.DiaryContentRobot;
import com.moyou.moyouRms.model.diaryRobot.DiaryRobot;
import com.moyou.moyouRms.model.secretRobot.SecretRobot;
import com.moyou.moyouRms.qiniu.service.QiNiuUploadService;
import com.moyou.moyouRms.response.Msg;
import com.moyou.moyouRms.service.BaseJunit4;
import com.moyou.moyouRms.service.diarycontentrobot.DiaryContentRobotService;
import com.moyou.moyouRms.service.diaryrobot.DiaryRobotService;
import com.moyou.moyouRms.util.JsonUtil;
import com.moyou.moyouRms.util.StringUtil;
import com.moyou.moyouRms.util.UUIDUtil;
import com.moyou.moyouRms.util.data.DataframeInDC;
import com.moyou.moyouRms.util.excel.ExcelReadX;

public class DairyAddFromExcel extends BaseJunit4{
	@Autowired
	private QiNiuUploadService qiNiuUploadService;
	@Resource
	private DiaryContentRobotService diaryContentRobotService; 
	@Resource
	private DiaryRobotService dairyRobotService;
	String rootPath="E:\\故事数据（4月27日~5月3日）\\";
	@SuppressWarnings("all")
	@Test
	@Rollback(false)
	public void insert() {
		ExcelReadX er = new ExcelReadX();
		DataframeInDC dc;
		try {
			/**
			 * start 读取excel 向数据库里插入数据
			 */
			dc = er.converter(rootPath+"故事.xlsx", "Sheet1");
			Map<String, ArrayList<Object>> dataMap = dc.getColValues();

			List number = dataMap.get("序号");
			List title = dataMap.get("标题");
			List text1 = dataMap.get("正文1");
			List picture1 = dataMap.get("图片1");
			List text2 = dataMap.get("正文2");
			List picture2 = dataMap.get("图片2");
			List text3 = dataMap.get("正文3");
			List picture3 = dataMap.get("图片3");
			List text4 = dataMap.get("正文4");
			List text5 = dataMap.get("正文5");
			List picture4 = dataMap.get("图片4");
			List picture5 = dataMap.get("图片5");
			List time = dataMap.get("发布时间");
			List sex = dataMap.get("性别");
			List noName = dataMap.get("是否匿名");
			int index=number.size();
//			for (int i = 0; i < number.size(); i++) {
//				if(StringUtil.isEmpty((number.get(i).toString()))){
//					index=Integer.valueOf(number.get(i-1).toString());
//					break;
//				}
//			}
			for (int i = 0; i < index-1; i++) {
				DiaryRobot dr=new DiaryRobot();
				dr.setCreateTime(new Date());
				dr.setDiaryTitle(title.get(i)+"");
				dr.setPushState(SecretRobot.UNPUBLISH_STATE);
				dr.setState(SecretRobot.PUBLISH_STATE);
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
				dr.setPushTime(sdf.parse((time.get(i)==null? new Date().toString():time.get(i))+""));
				dr.setSex(sex.get(i).toString().equals("男") ? 0:1);
				dr.setSearchContent(text1.get(i)+""+text2.get(i)+text3.get(i)+text4.get(i)+text5.get(i));
				dairyRobotService.insertSelective(dr);
				List<DiaryContentRobot> diaryContentRobotList=new ArrayList<DiaryContentRobot>();
				createComment(dr,text1.get(i),DiaryContentRobot.TEXT,1);
				createComment(dr,picture1.get(i),DiaryContentRobot.PIC,2);
				createComment(dr,text2.get(i),DiaryContentRobot.TEXT,3);
				createComment(dr,picture2.get(i),DiaryContentRobot.PIC,4);
				createComment(dr,text3.get(i),DiaryContentRobot.TEXT,5);
				createComment(dr,picture3.get(i),DiaryContentRobot.PIC,6);
				createComment(dr,text4.get(i),DiaryContentRobot.TEXT,7);
				createComment(dr,picture4.get(i),DiaryContentRobot.PIC,8);
				createComment(dr,text5.get(i),DiaryContentRobot.TEXT,9);
				createComment(dr,picture5.get(i),DiaryContentRobot.PIC,10);
				
			}
				/**
				 * end 封装公共资源
				 */
			/**
			 * end 读取excel 向数据库里插入数据
			 */
			System.err.println("加载完成*************************************日记*");
			System.err.println("加载完成*************************************日记*");
			System.err.println("加载完成*************************************日记*");
			System.err.println("加载完成*************************************日记*");
			System.err.println("加载完成*************************************日记*");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static List<File> getFileList(String strPath) {
		File dir = new File(strPath);
		File[] files = dir.listFiles(); // 该文件目录下文件全部放入数组
		List<File> filelist = new ArrayList<File>();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				String fileName = files[i].getName();
				if (files[i].isDirectory()) { // 判断是文件还是文件夹
					getFileList(files[i].getAbsolutePath()); // 获取文件绝对路径
				} else if (fileName.endsWith("jpg")||fileName.endsWith("jpeg")) { // 判断文件名是否以.avi结尾
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
	@SuppressWarnings("unused")
	private DiaryContentRobot createComment(DiaryRobot dr,Object obj ,int type,int sorting){
		if(obj==null||StringUtil.isEmpty(obj)){
			return null;
		}
		DiaryContentRobot dcr=new DiaryContentRobot();
		dcr.setCreateTime(dr.getCreateTime());
		dcr.setContenType(type);
		dcr.setSorting(sorting);
		dcr.setDiaryRobotId(dr.getId());
		if(type==DiaryContentRobot.PIC){
			File avatorFolder = new File(rootPath+obj+".jpg");
			if(avatorFolder==null){
				avatorFolder=new  File(rootPath+obj+".jpeg");
			}
			Msg msg=uploadPic(avatorFolder);
			if(msg.isSuccess()){
				Map<String, Object> map = JsonUtil.toMap(msg.getData());
				dcr.setTextOrPicture(StringUtil.getStr(map.get("QINIU_HTTP_URL")));
				dcr.setExtendData(getImgWidth(avatorFolder)+"_"+getImgHeight(avatorFolder));
			}
		}else{
			dcr.setTextOrPicture(obj+"");
		}
		diaryContentRobotService.insertSelective(dcr);
		return dcr;
	}
	private Msg uploadPic(File picFile){
		Msg msg = qiNiuUploadService.uploadImageFile(picFile,
				UUIDUtil.getUUID() + ".jpg");
		return msg;
	}
	   public static int getImgHeight(File file) {  
	        InputStream is = null;  
	        BufferedImage src = null;  
	        int ret = -1;  
	        try {  
	            is = new FileInputStream(file);  
	            src = javax.imageio.ImageIO.read(is);  
	            ret = src.getHeight(null); // 寰楀埌婧愬浘楂�  
	            is.close();  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        return ret;  
	    }  
	   public static int getImgWidth(File file) {  
	        InputStream is = null;  
	        BufferedImage src = null;  
	        int ret = -1;  
	        try {  
	            is = new FileInputStream(file);  
	            src = javax.imageio.ImageIO.read(is);  
	            ret = src.getWidth(null); // 寰楀埌婧愬浘瀹�  
	            is.close();  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        return ret;  
	    }  
	        
}
