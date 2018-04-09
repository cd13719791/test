package com.moyou.moyouRms.controller.h5;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.controller.BaseController;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.liveshow.LiveUserInfoService;

/**
 * @author created by Chenxv
 * @date 2017年9月28日 下午2:27:23
 */
@Controller
@RequestMapping("h5/liveshow/")
public class H5LiveController extends BaseController {
	@Resource
	private LiveUserInfoService liveUserInfoService;

	/**
	 * 查询分享直播页数据
	 * 
	 * @param blessingId
	 * @return
	 */
	@RequestMapping(value = "/queryLiveShow", method = RequestMethod.POST)
	public @ResponseBody ApiResult queryBlessingDetails(@RequestBody Map<String, Object> map) {
		return new ApiResult(RESPONSE.SUCCESS, "成功", liveUserInfoService.queryH5ShareLiveShow(map));
	}
}
