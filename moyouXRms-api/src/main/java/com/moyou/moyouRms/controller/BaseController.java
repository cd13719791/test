package com.moyou.moyouRms.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.moyou.moyouRms.constants.CONSTANT;
import com.moyou.moyouRms.constants.Const;
import com.moyou.moyouRms.interceptor.Assist;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.po.system.account.Account;
import com.moyou.moyouRms.model.po.system.resource.Resources;
import com.moyou.moyouRms.response.AjaxRes;
import com.moyou.moyouRms.service.system.account.AccountShiroUtil;
import com.moyou.moyouRms.service.system.resource.ResourcesService;
import com.moyou.moyouRms.util.StringUtil;
import com.moyou.moyouRms.util.UUIDUtil;
import com.moyou.moyouRms.util.webpage.PageData;

/**
 * 
 * @title Controller基类.java
 * @Description TODO
 * @author liuxinyi
 * @date 2016年11月22日 下午5:49:36
 * @version 1.0.0
 */
public class BaseController {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	public ResourcesService resourcesService;
	@Resource(name = "redisTemplate_KVString")
	private RedisTemplate<String, String> redisTemplate_KVString;

	/**
	 * 日期格式化
	 * 
	 * @param binder
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	/**
	 * 获取一个pageBean
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	protected PageBean getJsonWrapPageBean(@RequestBody Map<String, Object> map) {
		int pageNumber = StringUtil.getInt(map.get("pageNumber"));
		int pageSize = StringUtil.getInt(map.get("pageSize"));
		PageBean pageBean = new PageBean();
		pageBean.setPageNumber(pageNumber);
		pageBean.setPageSize(pageSize);
		pageBean.setConditions(map);
		return pageBean;
	}

	/**
	 * 获取一个assist
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	protected Assist getJsonWrapAssist(@RequestBody Map<String, Object> map) {
		int pageNumber = StringUtil.getInt(map.get("pageNumber"));
		int pageSize = StringUtil.getInt(map.get("pageSize"));
		if (pageNumber != 0)
			pageNumber = (pageNumber - 1) * pageSize;
		Assist assist = new Assist();
		assist.setRowSize(pageSize);
		assist.setStartRow(pageNumber);
		return assist;
	}

	/**
	 * 获取管理员登录用户
	 * 
	 * @param req
	 * @return
	 */
	protected Account getAdminUser() {
		Account o = new Account();
		Subject currentUser = SecurityUtils.getSubject();
		if (null != currentUser) {
			Session session = currentUser.getSession();
			if (null != session) {
				o = (Account) session.getAttribute(Const.SESSION_USER);
			}
		}
		return o;
	}

	/**
	 * 根据用户token用户登录的用户id
	 * 
	 * @param token
	 * @return
	 */
	public int getUserIdByToken(String token) {
		String tokenValue = redisTemplate_KVString.opsForValue().get(
				CONSTANT.H5_USER_TOKEN_PREFIX + token);
		int userId = 0;
		if (tokenValue != null) {
			try {
				String tokenValueArr[] = tokenValue.split("_");
				// logger.info("getUserIdByToken=========="+tokenValue);
				userId = StringUtil.getInt(tokenValueArr[0]);
				// logger.info("getUserIdByToken=========="+userId);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return userId;
		}
		return 0;
	}

	public AjaxRes getAjaxRes() {
		return new AjaxRes();
	}

	/**
	 * 资源的权限（显示级别）
	 * 
	 * @param type
	 *            资源类别
	 * @return
	 */
	public List<Resources> getPermitBtn(String type) {
		List<Resources> perBtns = new ArrayList<Resources>();
		try {
			String menu = getPageData().getString("menu");
			if (StringUtils.isNotBlank(menu)) {
				String menuNum = menu.replaceAll("menu", "");
				String userId = AccountShiroUtil.getCurrentUser().getAccountId();
				perBtns = resourcesService.findBtn(type, menuNum, userId);
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return perBtns;
	}

	/**
	 * 资源的权限（URl级别）
	 * 
	 * @param type
	 *            资源类别(优化速度)
	 * @return
	 */
	protected boolean doSecurityIntercept(String type) {
		try {
			String servletPath = getRequest().getServletPath();
			servletPath = StringUtils.substringBeforeLast(servletPath, ".");// 去掉后面的后缀
			String userId = AccountShiroUtil.getCurrentUser().getAccountId();
			List<Resources> authorized = resourcesService.resAuthorized(userId, type);
			for (Resources r : authorized) {
				if (r != null && StringUtils.isNotBlank(r.getResUrl())) {
					if (StringUtils.equals(r.getResUrl(), servletPath)) {
						return true;
					}
				}

			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return false;
	}

	/**
	 * 资源的权限（URl级别,拥有第一级资源权限，这资源才能访问）
	 * 
	 * @param type
	 *            资源类别(优化速度)
	 * @param url
	 *            第一级资源
	 * @return
	 */
	protected boolean doSecurityIntercept(String type, String url) {
		try {
			String userId = AccountShiroUtil.getCurrentUser().getAccountId();
			List<Resources> authorized = resourcesService.resAuthorized(userId, type);
			for (Resources r : authorized) {
				if (r != null && StringUtils.isNotBlank(r.getResUrl())) {
					if (StringUtils.equals(r.getResUrl(), url)) {
						return true;
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return false;
	}

	/**
	 * 得到PageData
	 */
	public PageData getPageData() {
		return new PageData(this.getRequest());
	}

	/**
	 * 得到request对象
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		return request;
	}

	/**
	 * 得到32位的uuid
	 * 
	 * @return
	 */
	public String get32UUID() {
		return UUIDUtil.getUUID();
	}

}
