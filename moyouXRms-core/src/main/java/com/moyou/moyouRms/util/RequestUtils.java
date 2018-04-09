package com.moyou.moyouRms.util;

import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

/**
 * @title Request 操作
 * @Description TODO
 * @author liuxinyi
 * @date 2016年11月15日 下午3:21:08
 * @version 1.0.0
 */
public class RequestUtils {

	private HttpServletRequest request;

	public RequestUtils(HttpServletRequest _request) {
		super();

		this.request = _request;
	}

	public String[] getParameterValues(String _sParamName) {
		return request.getParameterValues(_sParamName);
	}

	/**
	 * 将参数转换成Map对象
	 * 
	 * @return
	 */
	public Map<String, Object> parseRequestParamsToParamsContext() {
		Map<String, Object> paramContext = new ConcurrentHashMap<String, Object>();
		Enumeration<String> enumNames = request.getParameterNames();
		while (enumNames.hasMoreElements()) {
			String sParamName = (String) enumNames.nextElement();
			String[] sParameterValues = getParameterValues(sParamName);
			if (sParameterValues == null || sParameterValues.length == 1) {
				String sParameterValue = StringUtil.getStrByDefault(
						request.getParameter(sParamName), "");
				paramContext.put(sParamName, sParameterValue);
			} else {
				paramContext.put(sParamName, sParameterValues);
			}
		}
		return paramContext;
	}

	/**
	 * 处理参数
	 * 
	 * @param _sParamName
	 * @param _sDefaultValue
	 * @return
	 */
	public String getParameterAsString(String _sParamName, String _sDefaultValue) {
		String sParamValue = request.getParameter(_sParamName);
		String strParamValue = StringUtil.getStrByDefault(sParamValue,
				_sDefaultValue);
		// if (_sParamName.equalsIgnoreCase("SearchType")) {
		strParamValue = StringUtil.filterForSQL(strParamValue);
		// }
		return strParamValue;
	}
}