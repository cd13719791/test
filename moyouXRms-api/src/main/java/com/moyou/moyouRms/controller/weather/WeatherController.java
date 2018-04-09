package com.moyou.moyouRms.controller.weather;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.response.ApiResult;

@Controller
@ResponseBody
@RequestMapping(value = "http,https://ali-weather.showapi.com")
public class WeatherController {
	@RequestMapping(value = "/day15", method = RequestMethod.GET)
	public @ResponseBody ApiResult queryweather(
			@RequestBody Map<String, String> querys) {
		Map<String, String> headers = new HashMap<String, String>();
		// 最后在header中的格式(中间是英文空格)为Authorization:APPCODE
		// 83359fd73fe94948385f570e3c139105
		headers.put("Authorization", "APPCODE 9292a13dd7ae43d28e213588e96e8957");
		querys.put("area", querys.get("area") + "");
		try {
			/**
			 * 重要提示如下: HttpUtils请从
			 * https://github.com/aliyun/api-gateway-demo-sign
			 * -java/blob/master/src
			 * /main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java 下载
			 *
			 * 相应的依赖请参照
			 * https://github.com/aliyun/api-gateway-demo-sign-java/blob
			 * /master/pom.xml
			 */
		/*	HttpResponse response = HttpUtils.doGet(host, path, method,
					headers, querys);*/
		/*	System.out.println(response.toString());*/
			// 获取response的body
			// System.out.println(EntityUtils.toString(response.getEntity()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  new ApiResult(RESPONSE.SUCCESS,"成功");
	}
}