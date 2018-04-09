package com.moyou.moyouRms.controller.IMUserAPIController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.easemob.server.api.IMUserAPI;
import com.easemob.server.comm.ClientContext;
import com.easemob.server.comm.EasemobRestAPIFactory;
import com.moyou.moyouRms.constants.Const;
import com.moyou.moyouRms.constants.enums.ResponseEnum;
import com.moyou.moyouRms.controller.BaseController;
import com.moyou.moyouRms.dao.sysTalkManagerInfo.SysTalkManagerInfoMapper;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.chatRevert.ChatRevert;
import com.moyou.moyouRms.model.po.system.account.Account;
import com.moyou.moyouRms.model.report.CommonResource;
import com.moyou.moyouRms.model.sysAdminRelateUser.SysAdminRelateUser;
import com.moyou.moyouRms.model.sysTalkManagerInfo.SysTalkManagerInfo;
import com.moyou.moyouRms.model.user.UserMsgInfo;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.chatrevert.ChatRevertService;
import com.moyou.moyouRms.service.report.CommonResourceService;
import com.moyou.moyouRms.service.sysadminrelateuser.SysAdminRelateUserService;
import com.moyou.moyouRms.service.systalkmanagerinfo.SysTalkManagerinfoService;
import com.moyou.moyouRms.service.system.account.AccountService;
import com.moyou.moyouRms.service.system.account.AccountShiroUtil;
import com.moyou.moyouRms.service.user.UserInfoService;
import com.moyou.moyouRms.util.JsonUtil;

@RestController
@RequestMapping(value = "/IMUserAPI")
public class IMUserAPIController extends BaseController {
	@Resource
	private AccountService accountService;
	@Resource
	SysAdminRelateUserService sysAdminRelateUserService;
	@Resource
	UserInfoService userInfoService;
	@Resource
	SysTalkManagerInfoMapper sysTalkManagerInfoMapper;
	@Resource
	SysTalkManagerinfoService sysTalkManagerInfoService;
	@Resource
	private CommonResourceService commonResourceService;
	// 常用语 相关
	@Resource
	ChatRevertService chatRevertService;

	/**
	 * 查询离线消息数量 {"userName":"94093ad137d441d29605f1b99ffa0a07"}
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/getUserOffonlineMessegeCount", method = RequestMethod.POST)
	public ApiResult getUserOffonlineMessegeCount() {
		Account account = null;
		// Account account = accountService
		// .selectAccountById("51bf3f28b05f4a19904ac104bef2a379");
		List<SysAdminRelateUser> list = null;
		List<UserMsgInfo> resultList = new ArrayList<UserMsgInfo>();
		EasemobRestAPIFactory factory = ClientContext.getInstance()
				.init(ClientContext.INIT_FROM_PROPERTIES).getAPIFactory();
		IMUserAPI user = (IMUserAPI) factory
				.newInstance(EasemobRestAPIFactory.USER_CLASS);
		try {
			account = AccountShiroUtil.getCurrentUser();
		} catch (Exception e) {
			return new ApiResult(ResponseEnum.ERROR.getValue(), "尚未登录");
		}
		if (account == null) {
			return new ApiResult(ResponseEnum.ERROR.getValue(), "尚未登录");
		} else {
			String accountId = account.getAccountId();
			list = sysAdminRelateUserService
					.selectSysAdminRelateUserByAccountId(accountId);
			Collections.shuffle(list);
			for (int i = 0; i < list.size(); i++) {
				SysAdminRelateUser s = list.get(i);
				Map<String, Object> map1 = JsonUtil.toMap(JsonUtil.toMap(user
						.getOfflineMsgCount(s.getPushChatId())));
				try {
					Thread.sleep(200L);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				Map<String, Object> map2 = JsonUtil.toMap(map1
						.get("responseBody"));
				String data = null;
				try {
					data = map2.get("data").toString().split("=")[1];
				} catch (Exception e) {
					continue;
				}
				Integer count = Integer.valueOf(data.substring(0,
						data.length() - 1));
				if (map2.get("data") == null) {
					continue;
				}
				s.setOfflineMsgCount(count);
				if (count > 0) {
					resultList
							.add(UserMsgInfo.instance(userInfoService
									.selectUsreDetailInfo(s.getUserId()), count));
				}
				if (resultList.size() >= 20)
					break;
			}
			return new ApiResult(ResponseEnum.SUCCESS.getValue(), "成功",
					resultList);
		}
	}

	/**
	 * 从本地数据库获取离线消息数量
	 * @param map
	 * @return
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/getuseroffonlinemessegecountfromlocal", method = RequestMethod.POST)
	public ApiResult getUserOffonlineMessegeCountFromLocal(
			@RequestBody Map<String, Object> map) {
		Account account = null;
		try {
			account = AccountShiroUtil.getCurrentUser();
		} catch (Exception e) {
			return new ApiResult(ResponseEnum.ERROR.getValue(), "尚未登录");
		}
		map.put("accountId", account.getAccountId());
		PageBean pb = this.getJsonWrapPageBean(map);
		pb.setResults(sysAdminRelateUserService
				.selectSysAdminRelateUserByAccountIdPage(pb));
		return new ApiResult(ResponseEnum.SUCCESS.getValue(), "成功", pb);
	}
	
	/**
	 * 修改离线消息数量=0
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/updateaccountofflinecountbyuserid", method = RequestMethod.POST)
	public ApiResult updateAccountOfflineCountByUserId(
			@RequestBody SysAdminRelateUser sysAdminRelateUser) {
		Account account = null;
		try {
			account = AccountShiroUtil.getCurrentUser();
		} catch (Exception e) {
			return new ApiResult(ResponseEnum.ERROR.getValue(), "尚未登录");
		}
		sysAdminRelateUser.setAccountId(account.getAccountId());
		return new ApiResult(ResponseEnum.SUCCESS.getValue(),"成功",sysAdminRelateUserService.updateAccountOfflineCountByUserId(sysAdminRelateUser));
	}
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
	 * 查询账户统计数据
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/queryAccountCount", method = RequestMethod.POST)
	public ApiResult queryAccountCount() {
		Account account = null;
		try {
			account = (Account) SecurityUtils.getSubject().getSession()
					.getAttribute(Const.SESSION_USER);
		} catch (Exception e) {
			return new ApiResult(ResponseEnum.ERROR.getValue(), "尚未登录");
		}
		Map<String, Object> map = sysTalkManagerInfoMapper
				.selectAccountCount(account.getAccountId());
		if (map == null) {
			map = new HashMap<String, Object>();
			map.put("todayTalkSum", 0);
			map.put("todayUserSum", 0);
		}
		map.put("name", account.getName());
		map.put("fadeUserCount", sysAdminRelateUserService
				.selectSysAdminRelateUserCountByAccountId(account
						.getAccountId()));
		return new ApiResult(ResponseEnum.SUCCESS.getValue(), "成功", map);
	}

	// /**
	// * 修改账号绑定假用户
	// *
	// * @param map
	// * @return
	// */
	// @RequestMapping(value = "/updateAccountUser", method =
	// RequestMethod.POST)
	// public ApiResult updateAccountUser(@RequestBody Map<String, Object> map)
	// {
	// Assert.notNull(map.get("accountId"));
	// Assert.notNull(map.get("userNumber"));
	// String accountId = map.get("accountId").toString();
	// Integer number = Integer.valueOf(map.get("userNumber").toString());
	// return new ApiResult(ResponseEnum.SUCCESS.getValue(), "成功",
	// sysAdminRelateUserService.updateAccountUser(number, accountId));
	// }

