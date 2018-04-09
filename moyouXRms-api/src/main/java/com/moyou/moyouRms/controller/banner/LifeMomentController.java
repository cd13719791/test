package com.moyou.moyouRms.controller.banner;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.controller.BaseController;
import com.moyou.moyouRms.model.banner.Banner;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.banner.LifeMomentService;
import com.moyou.moyouRms.util.JsonUtil;

/**
 * 生活圈首页 Controller
 * 
 * @author PzC.
 * @time 2017年2月27日 下午5:01:52
 * 
 */
@RestController
@RequestMapping(value = "/life")
public class LifeMomentController extends BaseController {
	@Resource
	private LifeMomentService lifeMomentService;

	@RequestMapping(value = "/queryBanner", method = RequestMethod.GET)
	public ApiResult queryBanner() {
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				lifeMomentService.queryBanner());
	}

	@RequestMapping(value = "/deleteBanner", method = RequestMethod.POST)
	public ApiResult deleteBanner(@RequestBody String id) {
		String processId = JsonUtil.toObject(id, HashMap.class).get("id")
				.toString();
		Integer it = Integer.valueOf(processId);
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				lifeMomentService.deleteBanner(it));
	}

	@RequestMapping(value = "/insertBanner", method = RequestMethod.POST)
	public ApiResult insertBanner(@RequestBody Banner banner) {
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				lifeMomentService.insertBanner(banner));
	}

	/**
	 * 启用/禁用banner
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/updateState", method = RequestMethod.POST)
	public ApiResult updateState(@RequestBody Banner banner) {
		// banner状态 0.停用未推荐1.启用推荐
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				lifeMomentService.updateState(banner));
	}

	@RequestMapping(value = "/queryBannerInfo", method = RequestMethod.POST)
	public ApiResult queryBannerInfo(@RequestBody String id) {
		String processId = JsonUtil.toObject(id, HashMap.class).get("id")
				.toString();
		Integer it = Integer.valueOf(processId);
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				lifeMomentService.queryBannerInfo(it));
	}

	/**
	 * 修改banner排序
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/updateOrderNo", method = RequestMethod.POST)
	public ApiResult updateOrderNo(@RequestBody Banner banner) {
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				lifeMomentService.updateOrderNo(banner));
	}
	 /**
     * 初始化直播banner
     * 
     * @return
     */
    @RequestMapping(value = "/querylivebanner", method = RequestMethod.POST)
    public ApiResult queryLiveBanner()
    {
        return new ApiResult(RESPONSE.SUCCESS, "成功", lifeMomentService.queryLiveBanner());
    }

}
