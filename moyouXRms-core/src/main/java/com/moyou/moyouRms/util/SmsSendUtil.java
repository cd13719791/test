package com.moyou.moyouRms.util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.moyou.moyouRms.constants.CONSTANT;
import com.moyou.moyouRms.constants.enums.ResponseEnum;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

import edu.emory.mathcs.backport.java.util.Arrays;

/**
 * 短信验证工具
 * 
 * @author PzC.
 * @time 2017年1月6日 下午2:36:23
 * 
 */
public class SmsSendUtil {
	protected static Logger logger = Logger.getLogger(SmsSendUtil.class);

	public static final AlibabaAliqinFcSmsNumSendResponse send(String phoneNumber, String captcha,
			String signName, String smsTemplate, int s) throws ApiException {
		/**
		 * 短信验证码，使用同一个签名，对同一个手机号码发送短信验证码，允许每分钟1条，累计每小时7条。
		 * 短信通知，使用同一签名、同一模板，对同一手机号发送短信通知，允许每天50条（自然日）。
		 */
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		TaobaoClient client = new DefaultTaobaoClient(CONSTANT.SMS_SEND_URL,
				CONSTANT.ALIDAYU_APP_KEY, CONSTANT.ALIDAYU_APP_SECRET);
		req.setExtend("");
		req.setSmsFreeSignName(signName);
		req.setSmsParamString("{name:'" + captcha + "',code:" + ResponseEnum.getByValue(s) + "}");
		req.setRecNum(phoneNumber);
		req.setSmsTemplateCode(smsTemplate);
		req.setSmsType("normal");
		return client.execute(req);
	}

	@SuppressWarnings("all")
	public static final void send(String[] phoneNumber, String captcha, String signName,
			String smsTemplate, int s) {
		/**
		 * 短信验证码，使用同一个签名，对同一个手机号码发送短信验证码，允许每分钟1条，累计每小时7条。
		 * 短信通知，使用同一签名、同一模板，对同一手机号发送短信通知，允许每天50条（自然日）。
		 */
		List<String> list = Arrays.asList(phoneNumber);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		TaobaoClient client = new DefaultTaobaoClient(CONSTANT.SMS_SEND_URL,
				CONSTANT.ALIDAYU_APP_KEY, CONSTANT.ALIDAYU_APP_SECRET);
		list.forEach(str -> {
			req.setExtend("");
			req.setSmsFreeSignName(signName);
			// String APP_FLAG = PropertiesUtil.getValueByKey("app_flag",
			// CONSTANT.SYS_CONF_PATH);
			req.setSmsParamString("{name:'" + captcha + "',code:" + ResponseEnum.getByValue(s)
					+ "}");
			req.setRecNum(str);
			req.setSmsTemplateCode(smsTemplate);
			req.setSmsType("normal");
			try {
				client.execute(req);
			} catch (Exception e) {
				logger.error("send mobile msg fail", e);
				e.printStackTrace();
			}
		});
	}

	@SuppressWarnings("all")
	public static final AlibabaAliqinFcSmsNumSendResponse sendForIdentifyingCode(
			String phoneNumber, String captcha, String signName, String smsTemplate, Integer s,
			String money) throws ApiException {
		/**
		 * 短信验证码，使用同一个签名，对同一个手机号码发送短信验证码，允许每分钟1条，累计每小时7条。
		 * 短信通知，使用同一签名、同一模板，对同一手机号发送短信通知，允许每天50条（自然日）。
		 */
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		TaobaoClient client = new DefaultTaobaoClient(CONSTANT.SMS_SEND_URL,
				CONSTANT.ALIDAYU_APP_KEY, CONSTANT.ALIDAYU_APP_SECRET);
		req.setExtend("");
		req.setSmsFreeSignName(signName);
		req.setSmsParamString("{name:'" + captcha + "',code:'" + s.toString() + "',money:'" + money
				+ "'}");
		req.setRecNum(phoneNumber);
		req.setSmsTemplateCode(smsTemplate);
		req.setSmsType("normal");
		try {
			AlibabaAliqinFcSmsNumSendResponse alibabaAliqinFcSmsNumSendResponse = client
					.execute(req);
			if (alibabaAliqinFcSmsNumSendResponse.isSuccess()) {
				return alibabaAliqinFcSmsNumSendResponse;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static final AlibabaAliqinFcSmsNumSendResponse sendSuccess(String phone,
			String smsTemplate, String captcha, BigDecimal fund, String loginName) {
		/**
		 * 短信验证码，使用同一个签名，对同一个手机号码发送短信验证码，允许每分钟1条，累计每小时7条。
		 * 短信通知，使用同一签名、同一模板，对同一手机号发送短信通知，允许每天50条（自然日）。
		 */
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		TaobaoClient client = new DefaultTaobaoClient(CONSTANT.SMS_SEND_URL,
				CONSTANT.ALIDAYU_APP_KEY, CONSTANT.ALIDAYU_APP_SECRET);
		req.setExtend("");
		req.setSmsFreeSignName(captcha);
		req.setSmsParamString("{name:'" + loginName + "test" + "',time:'"
				+ DateTimeUtil.formatDate(new Date(), "yyyy年MM月dd日 hh时mm分ss秒") + "',money:'" + fund
				+ "'}");
		req.setRecNum(phone);
		req.setSmsTemplateCode(smsTemplate);
		req.setSmsType("normal");
		try {
			AlibabaAliqinFcSmsNumSendResponse alibabaAliqinFcSmsNumSendResponse = client
					.execute(req);
			if (alibabaAliqinFcSmsNumSendResponse.isSuccess()) {
				return alibabaAliqinFcSmsNumSendResponse;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		System.out.println(sendSuccess("15982125521", "SMS_71161207", "陌友",
				new BigDecimal("123.4"), "test"));
	}
}
