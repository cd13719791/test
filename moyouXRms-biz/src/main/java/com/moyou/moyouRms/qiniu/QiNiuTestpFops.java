package com.moyou.moyouRms.qiniu;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import com.qiniu.common.Config;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.qiniu.util.UrlSafeBase64;

public class QiNiuTestpFops {
	// http://o8e03bcmv.bkt.clouddn.com/negoo.png 
  //设置好账号的ACCESS_KEY和SECRET_KEY
	  private String ACCESS_KEY = "KEWGcIyBb7w6g6XeNP8yQgNYtUXGdgAqr-WmJiJk";
	  private String SECRET_KEY = "gCS5DYd7P4gTPgcmM6BE8TJwTjxzcdYzWACzf_Lj";
  //要上传的空间
  String bucketname = "moyou";
  //上传到七牛后保存的文件名
  String key = "my-java.png";
  //上传文件的路径
  String FilePath = "C:\\Users\\liuxinyi\\Desktop\\temp\\dd.mp4";

  //设置转码操作参数
  String watermark = UrlSafeBase64.encodeToString("http://image.immouo.com/webimageb2858b92a3f443b982d075cddb10660d.png");
//  String fops = "avthumb/mp4/ab/128k/ar/44100/acodec/libfaac/r/30/vb/1200k/vcodec/libx264/s/854x480/autoscale/1/stripmeta/0";
  String fops = "avthumb/mp4/s/640x360/vb/1.25m/wmImage/"+watermark+"/wmGravity/NorthEast";
//  String fops = "avthumb/mp4/wmImage/"+watermark+"/wmGravity/Center";
  //设置转码的队列
  String pipeline = "immouoavpipeline";

  //可以对转码后的文件进行使用saveas参数自定义命名，当然也可以不指定文件会默认命名并保存在当前空间。
  String urlbase64 = UrlSafeBase64.encodeToString("videoo:webfile"+new Date().getTime()+".mp4");

  String pfops = fops + "|saveas/"+ urlbase64;
//  String pfops = "avthumb/mp4/wmImage/"+watermark+ "|saveas/"+ urlbase64;;

  //密钥配置
  Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
  //创建上传对象
  UploadManager uploadManager = new UploadManager();

  //上传策略中设置persistentOps字段和persistentPipeline字段
  public String getUpToken(){
      return auth.uploadToken(bucketname,null,3600,new StringMap()
          .putNotEmpty("persistentOps", pfops)
          .putNotEmpty("persistentPipeline", pipeline), true);
  }

  public String getVideooUpToken(String fileName){
	  //设置转码操作参数
	  String watermark = UrlSafeBase64.encodeToString("http://image.immouo.com/webimageb2858b92a3f443b982d075cddb10660d.png");
	  // vframe/jpg/offset/7/w/480/h/360
	  String fops = "avthumb/mp4/ab/128k/ar/44100/acodec/libfaac/r/30/vb/1200k/vcodec/libx264/s/854x480/autoscale/1/stripmeta/0/wmImage/"+watermark+"/wmGravity/NorthEast";
//	  String fops = "avthumb/mp4/vb/1.25m/wmImage/"+watermark+"/wmGravity/NorthEast";
	  //设置转码的队列
	  String pipeline = "immouoavpipeline";
	  //可以对转码后的文件进行使用saveas参数自定义命名，当然也可以不指定文件会默认命名并保存在当前空间。
	  String urlbase64 = UrlSafeBase64.encodeToString("immouovideoo"+":"+fileName);

	  String pfops = fops + "|saveas/"+ urlbase64;
	  Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
      return auth.uploadToken("immouovideoo",null,3600,new StringMap()
          .putNotEmpty("persistentOps", pfops)
          .putNotEmpty("persistentPipeline", pipeline), true);
  }
  public void upload() throws IOException{
    try {
   	 Config.zone = Zone.zone1(); 
      //调用put方法上传
   	 String fileName = "webfile"+new Date().getTime()+".mp4";
 	String upToken = getVideooUpToken(fileName);
 	File file = new File(FilePath);
	Response res = uploadManager.put(file, fileName, upToken);
	
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
    new QiNiuTestpFops().upload();
  }

}