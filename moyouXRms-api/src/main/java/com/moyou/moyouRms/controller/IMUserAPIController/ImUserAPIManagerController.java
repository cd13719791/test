package com.moyou.moyouRms.controller.IMUserAPIController;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.moyou.moyouRms.constants.enums.ResponseEnum;
import com.moyou.moyouRms.controller.BaseController;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.po.system.account.Account;
import com.moyou.moyouRms.model.report.CommonResource;
import com.moyou.moyouRms.model.sysTalkManagerInfo.SysTalkManagerInfo;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.report.CommonResourceService;
import com.moyou.moyouRms.service.sysadminrelateuser.SysAdminRelateUserService;
import com.moyou.moyouRms.service.system.account.AccountService;
@RestController
@RequestMapping(value = "/IMUserAPIManager")
public class ImUserAPIManagerController extends BaseController{
	@Resource
	SysAdminRelateUserService sysAdminRelateUserService;
	@Resource
	private AccountService accountService;
	@Resource
	private CommonResourceService commonResourceService;
	/**
	 * 初始化单聊账号列表
	 * 
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/queryAccountByType", method = RequestMethod.POST)
	public ApiResult queryAccountByType(@RequestBody Map<String, Object> map) {
		map.put("type", Account.TALK_MANAGER);
		PageBean pb = this.getJsonWrapPageBean(map);
		List<SysTalkManagerInfo> list = accountService
				.querySysTalkManagerInfoByType(pb);
		pb.setResults(list);
		return new ApiResult(ResponseEnum.SUCCESS.getValue(), "成功", pb);
	}
	/**
	 * 修改账号绑定假用户
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/updateaccountuser", method = RequestMethod.POST)
	public ApiResult updateAccountUser(@RequestBody Map<String, Object> map) {
		Assert.notNull(map.get("accountId"));
		Assert.notNull(map.get("userNumberStart"));
		Assert.notNull(map.get("userNumberEnd"));
		String accountId = map.get("accountId").toString();
		Integer userNumberStart = Integer.valueOf(map.get("userNumberStart").toString());
		Integer userNumberEnd = Integer.valueOf(map.get("userNumberEnd").toString());
		return sysAdminRelateUserService.updateAccountUser(userNumberStart,userNumberEnd, accountId);
	}
	/**
	 * 清空未读消息
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/updateaccountofflinecount", method = RequestMethod.POST)
	public ApiResult updateAccountOfflineCount(@RequestBody Map<String, Object> map) {
		Assert.notNull(map.get("accountId"));
		return new ApiResult(ResponseEnum.SUCCESS.getValue(), "成功",sysAdminRelateUserService.updateAccountOfflineCount(map.get("accountId").toString()));
	}
	/**
	 * 修改文案
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/updateatext", method = RequestMethod.POST)
	public ApiResult updateText(@RequestBody Map<String, Object> map) {
		Assert.notNull(map.get("text"));
		return new ApiResult(ResponseEnum.SUCCESS.getValue(), "成功",commonResourceService.updateCommonResourceExtendData((int) CommonResource.OBJECT_TYPE_CHAT,map.get("text").toString()));
	}
	/**
	 * 初始化单聊文案
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/selecttext", method = RequestMethod.POST)
	public ApiResult selectText() {
		return new ApiResult(ResponseEnum.SUCCESS.getValue(), "成功",commonResourceService.selectCommonResourceByObjectType(new CommonResource(CommonResource.OBJECT_TYPE_CHAT,"")));
	}
}
