/**  
 * @Title:  获取用户分享过来的数据内容
 * @Package com.immouo.moyou.service.userreceiveshare.manager   
 * @Description:    TODO
 * @author: liuxinyi
 * @date:   2017年7月19日 下午4:07:20   
 * @email liuxinyi@mousns.com
 * @version V3.4
 */
package com.moyou.moyouRms.urlshareanalysis.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.moyou.moyouRms.constants.CONSTANT;
import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.constants.enums.DiarySourceTypeEnum;
import com.moyou.moyouRms.urlshareanalysis.entity.UserReceiveDataAnalysisResult;
import com.moyou.moyouRms.util.PropertiesUtil;
import com.moyou.moyouRms.util.StringUtil;
import com.moyou.moyouRms.util.file.CMyFileUtils;
import com.moyou.moyouRms.util.file.ImgProperty;

public abstract class UserReceiveShareAbstractManager {
	protected Logger LOG = LoggerFactory.getLogger(this.getClass());
	/**
	 * 加载网页里面的图片张数
	 */
	protected static final int LOAD_IMG_NUMBER = 1;
	/**
	 * 保存图片的最小宽高 300*300
	 */
	protected static final int SAVE_IMAGE_MIN_WIDTH = 300;
	protected static final int SAVE_IMAGE_MIN_HEIGHT = 300;
	/**
	 * 内容简介限制长度
	 */
	protected static final int CONTENT_DESC_MAX_LENGTH = 50;

	/**
	 * 解析html页面内容
	 * 
	 * @param url
	 * @return
	 */
	public abstract UserReceiveDataAnalysisResult analysisByUrl(String url);

