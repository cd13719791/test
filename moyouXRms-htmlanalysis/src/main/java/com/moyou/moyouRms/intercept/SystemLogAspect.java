package com.moyou.moyouRms.intercept;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.moyou.moyouRms.constants.Const;
import com.moyou.moyouRms.constants.enums.LogEnum;
import com.moyou.moyouRms.model.po.system.account.Account;
import com.moyou.moyouRms.model.sysOperationLog.SysOperationLog;
import com.moyou.moyouRms.service.sysoperationlog.SysOperationLogService;
import com.moyou.moyouRms.service.system.loginlog.LoginLogService;
import com.moyou.moyouRms.util.JsonUtil;
import com.moyou.moyouRms.util.StringUtil;

/**
 * 30 * @author 陈旭 31 * @E-mail: email 32 * @version 创建时间：2017/1/6 33 * @desc
 * 切点类 34
 */
//@Aspect
//@Controller
public class SystemLogAspect {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private LoginLogService loginLogService;
	@Autowired
	private SysOperationLogService sysOperationLogService;

	// Controller层切点 com.moyou.moyouRms.controller.
	@Pointcut("execution(public * com.moyou.moyouRms.controller..*.*(..))")
	public void controllerAspect() {

	}

	/**
	 * 前置通知 用于拦截Controller层记录用户的操作
	 * 
	 * @param joinPoint
	 *            切点
	 */
	@Before("controllerAspect()")
	public void doBefore(JoinPoint joinPoint) {
		// System.out.println("==========执行controller前置通知===============");

	}

