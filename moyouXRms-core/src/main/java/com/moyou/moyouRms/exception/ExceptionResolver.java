package com.moyou.moyouRms.exception;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.util.JsonUtil;

public class ExceptionResolver implements HandlerExceptionResolver {
	private static Logger LOG = LoggerFactory.getLogger(ExceptionResolver.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		LOG.error(ex.getMessage(), ex);
		ApiResult result = new ApiResult(RESPONSE.ERROR, checkEXClass(ex), null);
		if(SecurityUtils.getSubject().getSession()==null||SecurityUtils.getSubject().getSession().getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY)==null){
				result = new ApiResult(RESPONSE.ERROR,"您已经被强制挤下线");
		};
		responseError(response, result);
		return null;
	}

	private void responseError(HttpServletResponse response, ApiResult result) {
		response.setContentType("application/json; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(JsonUtil.toJson(result));
		} catch (IOException e) {
		} finally {
			IOUtils.closeQuietly(writer);
		}
	}

	private String checkEXClass(Exception ex) {
		if (ex instanceof MouoException) {
			return ex.getMessage();
		} else {
			return "出错了";
		}
	}

}
