package com.moyou.moyouRms.controller.alipay;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moyou.moyouRms.constants.enums.ResponseEnum;
import com.moyou.moyouRms.controller.BaseController;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.user.UserCountService;
import com.moyou.moyouRms.service.user.UserInfoService;
import com.moyou.moyouRms.service.user.UserService;
import com.moyou.moyouRms.service.userfund.UserFundService;
import com.moyou.moyouRms.util.JsonUtil;
/**
 * @describe  阿里支付相关
 * @author liuxinyi
 * @date 2017年3月13日
 * @email liuxinyi@mousns.com
 * @version 1.0.0
 */
@Controller
@RequestMapping(value = "/biz/alipay")
public class ApplicationGatewayController extends BaseController {

	@Resource
	private UserService userService;
	@Resource
	private UserCountService userCountService;
	@Resource
	private UserInfoService userInfoService;
	@Resource
	private UserFundService userFundService;
	/**
	 * 应用网关
	 * @param callbackData
	 * @return
	 */
	@RequestMapping(value = "/application_gateway", method = RequestMethod.POST)
	public @ResponseBody ApiResult pay_success(@RequestBody Map<String, Object> callbackData) {
//		userFundService.webhooksPaySuccess(callbackData);
		logger.debug(JsonUtil.toJson(callbackData));
		return new ApiResult(ResponseEnum.SUCCESS );
	}
	/**
	 * 应用授权
	 * @param callbackData
	 * @return
	 */
	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public @ResponseBody ApiResult auth(@RequestBody Map<String, Object> callbackData) {
//		userFundService.webhooksPaySuccess(callbackData);
		logger.debug(JsonUtil.toJson(callbackData));
		return new ApiResult(ResponseEnum.SUCCESS );
	}
}
