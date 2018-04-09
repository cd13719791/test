/**  
 * @Title:  网易新闻页面分析/视频
 * @Package com.immouo.moyou.service.userreceiveshare.manager   
 * @Description:    TODO
 * @author: liuxinyi
 * @date:   2017年7月20日 下午4:07:20    
 * @email liuxinyi@mousns.com
 * @version V3.4
 */
package com.moyou.moyouRms.urlshareanalysis.neteasy;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.moyou.moyouRms.constants.enums.DiarySourceTypeEnum;
import com.moyou.moyouRms.urlshareanalysis.entity.UserReceiveDataAnalysisResult;
import com.moyou.moyouRms.urlshareanalysis.manager.UserReceiveShareAbstractManager;
import com.moyou.moyouRms.util.JsonUtil;
import com.moyou.moyouRms.util.StringUtil;

public class NeteasyUserReceiveShareForVideoManager extends UserReceiveShareAbstractManager {
	
	@SuppressWarnings("all")
	@Override
	public UserReceiveDataAnalysisResult analysisByUrl(String url) {
		UserReceiveDataAnalysisResult userReceiveShareDataAnalysisResult = new UserReceiveDataAnalysisResult();
		String xmlHtml = getHtmlLoadUrl(url);
		String videoUrl="";
		String picUrl="";
		if (StringUtil.isEmpty(xmlHtml)) {
			userReceiveShareDataAnalysisResult.setAnalysisState(UserReceiveDataAnalysisResult.ANALYSIS_STATE_FAIL);
		}
		Document parse = Jsoup.parse(xmlHtml);
		String title = parse.title();
		Element body = parse.body();
		String htmlStr = "";
		Elements articleMain = null;

		Elements videos = parse.select("div.video-holder");//视频
		 if (videos != null && videos.size() > 0) {
			// 去掉不需要的元素
			articleMain=videos.clone();
			articleMain.select("div.end").remove();
			articleMain.select("div.m-video-wifi").remove();
			articleMain.select("a.u-video-open-tip").remove();
			articleMain.select("div.video-subtitle").remove();
			articleMain.select("div.video-btn").remove();
			articleMain.select("div.btn").remove();
			articleMain.select("div.u-play-btn").remove();
			articleMain.select("div.video-desc-wrap").remove();
			articleMain.select("div.video-title").remove();			
			articleMain.select("img[src~=(?i)\\.(png|jpe?g)]").remove();
			if(articleMain.select("video").hasAttr("src")){
				 videoUrl=	articleMain.select("video").attr("src");
				articleMain.select("video").attr("src","http:"+videoUrl);				
			}
			htmlStr = articleMain.html();	
			userReceiveShareDataAnalysisResult.setContent(htmlStr);
			userReceiveShareDataAnalysisResult
					.setAnalysisState(UserReceiveDataAnalysisResult.ANALYSIS_STATE_SUCCESS);
			Map<String,Object> map=new HashMap<String,Object>();
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
		userReceiveShareDataAnalysisResult.setImgList(getImgListByHtml(body,LOAD_IMG_NUMBER, DiarySourceTypeEnum.FROM_NETEASY));	
			return userReceiveShareDataAnalysisResult;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// String url1 =
		// "http://m.zijiecdn.cn/group/6445506807975641614/?iid=12461954640&app=news_article&tt_from=android_share&utm_medium=toutiao_android&utm_campaign=client_share";
		// String groupId = url1.split("group/")[1].substring(0,
		// url1.split("group/")[1].indexOf("/"));
		// String url2 = "http://www.toutiao.com/group/"+groupId;
		String url = "https://c.m.163.com/news/v/VJPBLBAEK.html?spss=newsapp&spsw=1";
		// String url =
		// "https://c.m.163.com/news/v/VHQHVAA90.html?spss=newsapp&spsw=1";
		// String url = "http://www.toutiao.com/group/6444865527784407566/";

		try {
			@SuppressWarnings("resource")
			WebClient webClient = new WebClient(BrowserVersion.FIREFOX_45);
			// 模拟浏览器设置参数
			webClient.getOptions().setJavaScriptEnabled(true);
			webClient.getOptions().setCssEnabled(false);
			webClient.setAjaxController(new NicelyResynchronizingAjaxController());
			// webClient.getOptions().setTimeout(50000);
			webClient.getOptions().setThrowExceptionOnScriptError(false);
			webClient.setJavaScriptTimeout(5000);
			
			HtmlPage rootPage = webClient.getPage(url);
			String html = rootPage.asXml();
			Document parse = Jsoup.parse(html);
			// Elements main=parse.getElementsByClass("video-holder");
			Elements imgs = parse.getElementsByClass("video-wrap js-video");
			// Elements figureEl = parse.select("div.video-wrap js-video");
			for (int i = 0; i < imgs.size(); i++) {
				String imgSrc = imgs.get(i).attr("data-echo");
				imgs.get(i).append("<img src='" + imgSrc + "'/>");
			}
			System.out.println(parse.html());
			// Element body = parse.body();
			System.out.println(html);
		} catch (Exception e) {
		}
	}
}
