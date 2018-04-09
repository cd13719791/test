package com.moyou.moyouRms.controller.system.login;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moyou.moyouRms.constants.Const;
import com.moyou.moyouRms.constants.enums.LogEnum;
import com.moyou.moyouRms.constants.enums.ResponseEnum;
import com.moyou.moyouRms.controller.BaseController;
import com.moyou.moyouRms.intercept.Log;
import com.moyou.moyouRms.model.limitip.LimitIp;
import com.moyou.moyouRms.model.po.system.account.Account;
import com.moyou.moyouRms.model.po.system.loginlog.LoginLog;
import com.moyou.moyouRms.model.sysBaseAccountRole.SysBaseAccountRole;
import com.moyou.moyouRms.model.sysBaseRoleResources.SysBaseRoleResourcesKey;
import com.moyou.moyouRms.model.sysSessionManager.SysSessionManager;
import com.moyou.moyouRms.model.user.UserRegCondition;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.limitip.LimitIpService;
import com.moyou.moyouRms.service.syssessionmanager.SysSessionManagerService;
import com.moyou.moyouRms.service.system.account.AccountService;
import com.moyou.moyouRms.service.system.account.AccountShiroUtil;
import com.moyou.moyouRms.service.system.loginlog.LoginLogService;
import com.moyou.moyouRms.service.system.resource.SysBaseRoleResourcesService;
import com.moyou.moyouRms.service.system.role.SysBaseAccountRoleService;
import com.moyou.moyouRms.service.user.UserService;
import com.moyou.moyouRms.util.IpUtils;
import com.moyou.moyouRms.util.JsonUtil;
import com.moyou.moyouRms.util.Md5Util;

