package com.moyou.moyouRms.intercept;

import java.lang.reflect.Method;
import java.util.Map;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.moyou.moyouRms.annotation.rule.Length;
import com.moyou.moyouRms.annotation.rule.Rule;
import com.moyou.moyouRms.annotation.vaild.Validation;
import com.moyou.moyouRms.confing.SysConf;
import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.response.ApiResult;

/**
 * 校验拦截器
 * 
 * @author PzC.
 * @date 2016年11月15日 下午8:16:22
 * 
 */
public class ValidInterceptor implements MethodInterceptor {
	Logger log = LoggerFactory.getLogger(ValidInterceptor.class);

	private SysConf conf = SysConf.getInstance();
	
	@SuppressWarnings("unchecked")
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Method method = invocation.getMethod();
		Map<String, Object> params = null;
		if (method.isAnnotationPresent(Validation.class)) {
			Validation valid = method.getAnnotation(Validation.class);
			Object[] args = invocation.getArguments();
			for (Object obj : args) {
				if (obj instanceof Map) {
					params = (Map<String, Object>) obj;
				}
			}
			Object[] result = valid(valid, params);
			if (!(boolean) result[0]) {
				return new ApiResult(RESPONSE.ERROR, result[1].toString(), "{}");
			}
		}
		return invocation.proceed();
	}

	private Object[] valid(Validation valid, Map<String, Object> params) {
		Object[] result = new Object[] {};
		result = notNull(valid.notNull(), params);
		if ((boolean) result[0]) {
			result = length(valid.length(), params);
			if ((boolean) result[0]) {
				result = email(valid.email(), params);
				if ((boolean) result[0]) {
					result = rules(valid.rules(), params);
					if ((boolean) result[0]) {
						result = mobile(valid.mobile(), params);
						if ((boolean) result[0]) {
							result = number(valid.number(), params);
						}
					}
				}
			}
		}
		return result;
	}

	private Object[] number(String[] numbers, Map<String, Object> params) {
		Object[] result = new Object[] { true, new String() };
		if (numbers != null && numbers.length > 0) {
			for (String filed : numbers) {
				Object param = params.get(filed);
				if (param != null && !param.toString().matches(conf.getValueByKey("number"))) {
					result[0] = false;
					result[1] = conf.getValueByKey("validator.constraints.Number.message", filed);
					break;
				}
			}
		}
		return result;
	}

	private Object[] email(String[] emails, Map<String, Object> params) {
		Object[] result = new Object[] { true, new String() };
		if (emails != null && emails.length > 0) {
			for (String filed : emails) {
				Object param = params.get(filed);
				if (param == null || !param.toString().matches(conf.getValueByKey("email"))) {
					result[0] = false;
					result[1] = conf.getValueByKey("validator.constraints.Email.message", filed);
					break;
				}
			}
		}
		return result;
	}

	private Object[] mobile(String[] mobile, Map<String, Object> params) {
		Object[] result = new Object[] { true, new String() };
		if (mobile != null && mobile.length > 0) {
			for (String filed : mobile) {
				Object param = params.get(filed);
				if (param == null || !param.toString().matches(conf.getValueByKey("mobile"))) {
					result[0] = false;
					result[1] = conf.getValueByKey("validator.constraints.Mobile.message", filed);
					break;
				}
			}
		}
		return result;
	}

	private Object[] notNull(String[] notNull, Map<String, Object> params) {
		Object[] result = new Object[] { true, new String() };
		if (notNull != null && notNull.length > 0) {
			for (String filed : notNull) {
				Object param = params.get(filed);
				if (param == null || param.toString().matches(conf.getValueByKey("isNull"))) {
					result[0] = false;
					result[1] = conf.getValueByKey("validator.constraints.IsNull.message", filed);
					break;
				}
			}
		}
		return result;
	}

	private Object[] length(Length[] lengths, Map<String, Object> params) {
		Object[] result = new Object[] { true, new String() };
		if (lengths != null && lengths.length > 0) {
			for (Length length : lengths) {
				String filed = length.filed();
				int min = length.min();
				int max = length.max();
				Object param = params.get(filed);
				if (param == null || param.toString().length() < min || param.toString().length() > max) {
					result[0] = false;
					result[1] = conf.getValueByKey("validator.constraints.Length.message", filed, min, max);
					break;
				}
			}
		}
		return result;
	}

	private Object[] rules(Rule[] rules, Map<String, Object> params) {
		Object[] result = new Object[] { true, new String() };
		if (rules != null && rules.length > 0) {
			for (Rule rule : rules) {
				String filed = rule.filed();
				String regx = rule.regx();
				Object param = params.get(filed);
				if (param == null || !param.toString().matches(regx)) {
					result[0] = false;
					result[1] = conf.getValueByKey("validator.constraints.Rule.message", filed);
				}
			}
		}
		return result;
	}
}