package com.moyou.moyouRms.urlshareanalysis.service.impl;

import java.io.FileReader;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import com.moyou.moyouRms.constants.CONSTANT;
import com.moyou.moyouRms.constants.enums.DiarySourceTypeEnum;
import com.moyou.moyouRms.urlshareanalysis.service.AnalysisService;
import com.moyou.moyouRms.urlshareanalysis.toutiao.ToutiaoUserReceiveShareManager;
import com.moyou.moyouRms.util.PropertiesUtil;

@Service
public class AnalysisServiceImpl implements AnalysisService {
	private static final Logger LOG = Logger.getLogger(AnalysisServiceImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.moyou.moyouRms.urlshareanalysis.service.AnalysisService#
	 * analysisToutiaoVideoUrl(java.lang.String)
	 */
	@Override
	public String analysisToutiaoVideoUrl(String videoId, DiarySourceTypeEnum diarySourceTypeEnum) {
		String videoUrl = "";
		switch (diarySourceTypeEnum) {
		case FROM_TOUTIAO:
			String toutiaoUrl = PropertiesUtil.getValueByKey(CONSTANT.TOUTIAO_HTTP_URL, CONSTANT.SYS_COMMON_PATH);
			String classPath = ToutiaoUserReceiveShareManager.class.getResource("/").toString();
			String jsPath = classPath + "toutiao/videoUrl.js";
			jsPath = jsPath.substring(5);
			String base64Url = "";
			toutiaoUrl = toutiaoUrl + videoId; // 重构url
			ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
			Bindings bind = engine.createBindings();
			bind.put("factor", 0);
			engine.setBindings(bind, ScriptContext.ENGINE_SCOPE);
			try {
				engine.eval(new FileReader(jsPath));
				Object js_result = engine.eval("crc32(' + " + toutiaoUrl + "')");
				base64Url = js_result.toString();
				Document doc = Jsoup.connect(base64Url).ignoreContentType(true).get();
				Element newBody = doc.body();
				String json = newBody.text();
				// json中main_url为视频地址,通过base64解码得到视频链接
				String base64 = json.substring(json.indexOf("main_url\":\"") + "main_url\":\"".length(),
						json.indexOf("\",\"backup_url_1"));// 视频地址
				byte[] bytes = Base64Utils.decodeFromString(base64);
				// 视频链接
				videoUrl = new String(bytes);
				return videoUrl;
			} catch (Exception e) {
				LOG.debug(e.getMessage());
			}

		default:
			return videoUrl;
		}
	}
}