@Controller
public class LoginController extends BaseController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private LoginLogService loginLogService;
	@Autowired
	private UserService service;
	@Resource
	private SysSessionManagerService sysSessionManagerService;
	@Resource
	private LimitIpService limitIpService;
	@Resource
	private SysBaseRoleResourcesService sysBaseRoleResourcesService;
	@Resource
	private SysBaseAccountRoleService sysBaseAccountRoleService;
	@Autowired
	private SessionDAO sessionDao;
	@Autowired
	ValidatingSessionManager validatingSessionManager;

	@SuppressWarnings("unused")
	private UserRegCondition getUser(String nickname, int sex, Date birthday, String address,
			String constellation, String sig) {
		UserRegCondition regCondition = new UserRegCondition();
		regCondition.setPassword(Md5Util.getMd5("123456"));
		regCondition.setLongitude(104.06);
		regCondition.setLatitude(30.67);
		regCondition.setNickname(nickname);
		regCondition.setSex(sex);
		regCondition.setBirthday(birthday);
		regCondition.setAddress(address);
		regCondition.setConstellation(constellation);
		regCondition.setSig(sig);
		regCondition.setIp("");
		regCondition.setType(com.moyou.moyouRms.model.user.User.ADMIN_CREATE);
		return regCondition;
	}

	/**
	 * 请求登录，验证用户
	 */
	@Log(operationType = "登录:", operationName = "登录", operationLogEnum = LogEnum.SYSTEM_LOGIN)
	@RequestMapping(value = "/system_login")
	@ResponseBody
	public Map<String, Object> login(@RequestBody Map<String, Object> map) throws Exception {
		// System.out.println(sessionDao.getActiveSessions().size());
		// System.out.println("在线人数***********************************");
		int result = 0;
		String errInfo = "";
		String loginName = map.get("loginName").toString() == null ? null : map.get("loginName")
				.toString();
		String passWord = map.get("passWord").toString() == null ? null : map.get("passWord")
				.toString();
		Assert.notNull(passWord);
		Assert.notNull(loginName);
		// shiro管理的session
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		String sessionCode = "ddddd";
		String code = "ddddd";
		String username = loginName;
		String password = passWord;
		if (null == code || "".equals(code)) {
			errInfo = "nullcode"; // 验证码为空
		} else if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			errInfo = "nullup"; // 缺少用户名或密码
		} else {
			if (StringUtils.isNotEmpty(sessionCode) && sessionCode.equalsIgnoreCase(code)) {
				// shiro加入身份验证
				UsernamePasswordToken token = new UsernamePasswordToken(username,
						password.toUpperCase());
				token.setRememberMe(false);
				try {
					if (!currentUser.isAuthenticated()) {
						currentUser.login(token);
					}
					// 记录登录日志
					String accountId = AccountShiroUtil.getCurrentUser().getAccountId();
					String loginIP = IpUtils.getIpAddr(getRequest());// 获取用户登录IP
					LimitIp IpAddress = limitIpService.queryByIpAddress(loginIP);
					if (null != IpAddress) {

						if (loginIP.equals(IpAddress.getIpAddress())) {
							errInfo = "ip is limit";// IP已被禁用
						}
					}
					LoginLog loginLog = new LoginLog(accountId, loginIP);
					loginLogService.saveLoginLog(loginLog);
				} catch (UnknownAccountException uae) {
					errInfo = "usererror";// 用户名或密码有误
				} catch (IncorrectCredentialsException ice) {
					errInfo = "usererror"; // 密码错误
				} catch (LockedAccountException lae) {
					errInfo = "inactive";// 未激活
				} catch (ExcessiveAttemptsException eae) {
					errInfo = "attemptserror";// 错误次数过多
				} catch (AuthenticationException ae) {
					errInfo = "codeerror";// 验证未通过
				}
				// 验证是否登录成功
				if (!currentUser.isAuthenticated()) {
					token.clear();
				}
			} else {
				errInfo = "codeerror"; // 验证码输入有误
			}
			if (StringUtils.isEmpty(errInfo)) {
				errInfo = "success";
				session.removeAttribute(Const.SESSION_SECURITY_CODE);// 移除SESSION的验证
				Account account = new Account();
				account.setLoginName(loginName);
				account = accountService.queryAccountByLoginName(account);// 验证成功
				// 聊天管理员用来记录的登录状态
				SysSessionManager sysSessionManager = new SysSessionManager();
				SysSessionManager sysSessionManagerTemp = sysSessionManagerService
						.selectToDayDataByLoginName(loginName);
				if (sysSessionManagerTemp != null) {
					if (sysSessionManagerTemp.getOnlineType() == SysSessionManager.ONLINE) {
						Session session2 = null;
						try {
							session2 = sessionDao.readSession(sysSessionManagerTemp.getSessionId());
							if (session2 != null && session2.getId() != (session.getId())) {
								session2.removeAttribute(Const.SESSION_USER);
								session2.removeAttribute(Const.SESSION_MENULIST);
								session2.setTimeout(0);
								sessionDao.delete(session2);
							}
						} catch (Exception e) {
							// e.printStackTrace();

						}
					}
				}
				sysSessionManager.setCreateTime(new Date());
				sysSessionManager.setSessionId(session.getId().toString());
				sysSessionManager.setLoginName(account.getLoginName());
				sysSessionManager.setOnlineType(SysSessionManager.ONLINE);
				// sessionDao.create(session);
				sysSessionManagerService.updateOnlineTypeByLoginName(sysSessionManager);
				map.put("account", account);
				result = 1;
			}
		}

		map.put("result", errInfo);
		map.put("code", result);
		return map;
	}

	/**
	 * 请求账号资源
	 * 
	 * @return
	 */
	@RequestMapping("/system_get_tree")
	@ResponseBody
	public ApiResult systemGetTree() {
		List<SysBaseRoleResourcesKey> list = new ArrayList<SysBaseRoleResourcesKey>();
		if (AccountShiroUtil.getCurrentUser() == null) {
			return new ApiResult(ResponseEnum.ERROR.getValue());
		}
		// 添加前端菜单树资源
		List<SysBaseAccountRole> sysBaseAccountRoleList = sysBaseAccountRoleService
				.selectSysBaseAccountRoleByAccountId(AccountShiroUtil.getCurrentUser()
						.getAccountId());
		if (sysBaseAccountRoleList != null && sysBaseAccountRoleList.size() > 0) {
			for (int i = 0; i < sysBaseAccountRoleList.size(); i++) {
				list.addAll(sysBaseRoleResourcesService
						.selectSysBaseRoleResourcesKeyByRoleId(sysBaseAccountRoleList.get(i)
								.getRoleId()));
			}
		}
		return new ApiResult(ResponseEnum.SUCCESS.getValue(), "成功", list);
	}

	/**
	 * 帐号注销
	 * 
	 * @return
	 */
	@RequestMapping("/system_logout")
	@ResponseBody
	public ApiResult logout(HttpServletResponse response) {
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		// session = request.getSession(true);
		Account acc = AccountShiroUtil.getCurrentUser();
		if (acc == null) {
			return new ApiResult(ResponseEnum.ERROR.getValue(), "未登录");
		}
		sysSessionManagerService.updateByLoginName(session);// 结算在线时间
		String loginName = AccountShiroUtil.getCurrentUser().getLoginName();
		SysSessionManager sysSessionManager = new SysSessionManager();
		sysSessionManager.setLoginName(loginName);
		sysSessionManager.setOnlineType(SysSessionManager.OFFLINE);
		sysSessionManagerService.updateOnlineTypeByLoginName(sysSessionManager);// 修改登录状态
		session.removeAttribute(Const.SESSION_USER);
		session.removeAttribute(Const.SESSION_MENULIST);
		try {
			session.setAttribute(Const.SESSION_USER, null);
			currentUser.logout();
			validatingSessionManager.validateSessions();// 验证所有回话是否过期 清楚过期回话
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ApiResult(ResponseEnum.SUCCESS.getValue(), "成功");
	}

	/**
	 * 查看内存
	 * 
	 * @return
	 */
	@RequestMapping("/system_memeory")
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