	/**
	 * 后置通知 用于拦截Controller层记录用户的操作
	 * 
	 * @param joinPoint
	 *            切点
	 */
	@After("controllerAspect()")
	public void after(JoinPoint joinPoint) {
		Account user = new Account();
		try {
			Subject currentUser = SecurityUtils.getSubject();
			if (null != currentUser) {
				Session session = currentUser.getSession();
				if (null != session) {
					user = (Account) session.getAttribute(Const.SESSION_USER);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			// 记录本地异常日志
			logger.error("==异常==");
			logger.error("异常信息:{}", e.getMessage());
			return;
		}
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		// 请求的IP
		String ip = StringUtil.getIpAddr(request);
		try {

			String targetName = joinPoint.getTarget().getClass().getName();
			String methodName = joinPoint.getSignature().getName();
			Object[] arguments = joinPoint.getArgs();
			@SuppressWarnings("rawtypes")
			Class targetClass = Class.forName(targetName);
			Method[] methods = targetClass.getMethods();
			String operationType = "";
			String operationName = "";
			LogEnum operationLogEnum = null;
			for (Method method : methods) {
				if (method.getName().equals(methodName)) {
					@SuppressWarnings("rawtypes")
					Class[] clazzs = method.getParameterTypes();
					if (clazzs.length == arguments.length) {
						Log log = method.getAnnotation(Log.class);
						if (log == null) {
							break;
						}
						operationType = log.operationType();
						operationName = log.operationName();
						operationLogEnum = log.operationLogEnum();
						break;
					}
				}
			}
			String params = "";
			if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
				for (int i = 0; i < joinPoint.getArgs().length; i++) {
					params += JsonUtil.toJson(joinPoint.getArgs()[i]) + ";";
				}
			}
			// logger.info("请求开始, 各个参数, url: {}, method: {}, uri: {}, params: {}",
			// url, method, uri, queryString);
			SysOperationLog log = new SysOperationLog();
			log.setLogEnumValue(operationLogEnum == null ? LogEnum.DEFAULT_VALUE
					.getValue() : operationLogEnum.getValue());
			log.setCreateTime(new Date());
			log.setLogdescription(operationType + " : " + operationName);
			log.setLogmethod((joinPoint.getTarget().getClass().getName() + "."
					+ joinPoint.getSignature().getName() + "()"));
			log.setLogParam(params);
			log.setLogusername(user != null ? user.getLoginName() : "找不到账户");
			log.setLogpath(ip);
			// 保存数据库
			sysOperationLogService.saveSysOperationLog(log);
			// System.out.println("=====controller后置通知结束=====");
		} catch (Exception e) {
			// 记录本地异常日志
			logger.error("==后置通知异常==");
			logger.error("异常信息:{}", e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 异常通知 用于拦截记录异常日志
	 * 
	 * @param joinPoint
	 * @param e
	 */
	@AfterThrowing(pointcut = "controllerAspect()", throwing = "e")
	public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
		// HttpServletRequest request = ((ServletRequestAttributes)
		// RequestContextHolder.getRequestAttributes()).getRequest();
		// RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		// ServletRequestAttributes sra = (ServletRequestAttributes) ra;
		// HttpServletRequest request = sra.getRequest();
		// HttpSession session = request.getSession();
		// //读取session中的用户
		// User user = (User) session.getAttribute(Const.SESSION_USER);
		//
		// //获取请求ip
		// String ip = request.getRemoteAddr();
		// //获取用户请求方法的参数并序列化为JSON格式字符串
		//
		Account user = new Account();
		Subject currentUser = null;
		try {

			currentUser = SecurityUtils.getSubject();
		} catch (Exception e2) {
			return;
		}
		if (null != currentUser) {
			Session session = currentUser.getSession();
			if (null != session) {
				user = (Account) session.getAttribute(Const.SESSION_USER);
			}
		}
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		// 请求的IP
		String ip = request.getRemoteAddr();
		String params = "";
		if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
			for (int i = 0; i < joinPoint.getArgs().length; i++) {
				params += (joinPoint.getArgs()[i]) + ";";
			}
		}
		try {

			// String targetName = joinPoint.getTarget().getClass().getName();
			// String methodName = joinPoint.getSignature().getName();
			// Object[] arguments = joinPoint.getArgs();
			// Class targetClass = Class.forName(targetName);
			// Method[] methods = targetClass.getMethods();
			String operationType = "";
			String operationName = "";
			// for (Method method : methods) {
			// if (method.getName().equals(methodName)) {
			// Class[] clazzs = method.getParameterTypes();
			// if (clazzs.length == arguments.length) {
			// operationType = method.getAnnotation(Log.class).operationType();
			// operationName = method.getAnnotation(Log.class).operationName();
			// break;
			// }
			// }
			// }
			/* ========控制台输出========= */
			// System.out.println("=====异常通知开始=====");
			// System.out.println("异常代码:" + e.getClass().getName());
			// System.out.println("异常信息:" + e.getMessage());
			// // System.out.println("异常方法:"
			// + (joinPoint.getTarget().getClass().getName() + "."
			// + joinPoint.getSignature().getName() + "()") + "."
			// + operationType);
			// System.out.println("方法描述:" + operationName);
			// System.out.println("请求人:" + user != null ? user.getLoginName()
			// : "找不到账户");
			// System.out.println("请求IP:" + ip);
			// Object[] args = joinPoint.getArgs();
			// System.out.println("请求参数:" + params);
			/* ==========数据库日志========= */
			SysOperationLog log = new SysOperationLog();
			log.setCreateTime(new Date());
			log.setLogdescription(operationType + " : " + operationName);
			log.setLogmethod((joinPoint.getTarget().getClass().getName() + "."
					+ joinPoint.getSignature().getName() + "()")
					+ " param={" + JsonUtil.toJson(params) + "}");
			log.setLogusername(user != null ? user.getLoginName() : "找不到账户");
			log.setLogpath(ip);
			// 保存数据库
			sysOperationLogService.saveSysOperationLog(log);
			// System.out.println("=====异常通知结束=====");
		} catch (Exception ex) {
			// 记录本地异常日志
			logger.error("==异常通知异常==");
			logger.error("异常信息:{}", ex.getMessage());
		}
		/* ==========记录本地异常日志========== */
		logger.error("异常方法:{}异常代码:{}异常信息:{}参数:{}", joinPoint.getTarget()
				.getClass().getName()
				+ joinPoint.getSignature().getName(), e.getClass().getName(),
				e.getMessage(), params);

	}

}
