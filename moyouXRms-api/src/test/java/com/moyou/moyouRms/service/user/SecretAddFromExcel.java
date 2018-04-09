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

import com.moyou.moyouRms.dao.anonymousAvatar.AnonymousAvatarMapper;
import com.moyou.moyouRms.model.diaryContentRobot.DiaryContentRobot;
import com.moyou.moyouRms.model.diaryRobot.DiaryRobot;
import com.moyou.moyouRms.model.secret.SecretContent;
import com.moyou.moyouRms.model.secretRobot.SecretRobot;
import com.moyou.moyouRms.model.secretRobot.SecretRobotContent;
import com.moyou.moyouRms.qiniu.service.QiNiuUploadService;
import com.moyou.moyouRms.response.Msg;
import com.moyou.moyouRms.service.BaseJunit4;
import com.moyou.moyouRms.service.secretrobot.SecretRobotContentService;
import com.moyou.moyouRms.service.secretrobot.SecretRobotService;
import com.moyou.moyouRms.util.JsonUtil;
import com.moyou.moyouRms.util.StringUtil;
import com.moyou.moyouRms.util.UUIDUtil;
import com.moyou.moyouRms.util.data.DataframeInDC;
import com.moyou.moyouRms.util.excel.ExcelReadX;

public class SecretAddFromExcel extends BaseJunit4{
	@Autowired
	private QiNiuUploadService qiNiuUploadService;
	@Resource
	private SecretRobotContentService secretRobotContentService;
	@Resource
	AnonymousAvatarMapper anonymousAvatarMapper;
	@Resource
		private SecretRobotService secretRobotService;
	String rootPath="e:\\秘密数据（4月24日~5月3日）\\";
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
			dc = er.converter(rootPath+"秘密.xls", "Sheet1");
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
			for (int i = 0; i < number.size()-1; i++) {
				if(StringUtil.isEmpty(number.get(i))){
					return;
				}
				SecretRobot dr=new SecretRobot();
				dr.setCreateTime(new Date());
				dr.setSecretTitle(title.get(i)+"");
				dr.setState(DiaryRobot.UNPUBLISH_STATE);
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
//				int avatarId=(int)(Math.random()*6+1);
				dr.setAnonymousAvatarId(anonymousAvatarMapper.selectRandomAvatar().getId());
				dr.setFirstContent(text1.get(i).toString());
				dr.setFirstImage(picture1.get(i).toString());
				File avatorFolder = new File(rootPath+picture1.get(i)+".jpg");
				dr.setExtendData(getImgWidth(avatorFolder)+"_"+getImgHeight(avatorFolder));
				dr.setPushTime(sdf.parse((time.get(i)==null? new Date().toString():time.get(i))+""));
//				List<SecretContent> secretContentList=new ArrayList<SecretContent>();
				int imageTotal=0;
				int commentTotal=0;
				
				{
				if(StringUtil.isNotEmpty(picture1.get(i))){imageTotal++;}
				if(StringUtil.isNotEmpty(picture2.get(i))){imageTotal++;}
				if(StringUtil.isNotEmpty(picture3.get(i))){imageTotal++;}
				if(StringUtil.isNotEmpty(picture4.get(i))){imageTotal++;}
				if(StringUtil.isNotEmpty(picture5.get(i))){imageTotal++;}
//				if(StringUtil.isNotEmpty(text1.get(i))){commentTotal++;}
//				if(StringUtil.isNotEmpty(text2.get(i))){commentTotal++;}
//				if(StringUtil.isNotEmpty(text3.get(i))){commentTotal++;}
//				if(StringUtil.isNotEmpty(text4.get(i))){commentTotal++;}
//				if(StringUtil.isNotEmpty(text5.get(i))){commentTotal++;}
				}
				dr.setImageTotal(imageTotal);
				dr.setCommentTotal(0);
				secretRobotService.insertSelective(dr);
				dr.setFirstContent(createComment(dr,text1.get(i),SecretContent.TEXT,1));	
				dr.setFirstImage(createComment(dr,picture1.get(i),SecretContent.PIC,2));
				createComment(dr,text2.get(i),SecretContent.TEXT,3);
				createComment(dr,picture2.get(i),SecretContent.PIC,4);
				createComment(dr,text3.get(i),SecretContent.TEXT,5);
				createComment(dr,picture3.get(i),SecretContent.PIC,6);
				createComment(dr,text4.get(i),SecretContent.TEXT,7);
				createComment(dr,picture4.get(i),SecretContent.PIC,8);
				createComment(dr,text5.get(i),SecretContent.TEXT,9);
				createComment(dr,picture5.get(i),SecretContent.PIC,10);
				secretRobotService.updateByPrimaryKey(dr);
			}
				/**
				 * end 封装公共资源
				 */
			/**
			 * end 读取excel 向数据库里插入数据
			 */
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
				} else if (fileName.endsWith("jpg")) { // 判断文件名是否以.avi结尾
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
	private String createComment(SecretRobot dr,Object obj ,int type,int sorting){
		if(obj==null||StringUtil.isEmpty(obj)){
			return null;
		}
		Msg msg=null;
		SecretRobotContent dcr=new SecretRobotContent();
		dcr.setCreateTime(dr.getCreateTime());
		dcr.setContentType(type);
		dcr.setSorting(sorting);
		dcr.setSecretId(dr.getId());
		if(type==DiaryContentRobot.PIC){
			File avatorFolder = new File(rootPath+obj+".jpg");
			 msg=uploadPic(avatorFolder);
			if(msg.isSuccess()){
				Map<String, Object> map = JsonUtil.toMap(msg.getData());
				dcr.setTextOrPicture(StringUtil.getStr(map.get("QINIU_HTTP_URL")));
				dcr.setExtendData(getImgWidth(avatorFolder)+"_"+getImgHeight(avatorFolder));
			}
		}else{
			dcr.setTextOrPicture(obj+"");
		}
		secretRobotContentService.insertSelective(dcr);
		return dcr.getTextOrPicture();
	}
	private Msg uploadPic(File picFile){
		Msg msg = qiNiuUploadService.uploadImageFile(picFile,
				UUIDUtil.getUUID() + ".jpg");
		return msg;
	}
	  /** 
     * 获取图片宽度 
     * @param file  图片文件 
     * @return 宽度 
     */  
    public static int getImgWidth(File file) {  
        InputStream is = null;  
        BufferedImage src = null;  
        int ret = -1;  
        try {  
            is = new FileInputStream(file);  
            src = javax.imageio.ImageIO.read(is);  
            ret = src.getWidth(null); // 得到源图宽  
            is.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return ret;  
    }  
        
        
    /** 
     * 获取图片高度 
     * @param file  图片文件 
     * @return 高度 
     */  
    public static int getImgHeight(File file) {  
        InputStream is = null;  
        BufferedImage src = null;  
        int ret = -1;  
        try {  
            is = new FileInputStream(file);  
            src = javax.imageio.ImageIO.read(is);  
            ret = src.getHeight(null); // 得到源图高  
            is.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return ret;  
    }  
}
