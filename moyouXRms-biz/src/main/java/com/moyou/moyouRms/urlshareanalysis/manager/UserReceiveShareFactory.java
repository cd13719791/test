/**  
 * @Title:  获取用户分享过来的数据 工厂操作类
 * @Package com.immouo.moyou.service.userreceiveshare.manager   
 * @Description:    TODO
 * @author: liuxinyi
 * @date:   2017年7月20日 下午4:07:20   
 * @email liuxinyi@mousns.com
 * @version V3.4
 */
package  com.moyou.moyouRms.urlshareanalysis.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.moyou.moyouRms.constants.enums.DiarySourceTypeEnum;
import com.moyou.moyouRms.constants.enums.UserDeviceClientTypeEnum;
import com.moyou.moyouRms.response.Msg;
import com.moyou.moyouRms.urlshareanalysis.entity.UserReceiveDataAnalysisResult;
import com.moyou.moyouRms.urlshareanalysis.neteasy.NeteasyUserReceiveShareManager;
import com.moyou.moyouRms.urlshareanalysis.toutiao.ToutiaoUserReceiveShareManager;

@Component
public  class UserReceiveShareFactory {
	static Integer ANDROID=1;
	static Integer IOS=2;
	protected Logger LOG = LoggerFactory.getLogger(this.getClass());
	/**
	 * 根据字符串是否支持平台
	 * @param str
	 * @param userDeviceClientTypeEnum
	 * @return url
	 */
	public Msg isSupportWeb(String str, UserDeviceClientTypeEnum userDeviceClientTypeEnum) {
		Msg msg = Msg.makeSuccessMsg();
		DiarySourceTypeEnum diarySourceTypeEnum = DiarySourceTypeEnum.getEnumByStr(str, userDeviceClientTypeEnum);		
		if (diarySourceTypeEnum == null) {
			msg.setMsg("不支持此内容分享");	msg.setSuccess(false);
		}
		return msg;
	}
	/**
	 * 根据设备和平台解析出url
	 * @param str
	 * @param userDeviceClientTypeEnum
	 * @return
	 */
	public String getUrl(String str, UserDeviceClientTypeEnum userDeviceClientTypeEnum) {
		String h5Url = "";// 解析内容中的url
		switch (userDeviceClientTypeEnum) {
		case ANDROID:
			h5Url = DiarySourceTypeEnum.getUrlByStr(str, userDeviceClientTypeEnum);
			break;
		case IOS:
			h5Url = str;// 解析内容中的url
			break;
		default:
			break;
		}
		return h5Url;
	}
	/**
	 * 根据设备和平台解析出url
	 * @param str
	 * @param userDeviceClientTypeEnum
	 * @return
	 */
	public String getUrl(String str, Integer type ,Integer urlFromType){
		String h5Url = "";// 解析内容中的url
		switch (type) {
		case 1:
			h5Url = DiarySourceTypeEnum.getUrlForDb(str, type,urlFromType);
			break;
		case 2:
			h5Url = str;// 解析内容中的url
			break;
		default:
			break;
		}
		return h5Url;
	}
	/**
	 * 分析网页
	 * 
	 * @param str
	 * @return
	 */
	public UserReceiveDataAnalysisResult analysisHtml(String str, UserDeviceClientTypeEnum userDeviceClientTypeEnum) {
		DiarySourceTypeEnum diarySourceTypeEnum = DiarySourceTypeEnum.getEnumByStr(str, userDeviceClientTypeEnum);
		String url = getUrl(str, userDeviceClientTypeEnum);
		UserReceiveDataAnalysisResult userReceiveShareDataAnalysisResult = new UserReceiveDataAnalysisResult();
		UserReceiveShareAbstractManager userReceiveShareAbstractManager = new NeteasyUserReceiveShareManager();
		 switch (diarySourceTypeEnum) {
			case FROM_NETEASY:
				userReceiveShareAbstractManager = new NeteasyUserReceiveShareManager();
				userReceiveShareDataAnalysisResult = userReceiveShareAbstractManager.analysisByUrl(url);
				break;
			case FROM_TOUTIAO:
				userReceiveShareAbstractManager = new ToutiaoUserReceiveShareManager();
				userReceiveShareDataAnalysisResult = userReceiveShareAbstractManager.analysisByUrl(url);
				break;
			default:
				break;
			}
		return userReceiveShareDataAnalysisResult;
	}
	/**
	 * 分析网页
	 * 
	 * @param str
	 * @return
	 */
	public UserReceiveDataAnalysisResult analysisHtml(Integer type,String str, UserDeviceClientTypeEnum userDeviceClientTypeEnum) {
		UserReceiveDataAnalysisResult userReceiveShareDataAnalysisResult = new UserReceiveDataAnalysisResult();
		UserReceiveShareAbstractManager userReceiveShareAbstractManager = new NeteasyUserReceiveShareManager();
		 switch (type) {
			case 2:
				userReceiveShareAbstractManager = new NeteasyUserReceiveShareManager();
				userReceiveShareDataAnalysisResult = userReceiveShareAbstractManager.analysisByUrl(str);
				break;
			case 3:
				userReceiveShareAbstractManager = new ToutiaoUserReceiveShareManager();
				userReceiveShareDataAnalysisResult = userReceiveShareAbstractManager.analysisByUrl(str);
				break;
			default:
				break;
			}
		return userReceiveShareDataAnalysisResult;
	}
//	public UserReceiveDataAnalysisResult analysisHtmlForVideo(String str, UserDeviceClientTypeEnum userDeviceClientTypeEnum) {
//		DiarySourceTypeEnum diarySourceTypeEnum = DiarySourceTypeEnum.getEnumByStr(str, userDeviceClientTypeEnum);
//		String url = getUrl(str, userDeviceClientTypeEnum);
//		UserReceiveDataAnalysisResult userReceiveShareDataAnalysisResult = new UserReceiveDataAnalysisResult();
//		UserReceiveShareAbstractManager userReceiveShareAbstractManager = new NeteasyUserReceiveShareManager();
//		 switch (diarySourceTypeEnum) {
//			case FROM_NETEASY:
//				userReceiveShareAbstractManager = new NeteasyUserReceiveShareForVideoManager();
//				userReceiveShareDataAnalysisResult = userReceiveShareAbstractManager.analysisByUrl(url);
//				break;
//			case FROM_TOUTIAO:
//				userReceiveShareAbstractManager = new ToutiaoUserReceiveShareForVideoManager();
//				userReceiveShareDataAnalysisResult = userReceiveShareAbstractManager.analysisByUrl(url);
//				break;
//			default:
//				break;
//			}
//		return userReceiveShareDataAnalysisResult;
//	}
	
}
