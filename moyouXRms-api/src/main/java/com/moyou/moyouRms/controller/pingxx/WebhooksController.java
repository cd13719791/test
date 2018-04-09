package com.moyou.moyouRms.controller.pingxx;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moyou.moyouRms.constants.CONSTANT;
import com.moyou.moyouRms.constants.enums.ResponseEnum;
import com.moyou.moyouRms.controller.BaseController;
import com.moyou.moyouRms.pingxx.service.ChargeService;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.user.UserCountService;
import com.moyou.moyouRms.service.user.UserInfoService;
import com.moyou.moyouRms.service.user.UserService;
import com.moyou.moyouRms.service.userfund.UserFundService;
import com.moyou.moyouRms.util.JsonUtil;
import com.moyou.moyouRms.util.StringUtil;
/**
 * @describe pingxx webhooks回调通知
 * @author liuxinyi
 * @date 2017年3月13日
 * @email liuxinyi@mousns.com
 * @version 1.0.0
 */
@Controller
@RequestMapping(value = "/biz/pingxx/webhooks")
public class WebhooksController extends BaseController {

	@Resource
	private UserService userService;
	@Resource
	private UserCountService userCountService;
	@Resource
	private UserInfoService userInfoService;
	@Resource
	private UserFundService userFundService;
	@Autowired 
	private HttpServletRequest request;
	@Autowired 
	private ChargeService chargeService;
	
	@RequestMapping(value = "/pay_success", method = RequestMethod.POST)
	public @ResponseBody ApiResult pay_success(@RequestBody String callbackJsonData) {
		String signatureString = request.getHeader("x-pingplusplus-signature");
		
		//  需要判断白名单 
		String whiteIP = CONSTANT.PINGXX_WEBHOOKS_WHITE_IP;
		List<String> whiteIPList = Arrays.asList(whiteIP.split(","));
		String ip = StringUtil.getIpAddr(request);
		if (CONSTANT.APP_FLAG_CURRENT_VALUE.equals(CONSTANT.APP_FLAG_VALUE)) {
			if (! whiteIPList.contains(ip)) {
				logger.error("ping++webhooks  whiteIp intercept");
				return new ApiResult(ResponseEnum.ERROR);
			}
			if (chargeService.verifyWebhooksData(callbackJsonData, signatureString)) {
				userFundService.createWebhooksPaySuccess(JsonUtil.toMap(callbackJsonData));
				return new ApiResult(ResponseEnum.SUCCESS);
			}
		} else {
			userFundService.createWebhooksPaySuccess(JsonUtil.toMap(callbackJsonData));
		}
		return new ApiResult(ResponseEnum.ERROR );
	}
	
	
	
	
	
	
	
}
