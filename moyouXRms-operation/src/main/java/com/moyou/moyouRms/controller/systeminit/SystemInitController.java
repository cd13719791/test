package com.moyou.moyouRms.controller.systeminit;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moyou.moyouRms.constants.enums.ResponseEnum;
import com.moyou.moyouRms.controller.BaseController;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.util.JsonUtil;

/**
 * @author created by Chenxv
 * @date 2017年8月30日 上午11:28:56
 */
@RequestMapping("/system_memeory")
public class SystemInitController extends BaseController {

	/**
	 * 查看内存
	 * 
	 * @return
	 */
	@ResponseBody
	public ApiResult systemMemeory() {
		Map<String, Object> map = new HashMap<String, Object>();
		Runtime rt = Runtime.getRuntime();
		map.put("freeMemory", rt.freeMemory() / 1024 / 1024);
		map.put("totalMemory", rt.totalMemory() / 1024 / 1024);
		map.put("maxMemory", rt.maxMemory() / 1024 / 1024);
		super.logger.info(JsonUtil.toJson(map));
		return new ApiResult(ResponseEnum.SUCCESS.getValue(), "成功", JsonUtil.toJson(map));
	}
}
