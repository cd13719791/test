package com.moyou.moyouRms.controller.system.account;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moyou.moyouRms.constants.enums.LogEnum;
import com.moyou.moyouRms.constants.enums.ResponseEnum;
import com.moyou.moyouRms.controller.BaseController;
import com.moyou.moyouRms.intercept.Log;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.po.system.account.Account;
import com.moyou.moyouRms.model.sysBaseAccountRole.SysBaseAccountRole;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.security.CipherUtil;
import com.moyou.moyouRms.service.sysoperationlog.SysOperationLogService;
import com.moyou.moyouRms.service.system.account.AccountService;
import com.moyou.moyouRms.service.system.role.SysBaseAccountRoleService;
import com.moyou.moyouRms.util.StringUtil;
import com.moyou.moyouRms.util.UUIDUtil;

@Controller
@RequestMapping("/account")
public class AccountController extends BaseController {

	@Resource
	private SysBaseAccountRoleService sysBaseAccountRoleService;
	@Autowired
	private AccountService service;
	@Resource
	private SysOperationLogService sysOperationLogService;

	/**
	 * 添加管理员
	 */
	@Log(operationType = "insert:", operationName = "insert:Account", operationLogEnum = LogEnum.SYSTEM_INSERT_ACCOUNT)
	@RequestMapping(value = "/insertAccount")
	@ResponseBody
	public ApiResult insertAccount(@RequestBody Account o) {
		Assert.notNull(o);
		Assert.notNull(o.getLoginName());
		Assert.notNull(o.getName());
		Assert.notNull(o.getPassword());
		// Assert.notNull(o.getPhone());
		Assert.notNull(o.getRoleId());
		Assert.notNull(o.getType());
		o.setAccountId(UUIDUtil.getUUID());
		String salt = CipherUtil.createSalt();
		o.setSalt(salt);
		// String pwrsMD5=CipherUtil.generatePassword("huangjie");//第一次加密md5，
		o.setSkin("skin-0");// 默认皮肤
		o.setPassword(CipherUtil.createPwdEncrypt(o.getLoginName(), o.getPassword().toUpperCase(),
				salt));
		o.setRoleIdStr(o.getRoleId());
		if (StringUtil.isEmpty(o.getPicUrl())) {
			o.setPicUrl("http://myimagetest.immouo.com/report/pic/56CCBB7B-ECB4-4FAB-B57C-0AD2684E8AAE1486804543730_1.jpg");
		}
		// o.setIsValid(1);

		try {
			SysBaseAccountRole sysBaseAccountRole = new SysBaseAccountRole();
			sysBaseAccountRole.setAccountId(o.getAccountId());
			sysBaseAccountRole.setRoleId(o.getRoleId());
			sysBaseAccountRoleService.insertSelective(sysBaseAccountRole);
			service.insertAccount(o);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ApiResult(ResponseEnum.SUCCESS.getValue(), "成功");
	}

	@RequestMapping(value = "/queryAccount")
	@ResponseBody
	public ApiResult queryAccount(@RequestBody Map<String, Object> map) {
		PageBean pb = this.getJsonWrapPageBean(map);
		Account o = new Account();
		o.setLoginName(map.get("loginName") == null ? null : map.get("loginName").toString());
		o.setRoleIdStr(map.get("roleIdStr") == null ? null : map.get("roleIdStr").toString());
		return new ApiResult(ResponseEnum.SUCCESS.getValue(), "成功", service.findByPage(o, pb));
	}

	/**
	 * 查询登录日志
	 * 
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/queryLoginLog")
	@ResponseBody
	public ApiResult queryLoginLog(@RequestBody Map<String, Object> map) {
		PageBean pb = this.getJsonWrapPageBean(map);
		pb.setResults(sysOperationLogService.querySysOperationLog(pb));
		return new ApiResult(ResponseEnum.SUCCESS.getValue(), "成功", pb);
	}

	/**
	 * 查询所有管理员
	 * 
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/queryAllAccount")
	@ResponseBody
	public ApiResult queryAllAccount(@RequestBody Map<String, Object> map) {
		map.put("isValid", "1");
		PageBean pb = this.getJsonWrapPageBean(map);
		pb.setResults(service.queryAccountByParam(pb));
		return new ApiResult(ResponseEnum.SUCCESS.getValue(), "成功", pb);
	}

	/**
	 * 编辑管理员
	 */
	@RequestMapping(value = "/updateAccount")
	@ResponseBody
	public ApiResult updateAccount(@RequestBody Account o) {
		Assert.notNull(o);
		// Account oldAccount=service.selectAccountById(o.getAccountId());
		// String pwrsMD5=CipherUtil.generatePassword("huangjie");//第一次加密md5，
		Account a = service.selectAccountById(o.getAccountId());
		if (StringUtil.isNotEmpty(o.getNpwd()) && StringUtil.isNotEmpty(o.getQpwd())) {
			a.setOpwd(o.getOpwd());
			a.setQpwd(o.getQpwd());
			a.setNpwd(o.getNpwd());
			service.preResetPwdWithOrther(a);
		}
		o.setRoleIdStr(o.getRoleId());
		if (StringUtil.isEmpty(o.getPicUrl())) {
			o.setPicUrl("http://myimagetest.immouo.com/report/pic/56CCBB7B-ECB4-4FAB-B57C-0AD2684E8AAE1486804543730_1.jpg");
		}
		if (StringUtil.isNotEmpty(o.getRoleId())) {
			sysBaseAccountRoleService.updateAccountRole(o);
		} else {
			o.setRoleId(a.getRoleId());
		}
		if (StringUtil.isEmpty(o.getName())) {
			o.setName(a.getName());
		}
		service.update(o);
		return new ApiResult(ResponseEnum.SUCCESS.getValue(), "成功");
	}
}