	/**
	 * 加载url 获取html代码内容
	 * 
	 * @param url
	 * @return
	 */
	protected String getHtmlLoadUrl(String url) {
		String xmlHtml = "";
		WebClient webClient =null;
		try {
			webClient= new WebClient(BrowserVersion.CHROME);			// 设置webClient的相关参数
			webClient.getOptions().setJavaScriptEnabled(true);
			webClient.getOptions().setCssEnabled(false);
			webClient.setAjaxController(new NicelyResynchronizingAjaxController());
			webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
			webClient.setJavaScriptTimeout(100000);// 设置JS执行的超时时间
			webClient.getOptions().setThrowExceptionOnScriptError(false);// 当JS执行出错的时候是否抛出异常
			webClient.getOptions().setRedirectEnabled(true);
			webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);// 当HTTP的状态非200时是否抛出异常
			webClient.getOptions().setTimeout(30000);// 设置“浏览器”的请求超时时间
			webClient.getOptions().setCssEnabled(false);// 是否启用CSS
			webClient.getOptions().setJavaScriptEnabled(true); // 很重要，启用JS
			webClient.waitForBackgroundJavaScript(100000);// 设置JS后台等待执行时间
			webClient.setAjaxController(new NicelyResynchronizingAjaxController());//设置支持AJAX
		
			
			// 模拟浏览器打开一个目标网址
			HtmlPage rootPage = webClient.getPage(url);
			xmlHtml = rootPage.asXml();
		} catch (FailingHttpStatusCodeException | IOException e) {
			e.printStackTrace();
		}finally{
			webClient.getCurrentWindow().getJobManager().removeAllJobs();
			webClient.close();
		}
		return xmlHtml;
	}

	/**
	 * 根据html页面元素获取图片，没有图片则加上默认图片
	 * 
	 * @param body
	 * @param loadNumber
	 *            加载图片数量
	 * @return
	 */
	protected List<ImgProperty> getImgListByHtml(Element body, int loadNumber,
			DiarySourceTypeEnum diarySourceTypeEnum) {
		List<ImgProperty> imgList = new ArrayList<>();// 图片内容List
		int i = 0;
		if (body != null) {
			switch (diarySourceTypeEnum) {
			case FROM_NETEASY:
				Elements figureEl = body.select("figure.js-img.u-img-placeholder");
				Elements videos = body.select("div.video-holder");
				Elements picAndText =body.select("img");
				if (figureEl != null && figureEl.size() > 0) {
					for (int n = 0; n < figureEl.size(); n++) {
						String imgSrc = figureEl.get(n).attr("data-echo");
						if (StringUtil.isNotEmpty(imgSrc) && !imgSrc.startsWith("http")) {
							imgSrc = "http:" + imgSrc;
						}
						ImgProperty imageProperty = CMyFileUtils.getImgPropertyByUrl(imgSrc);
						if(imageProperty==null){
							break;
						}
						if (imageProperty.getWidth() < SAVE_IMAGE_MIN_WIDTH
								|| imageProperty.getHeight() < SAVE_IMAGE_MIN_HEIGHT) {// 这里获取封面图图片太小，过滤掉
							continue;
						}
						imgList.add(imageProperty);
						i++;
						if (i == loadNumber)
							break;// 最多获取 loadNumber 张图
					}
				} else if (videos != null && videos.size() > 0) {
					String url = "http:" + videos.select("img").attr("src");
					ImgProperty imageProperty = CMyFileUtils.getImgPropertyByUrl(url);
					imgList.add(imageProperty);
					i++;
				}else if(picAndText!=null && picAndText.size()>0){
					for (int n = 0; n < picAndText.size(); n++) {
						String imgSrc = picAndText.get(n).attr("src");
						if (StringUtil.isNotEmpty(imgSrc) && !imgSrc.startsWith("http")) {
							imgSrc = "http:" + imgSrc;
						}
						ImgProperty imageProperty = CMyFileUtils.getImgPropertyByUrl(imgSrc);
						if (imageProperty.getWidth() < SAVE_IMAGE_MIN_WIDTH
								|| imageProperty.getHeight() < SAVE_IMAGE_MIN_HEIGHT) {// 这里获取封面图图片太小，过滤掉
							continue;
						}
						imgList.add(imageProperty);
						i++;
						if (i == loadNumber)
							break;// 最多获取 loadNumber 张图
					}	
				}
				break;
			case FROM_TOUTIAO:
				// 今日头条
				Elements pngs = body.select("img[src~=http|https]");// 文章图片
				Elements gmw  = body.select("p").select("img"); //光明网封面图
//				Elements video = body.select("div.player-inner"); // 视频封面图
				if (pngs.size() != 0 && pngs != null) {
					for (Element e : pngs) {
						String imgSrc = e.attr("src");// 获取img中的src路径
						ImgProperty imageProperty = CMyFileUtils.getImgPropertyByUrl(imgSrc);
						if (imageProperty.getWidth() < SAVE_IMAGE_MIN_WIDTH
								|| imageProperty.getHeight() < SAVE_IMAGE_MIN_HEIGHT) {// 这里获取封面图图片太小，过滤掉
							continue;
						}
						imgList.add(imageProperty);
						i++;
						if (i == loadNumber)
							break;// 最多获取 loadNumber 张图
					}
				}else if (gmw.size() != 0 && gmw != null) {
					for (Element e : gmw) {
						String imgSrc = e.attr("src");// 获取img中的src路径
						imgSrc=imgSrc.replaceAll("../../../", "http://m.gmw.cn/");
						ImgProperty imageProperty = CMyFileUtils.getImgPropertyByUrl(imgSrc);
						if (imageProperty.getWidth() < SAVE_IMAGE_MIN_WIDTH
								|| imageProperty.getHeight() < SAVE_IMAGE_MIN_HEIGHT) {// 这里获取封面图图片太小，过滤掉
							continue;
						}
						imgList.add(imageProperty);
						i++;
						if (i == loadNumber)
							break;// 最多获取 loadNumber 张图
					}
				} 
				break;
				
			default:
				break;
			}
		}
		if (i == 0) {// 一张封面都没有，则随机获取一张图片
			String userReceiveShareDefaultCoverKey = PropertiesUtil
					.getValueByKey("user_receive_share_default_cover_key");
			String userReceiveShareDefaultCoverNumber = PropertiesUtil
					.getValueByKey("user_receive_share_default_cover_number");
			Random ran = new Random();
			String number = userReceiveShareDefaultCoverNumber.split(",")[ran
					.nextInt(userReceiveShareDefaultCoverNumber.split(",").length)];
			String imgSrc = CONSTANT.IMAGE_URL + userReceiveShareDefaultCoverKey + number;
			ImgProperty imageProperty = CMyFileUtils.getImgPropertyByUrl(imgSrc);
			imgList.add(imageProperty);
		}
		return imgList;
	}

}
