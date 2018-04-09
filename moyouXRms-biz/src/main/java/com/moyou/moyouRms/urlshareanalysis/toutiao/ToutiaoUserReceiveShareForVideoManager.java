/**  
 * @Title:  今日头条页面解析/视频
 * @Package com.immouo.moyou.service.userreceiveshare.manager   
 * @Description:    TODO
 * @author: liuxinyi
 * @date:   2017年7月20日 下午4:07:20   
 * @email liuxinyi@mousns.com
 * @version V3.4
 */
package  com.moyou.moyouRms.urlshareanalysis.toutiao;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.Base64Utils;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.DebuggingWebConnection;
import com.moyou.moyouRms.constants.CONSTANT;
import com.moyou.moyouRms.constants.enums.DiarySourceTypeEnum;
import com.moyou.moyouRms.urlshareanalysis.entity.UserReceiveDataAnalysisResult;
import com.moyou.moyouRms.urlshareanalysis.manager.UserReceiveShareAbstractManager;
import com.moyou.moyouRms.util.JsonUtil;
import com.moyou.moyouRms.util.PropertiesUtil;
import com.moyou.moyouRms.util.StringUtil;

public  class ToutiaoUserReceiveShareForVideoManager extends UserReceiveShareAbstractManager {

	@SuppressWarnings("all")
	@Override
	public UserReceiveDataAnalysisResult analysisByUrl(String url) {
		UserReceiveDataAnalysisResult userReceiveShareDataAnalysisResult = new UserReceiveDataAnalysisResult();
		String xmlHtml = getHtmlLoadUrl(url);
	   	Map<String,Object> map=new HashMap<String,Object>();
		String videoUrl = "";
		String picUrl ="";
		if (StringUtil.isEmpty(xmlHtml)) {
			userReceiveShareDataAnalysisResult.setAnalysisState(UserReceiveDataAnalysisResult.ANALYSIS_STATE_FAIL);
		}
		Document parse = Jsoup.parse(xmlHtml);
		String title = parse.title();
		if (StringUtil.isNotEmpty(title)) {
			title = title.replace("- 今日头条(www.toutiao.com)", "").trim();
		}
		Element body = parse.body();
		String htmlStr = "";
		Elements articleMainPlayer = body.select("div.player-inner");// 视频
			if(articleMainPlayer.size()!=0 && articleMainPlayer != null){
        	String toutiao_url=PropertiesUtil.getValueByKey(CONSTANT.TOUTIAO_HTTP_URL, CONSTANT.SYS_COMMON_PATH);
//        	String befoCrc = "http://ib.365yg.com/video/urls/v/1/toutiao/mp4/";
            String classPath = ToutiaoUserReceiveShareManager.class.getResource("/").toString();
            String jsPath = classPath + "js/videourl.js";   
            jsPath = jsPath.substring(5);
            String base64Url = "";          
            try {
            	 Elements e = parse.getElementsByTag("script"); //通过script标签找到videoId的值
                 String videoId = "";
                 for (Element element : e) {
                 	String script = element.data().toString();
                 	if(script.contains("videoid")){
                 		String subScript = script.substring(script.indexOf("videoid:'")+"videoid:'".length(),script.indexOf("share_url"));
                 		videoId=subScript.substring(0, subScript.indexOf(","));
                 		break;
                 	}else if(script.contains("videoId")){
                 		int index = script.indexOf("videoId:");
                 		int length = "videoId:'".length();
                 		videoId=script.substring(index+length+1, index+length+33);
                 		break; 		
                 	}
     			}
                toutiao_url= toutiao_url + videoId;  //重构url
                ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
                Bindings bind =engine.createBindings();
                bind.put("factor", 0);
                engine.setBindings(bind, ScriptContext.ENGINE_SCOPE);               
                engine.eval(new FileReader(jsPath));
                Object js_result = engine.eval("crc32(' + " + toutiao_url + "')");
                base64Url = js_result.toString();
                Document doc = Jsoup.connect(base64Url).ignoreContentType(true).get();
                Element newBody = doc.body();
                String json =newBody.text();
                //json中main_url为视频地址,通过base64解码得到视频链接
                String base64 = json.substring(json.indexOf("main_url\":\"")+"main_url\":\"".length(),json.indexOf("\",\"backup_url_1"));//视频地址
                picUrl = json.substring(json.indexOf("poster_url\":\"")+"poster_url\":\"".length(),json.indexOf("\",\"video_duration"));//封面图片
                byte[] bytes=Base64Utils.decodeFromString(base64);
                //视频链接
                videoUrl = new String(bytes);
                if(articleMainPlayer.select("source").hasAttr("src")){
                	articleMainPlayer.select("source").attr("src",videoUrl);//页面地址换为base64解码后的链接               	
                }
			}            
        	catch (Exception e) {
				e.printStackTrace();
			}    	
        	body.select("img[src~=http|https]").remove();//去掉多余的图片，头条每次都会丢一个头像图片进来
        	body.select("hi.abs-title").remove();
        	for (int n = 0; n < articleMainPlayer.size(); n++) {  //添加封面图到content
        		articleMainPlayer.get(n).attr("player-inner");
				articleMainPlayer.get(n).append("<img src='" + picUrl + "'/>");
			}
        	   	htmlStr = articleMainPlayer.html();
        	   	userReceiveShareDataAnalysisResult.setContent(htmlStr);
        	   	map.put("video", videoUrl);
        	   	map.put("pic", picUrl);
        	   	userReceiveShareDataAnalysisResult.setExtendData(JsonUtil.toJson(map));
        	   	userReceiveShareDataAnalysisResult.setAnalysisState(UserReceiveDataAnalysisResult.ANALYSIS_STATE_SUCCESS); 
        	   	userReceiveShareDataAnalysisResult.setShareMedia(userReceiveShareDataAnalysisResult.SHARE_MEDIA_VIDEO);
       } else {
			userReceiveShareDataAnalysisResult.setAnalysisState(UserReceiveDataAnalysisResult.ANALYSIS_STATE_FAIL);
		}
		userReceiveShareDataAnalysisResult.setContent(htmlStr);
		userReceiveShareDataAnalysisResult.setTitle(title);
		userReceiveShareDataAnalysisResult.setImgList(getImgListByHtml(body, LOAD_IMG_NUMBER, DiarySourceTypeEnum.FROM_TOUTIAO));	
		return userReceiveShareDataAnalysisResult;
	
	}
	
	public static void main(String[] args) throws Exception {
        WebClient wc = new WebClient(BrowserVersion.CHROME);
        try {
            wc.getOptions().setCssEnabled(true);
            wc.getOptions().setThrowExceptionOnScriptError(false);
            wc.getOptions().setJavaScriptEnabled(true);
            wc.setAjaxController(new NicelyResynchronizingAjaxController());
            wc.setJavaScriptTimeout(5000);
            wc.getOptions().setUseInsecureSSL(true);            
            wc.setWebConnection(new DebuggingWebConnection(wc.getWebConnection(), "thingy"));
        	String url = "http://www.toutiao.com/group/6444865527784407566/";
            HtmlPage p = (HtmlPage)wc.getPage(url);
            wc.waitForBackgroundJavaScript(4900);
            Thread.sleep(5000);
            System.out.println(p.asXml());
        } finally {
            wc.close();
        }
    }
}
