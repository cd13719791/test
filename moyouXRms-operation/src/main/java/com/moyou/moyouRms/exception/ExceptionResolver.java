package com.moyou.moyouRms.exception;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.util.JsonUtil;

/**
 * @describe 异常处理
 * @author liuxinyi
 * @date 2017年1月11日
 * @email liuxinyi@mousns.com
 * @version 1.0.0
 */
public class ExceptionResolver implements HandlerExceptionResolver {
	private static Logger LOG = LoggerFactory.getLogger(ExceptionResolver.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		LOG.error(ex.getMessage(), ex);
//		if (Objects.nonNull(ex.getCause())) {
//			ex.getCause().setStackTrace(new StackTraceElement[] { ex.getCause().getStackTrace()[0] });
//		}
//		if (Objects.nonNull(ex.getStackTrace())) {
//			ex.setStackTrace(new StackTraceElement[] { ex.getStackTrace()[0] });
//		}
		ApiResult result = new ApiResult(RESPONSE.ERROR,
				ex.getMessage() == null ? "您访问的页面出错了，程序员哥哥正在加班加点处理" : ex.getMessage(), "{}");
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

}
