/**  
 * @Title:  今日头条页面解析
 * @Package com.immouo.moyou.service.userreceiveshare.manager   
 * @Description:    TODO
 * @author: liuxinyi
 * @date:   2017年7月20日 下午4:07:20   
 * @email liuxinyi@mousns.com
 * @version V3.4
 */
package com.moyou.moyouRms.urlshareanalysis.toutiao;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.fasterxml.jackson.core.type.TypeReference;
import com.moyou.moyouRms.constants.enums.DiarySourceTypeEnum;
import com.moyou.moyouRms.urlshareanalysis.entity.UserReceiveDataAnalysisResult;
import com.moyou.moyouRms.urlshareanalysis.manager.UserReceiveShareAbstractManager;
import com.moyou.moyouRms.util.JsonUtil;
import com.moyou.moyouRms.util.StringUtil;

public class ToutiaoUserReceiveShareManager extends UserReceiveShareAbstractManager {

	@SuppressWarnings("all")
	@Override
	public UserReceiveDataAnalysisResult analysisByUrl(String url) {
		UserReceiveDataAnalysisResult userReceiveShareDataAnalysisResult = new UserReceiveDataAnalysisResult();
		String xmlHtml = getHtmlLoadUrl(url);
		Map<String, Object> map = new HashMap<String, Object>();
		// String videoUrl = "";
		// String picUrl ="";

		// String videoId= "";
		if (StringUtil.isEmpty(xmlHtml)) {
			userReceiveShareDataAnalysisResult
					.setAnalysisState(UserReceiveDataAnalysisResult.ANALYSIS_STATE_FAIL);
		}
		Document parse = Jsoup.parse(xmlHtml);
		String title = parse.title();
		if (StringUtil.isNotEmpty(title)) {
			title = title.replace("- 今日头条(www.toutiao.com)", "").trim();
		}
		Element body = parse.body();
		body.select("img.ceicon").remove();
		String htmlStr = "";
		Elements articleMain = body.select("div.article-content");				// 头条自带文章
		Elements pic = body.select("div.bui-box.gallery-inner").select("img");	//今日头条内置的滑动图片
		Elements youthpic=body.select("figure");								//中国青年网滑动图片,光明网滑动图片
		Elements guanChaArticleMain = body.select("article");					// 头条文章里的观察者网,中国青年网文章,中国经济网,光明网
		Elements huanQiuArticleMain =body.select("div.text").select("p");		//环球网
		Elements huiShengHuoArticleMain=body.select("div.content");             //会生活
		// Elements articleMainPlayer = body.select("div.player-inner");		// 视频
		if (articleMain.size() != 0) {
			htmlStr = articleMain.html();
			userReceiveShareDataAnalysisResult
					.setAnalysisState(UserReceiveDataAnalysisResult.ANALYSIS_STATE_SUCCESS);
			String allContentText = articleMain.text();
			String contentDesc = "";
			int len = CONTENT_DESC_MAX_LENGTH;
			if (StringUtil.isNotEmpty(allContentText)) {
				if (allContentText.length() < len) {
					len = allContentText.length();
				}
				contentDesc = allContentText.substring(0, len);
			}
			userReceiveShareDataAnalysisResult.setContentDesc(contentDesc);
		} else if (guanChaArticleMain.size() != 0) {
			title=title.replace("- 今日头条 -手机光明网", "");
			guanChaArticleMain.select("div#yuanweninfo").remove();
			guanChaArticleMain.select("script").remove();
			htmlStr = guanChaArticleMain.html();
			htmlStr=htmlStr.replaceAll("../../../", "http://m.gmw.cn/");
			userReceiveShareDataAnalysisResult
					.setAnalysisState(UserReceiveDataAnalysisResult.ANALYSIS_STATE_SUCCESS);
			String allContentText = guanChaArticleMain.text();
			String contentDesc = "";
			int len = CONTENT_DESC_MAX_LENGTH;
			if (StringUtil.isNotEmpty(allContentText)) {
				if (allContentText.length() < len) {
					len = allContentText.length();
				}
				contentDesc = allContentText.substring(0, len);
			}
			userReceiveShareDataAnalysisResult.setContentDesc(contentDesc);
		}else if (huanQiuArticleMain.size() != 0) {
			htmlStr = huanQiuArticleMain.html();
			userReceiveShareDataAnalysisResult
					.setAnalysisState(UserReceiveDataAnalysisResult.ANALYSIS_STATE_SUCCESS);
			String allContentText = huanQiuArticleMain.text();
			String contentDesc = "";
			int len = CONTENT_DESC_MAX_LENGTH;
			if (StringUtil.isNotEmpty(allContentText)) {
				if (allContentText.length() < len) {
					len = allContentText.length();
				}
				contentDesc = allContentText.substring(0, len);
			}
			userReceiveShareDataAnalysisResult.setContentDesc(contentDesc);
		}else if (pic.size() != 0 && pic != null) {
				pic.select("img").removeAttr("src");
			Elements picTemp = new Elements();
			for (int n = 0; n < pic.size(); n++) {
				String imgSrc = pic.get(n).attr("data-src");
				if (StringUtil.isNotEmpty(imgSrc)) {
					pic.get(n).append("<img src='" + imgSrc + "'/>");
				} else {
					picTemp.add(pic.get(n));
				}
			}
			pic.removeAll(picTemp);
			Elements picAndText = body.select("script");
			Elements titleList = null;
			for (Element element : picAndText) {
				String script = element.data().toString();
				if (script.contains("gallery")) {
					String subScript = script.substring(
							script.indexOf("gallery:") + "gallery:'".length(),
							script.indexOf("siblingList") - 2);
					subScript = subScript.trim();
					subScript = subScript.substring(0, subScript.length() - 1);
					Map<String, Object> mapSubScript = JsonUtil.toCollection(subScript,
							new TypeReference<Map<String, Object>>() {
							});
					int count = Integer.valueOf(mapSubScript.get("count").toString());
					String str=mapSubScript.get("sub_abstracts").toString();
					str = str.substring(1, str.length() - 1);
					String[] strs = str.split(",");
					@SuppressWarnings("unchecked")
					List<String> text = Arrays.asList(strs);
					text.forEach(System.out::println);
					picAndText=parse.select("body").html("");
					for (int i = 0; i < count; i++) {
						picAndText.append(pic.get(i).toString());
						picAndText.append(	"<p>" + text.get(i) + "</p>");
					}
					break;
				}
			}
			htmlStr = picAndText.html();
			userReceiveShareDataAnalysisResult
					.setAnalysisState(UserReceiveDataAnalysisResult.ANALYSIS_STATE_SUCCESS);
			String allContentText = picAndText.text();
			String contentDesc = "";
			int len = CONTENT_DESC_MAX_LENGTH;
			if (StringUtil.isNotEmpty(allContentText)) {
				if (allContentText.length() < len) {
					len = allContentText.length();
				}
				contentDesc = allContentText.substring(0, len);
			}
			userReceiveShareDataAnalysisResult.setContentDesc(contentDesc);
		}else if (youthpic.size() != 0) {
			htmlStr = youthpic.html();
			userReceiveShareDataAnalysisResult
					.setAnalysisState(UserReceiveDataAnalysisResult.ANALYSIS_STATE_SUCCESS);
			String allContentText = youthpic.text();
			String contentDesc = "";
			int len = CONTENT_DESC_MAX_LENGTH;
			if (StringUtil.isNotEmpty(allContentText)) {
				if (allContentText.length() < len) {
					len = allContentText.length();
				}
				contentDesc = allContentText.substring(0, len);
			}
			userReceiveShareDataAnalysisResult.setContentDesc(contentDesc);
		} else {
			userReceiveShareDataAnalysisResult
					.setAnalysisState(UserReceiveDataAnalysisResult.ANALYSIS_STATE_FAIL);
		}
		userReceiveShareDataAnalysisResult
				.setShareMedia(userReceiveShareDataAnalysisResult.SHARE_MEDIA_ARTICLE);
		userReceiveShareDataAnalysisResult.setImgList(getImgListByHtml(body, LOAD_IMG_NUMBER,
				DiarySourceTypeEnum.FROM_TOUTIAO));
		if (userReceiveShareDataAnalysisResult.getImgList() != null
				&& userReceiveShareDataAnalysisResult.getImgList().size() > 0) {
			map.put("pic", userReceiveShareDataAnalysisResult.getImgList().get(0).getImgUrl());
		}
		userReceiveShareDataAnalysisResult.setContent(htmlStr);
		userReceiveShareDataAnalysisResult.setTitle(title);
		userReceiveShareDataAnalysisResult.setExtendData(JsonUtil.toJson(map));

		return userReceiveShareDataAnalysisResult;
	}

	// @SuppressWarnings("all")
	// public static void main(String[] args) throws Exception {
	// WebClient wc = new WebClient(BrowserVersion.CHROME);
	// try {
	// wc.getOptions().setCssEnabled(true);
	// wc.getOptions().setThrowExceptionOnScriptError(false);
	// wc.getOptions().setJavaScriptEnabled(true);
	// wc.setAjaxController(new NicelyResynchronizingAjaxController());
	// wc.setJavaScriptTimeout(5000);
	// wc.getOptions().setUseInsecureSSL(true);
	// wc.setWebConnection(new DebuggingWebConnection(wc.getWebConnection(),
	// "thingy"));
	// String url = "http://www.toutiao.com/group/6444865527784407566/";
	// HtmlPage p = (HtmlPage)wc.getPage(url);
	// wc.waitForBackgroundJavaScript(4900);
	// Thread.sleep(5000);
	// } finally {
	// wc.close();
	// }
	// }
}
