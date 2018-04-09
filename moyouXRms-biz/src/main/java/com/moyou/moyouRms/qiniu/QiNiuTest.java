package com.moyou.moyouRms.qiniu;


import java.io.IOException;

import com.qiniu.common.Config;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Recorder;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.persistent.FileRecorder;
import com.qiniu.util.Auth;

/**
 * @title 
 * @author liuxinyi
 * @date 2016年6月2日
 * @version 1.0.0
 */

public class QiNiuTest {
	 //设置好账号的ACCESS_KEY和SECRET_KEY
	  private String ACCESS_KEY = "KEWGcIyBb7w6g6XeNP8yQgNYtUXGdgAqr-WmJiJk";
	  private String SECRET_KEY = "gCS5DYd7P4gTPgcmM6BE8TJwTjxzcdYzWACzf_Lj";
	  
	  //要上传的空间
	  String bucketname = "myimagetest";
	  //上传到七牛后保存的文件名
	  String key = "my-java.png";
	  //上传文件的路径
	  String filePath = "/.../...";

	  //密钥配置
	  Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
	  //创建上传对象
	  UploadManager uploadManager = new UploadManager();

	  // 覆盖上传
	  public String getUpToken(){
	    return auth.uploadToken(bucketname);
	  }
	  
	  public void upload() throws IOException{
	    //设置断点记录文件保存在指定文件夹或的File对象
	    String recordPath = "/.../...";
	    //实例化recorder对象
	     Recorder recorder = new FileRecorder(recordPath);
	     //实例化上传对象，并且传入一个recorder对象
	     UploadManager uploadManager = new UploadManager(recorder);

	    try {
	      //调用put方法上传
	    	 Config.zone = Zone.zone1(); 
	    	 String token = getUpToken();
//	    	 String token = "KEWGcIyBb7w6g6XeNP8yQgNYtUXGdgAqr-WmJiJk:XFTnkFs1QQVWq1eVILGQZBGnrOA=:eyJzY29wZSI6Im51bGw6dGFsay9waWMvMTFBQzc1OUEtOTdFOC00ODJFLUEwNjAtNTVFOEM5Mjc3OEZDMTQ5MDA2NDI0MjA3Ml8xLmpwZyIsInJldHVybkJvZHkiOiJ7XCJrZXlcIjogJChrZXkpLCBcImhhc2hcIjogJChldGFnKSwgXCJ3XCI6ICQoaW1hZ2VJbmZvLndpZHRoKSwgXCJoXCI6ICQoaW1hZ2VJbmZvLmhlaWdodCl9IiwiZGVhZGxpbmUiOjE0OTAwNjQ1NDJ9";
	    	 System.out.println(token);
	      Response res = uploadManager.put("G:\\apache-activemq-5.14.4\\webapps\\images\\activemq-logo.png", "d5E8C92778FC1490064242072_112.jpg", token);
	      //打印返回的信息
	      System.out.println(res.bodyString()); 
	      } catch (QiniuException e) {
	          Response r = e.response;
	          // 请求失败时打印的异常的信息
	          System.out.println(r.toString());
	          try {
	              //响应的文本信息
	            System.out.println(r.bodyString());
	          } catch (QiniuException e1) {
	              //ignore
	          }
	      }       
	  }

	  public static void main(String args[]) throws IOException{  
	    new QiNiuTest().upload();
		  
	  }
}
