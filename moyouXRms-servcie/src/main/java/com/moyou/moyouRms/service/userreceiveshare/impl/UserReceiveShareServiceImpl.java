package com.moyou.moyouRms.service.userreceiveshare.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.moyou.moyouRms.dao.diary.DiaryMapper;
import com.moyou.moyouRms.service.diary.impl.DiaryServiceImpl;
import com.moyou.moyouRms.service.userreceiveshare.UserReceiveShareService;
import com.moyou.moyouRms.urlshareanalysis.entity.DiaryH5ShareResult;
import com.moyou.moyouRms.util.JsonUtil;

@Service
public class UserReceiveShareServiceImpl implements UserReceiveShareService {

	@Resource
	private DiaryMapper diaryMapper;
	@Resource
	private DiaryServiceImpl diaryService;
	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(UserReceiveShareServiceImpl.class);

	@SuppressWarnings("all")
	@Override
	@Deprecated
	public String getToutiaoUrl(String videoId) {

		DiaryH5ShareResult diaryH5ShareResult = JsonUtil.toObject(
				diaryMapper.selectExtendDataByvideoId(videoId), DiaryH5ShareResult.class);
		String mapStr = diaryH5ShareResult.getExtendData();
		Map<String, Object> map = JsonUtil.toMap(mapStr);
		videoId = map.get("videoId").toString();
		// DiaryH5ShareResult diaryH5ShareResult = new DiaryH5ShareResult();
		// DiarySourceTypeEnum diarySourceTypeEnum =
		// DiarySourceTypeEnum.getEnumByStr(shareAppName);
		// switch (diarySourceTypeEnum) {
		// case FROM_TOUTIAO:
		// //取出videoId
		// diaryH5ShareResult.setExtend(map);
		// String toutiaoUrl
		// =PropertiesUtil.getValueByKey(CONSTANT.TOUTIAO_HTTP_URL,
		// CONSTANT.SYS_COMMON_PATH);
		// String classPath =
		// ToutiaoUserReceiveShareManager.class.getResource("/").toString();
		// String jsPath = classPath + "toutiao/videoUrl.js";
		// jsPath= jsPath.substring(5);
		// String videoUrl = "";
		// String base64Url ="";
		// toutiaoUrl = toutiaoUrl + videoId; //重构url
		// ScriptEngine engine = new
		// ScriptEngineManager().getEngineByName("javascript");
		// Bindings bind = engine.createBindings();
		// bind.put("factor", 0);
		// engine.setBindings(bind, ScriptContext.ENGINE_SCOPE);
		// try {
		// engine.eval(new FileReader(jsPath));
		// Object js_result = engine.eval("crc32(' + " + toutiaoUrl + "')");
		// base64Url = js_result.toString();
		// Document doc =
		// Jsoup.connect(base64Url).ignoreContentType(true).get();
		// Element newBody = doc.body();
		// String json = newBody.text();
		// // json中main_url为视频地址,通过base64解码得到视频链接
		// String base64 = json.substring(json.indexOf("main_url\":\"") +
		// "main_url\":\"".length(),json.indexOf("\",\"backup_url_1"));// 视频地址
		// byte[] bytes = Base64Utils.decodeFromString(base64);
		// // 视频链接
		// videoUrl = new String(bytes);
		// return diaryH5ShareResult;
		// } catch (Exception e) {
		// // TODO: handle exception
		// }
		// case FROM_NETEASY:
		// diaryH5ShareResult.setExtend(map);
		// return diaryH5ShareResult;
		// default:
		// break;
		// }
		return videoId;
	}
}
