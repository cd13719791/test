package com.moyou.moyouRms.controller.limitip;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.controller.BaseController;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.limitip.LimitIp;
import com.moyou.moyouRms.model.user.Page;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.limitip.LimitIpService;

@RestController
@RequestMapping(value = "/limitIp")
public class LimitIpController extends BaseController {
	@Resource
	private LimitIpService limitIpService;

	/**
	 * 初始化Ip限制 后台 modetype=1
	 * 
	 * @return
	 */
	@RequestMapping(value = "/querylimitpclist", method = RequestMethod.POST)
	public ApiResult queryLimitPCList(@RequestBody Page page) {
		limitIpService.queryLimitIpList(page);
		return new ApiResult(RESPONSE.SUCCESS, "成功", page);
	}

	/**
	 * 编辑一条IP限制记录 后台 modetype=1
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/updateByPrimaryKeySelective", method = RequestMethod.POST)
	public ApiResult updateByPrimaryKeySelective(@RequestBody LimitIp record) {
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				limitIpService.updateByPrimaryKeySelective(record));
	}

	/**
	 * 删除一条IP限制记录 后台 modetype=1
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/deletebyprimarykey", method = RequestMethod.POST)
	public ApiResult deleteByPrimaryKey(@RequestBody LimitIp record) {
		return new ApiResult(RESPONSE.SUCCESS, "成功", limitIpService.deleteByPrimaryKey(record
				.getId()));
	}

	/**
	 * 添加一条IP限制 后台 modetype=1
	 * 
	 * @param greetingUserSendLog
	 * @return
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ApiResult insert(@RequestBody LimitIp record) {
		return new ApiResult(RESPONSE.SUCCESS, "成功", limitIpService.insert(record));
	}

	/**
	 * 初始化Ip限制 后台 modetype=2
	 * 
	 * @param type
	 *            '1后台2前台' pageNumber pageSize
	 * @return
	 */
	@RequestMapping(value = "/querylimitiplistbytype", method = RequestMethod.POST)
	public ApiResult queryLimitIpListByType(@RequestBody Map<String, Object> map) {
		map.put("type", LimitIp.MODE_TYPE_PHONE);
		PageBean page = this.getJsonWrapPageBean(map);
		limitIpService.queryLimitIpListByType(page);
		return new ApiResult(RESPONSE.SUCCESS, "成功", page);
	}

	/**
	 * 编辑Ip限制 后台 modetype=1
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/updatelimitip", method = RequestMethod.POST)
	public ApiResult updateLimitIpListByType(@RequestBody LimitIp limitIp) {
		return new ApiResult(RESPONSE.SUCCESS, "成功", limitIpService.updatelimitip(limitIp));
	}

	/**
	 * 添加一条IP限制 后台 modetype=2
	 * 
	 * @param greetingUserSendLog
	 * @return
	 */
	@RequestMapping(value = "/insertlimitip", method = RequestMethod.POST)
	public ApiResult insertLimitip(@RequestBody LimitIp record) {
		return new ApiResult(RESPONSE.SUCCESS, "成功", limitIpService.insertLimitip(record));
	}
}