	/**
	 * 修改用户聊天条书
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/updateAccountTalkCount", method = RequestMethod.POST)
	public ApiResult updateAccountTalkCount(@RequestBody Map<String, Object> map) {
		// Assert.notNull(map.get("accountId"));
		Account account = null;
		try {
			account = (Account) SecurityUtils.getSubject().getSession()
					.getAttribute(Const.SESSION_USER);
		} catch (Exception e) {
			return new ApiResult(ResponseEnum.ERROR.getValue(), "尚未登录");
		}
		Assert.notNull(map.get("userId"));
		String accountId = account.getAccountId();
		Integer userId = Integer.valueOf(map.get("userId").toString());
		return new ApiResult(ResponseEnum.SUCCESS.getValue(), "成功",
				sysTalkManagerInfoService.insertSysTalkManagerInfo(userId,
						accountId));
	}

	/**
	 * 统计聊天信息
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/selectAccountTalkInfo", method = RequestMethod.POST)
	public ApiResult selectAccountTalkInfo() {
		Account account = null;
		try {
			account = (Account) SecurityUtils.getSubject().getSession()
					.getAttribute(Const.SESSION_USER);
		} catch (Exception e) {
			return new ApiResult(ResponseEnum.ERROR.getValue(), "尚未登录");
		}
		return new ApiResult(ResponseEnum.SUCCESS.getValue(), "成功",
				sysTalkManagerInfoService.selectAccountTalkInfo(account));
	}


	/**
	 * 添加一跳常用语
	 * 
	 * @param domain
	 * @return
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ApiResult insertDiary(@RequestBody ChatRevert record) {
		return new ApiResult(ResponseEnum.SUCCESS,
				chatRevertService.insert(record));
	}

	/**
	 * 删除常用语
	 * 
	 * @param domain
	 * @return
	 */
	@RequestMapping(value = "/updateByPrimaryKeySelective", method = RequestMethod.POST)
	public ApiResult updateByPrimaryKeySelective(@RequestBody ChatRevert record) {
		return new ApiResult(ResponseEnum.SUCCESS,
				chatRevertService.updateByPrimaryKeySelective(record));
	}

	/**
	 * 初始化常用语
	 * 
	 * @param domain
	 * @return
	 */
	@RequestMapping(value = "/queryListChatRevert", method = RequestMethod.POST)
	public ApiResult queryListChatRevert() {
		return new ApiResult(ResponseEnum.SUCCESS,
				chatRevertService.queryListChatRevert());
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
