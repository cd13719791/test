/**
 */
package com.moyou.moyouRms.pingxx.service.impl;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.moyou.moyouRms.constants.CONSTANT;
import com.moyou.moyouRms.constants.enums.UserFundModeEnum;
import com.moyou.moyouRms.pingxx.entity.ChargeEntity;
import com.moyou.moyouRms.pingxx.service.ChargeService;
import com.moyou.moyouRms.response.Msg;
import com.moyou.moyouRms.util.JsonUtil;
import com.moyou.moyouRms.util.PropertiesUtil;
import com.moyou.moyouRms.util.StringUtil;
import com.pingplusplus.exception.APIConnectionException;
import com.pingplusplus.exception.APIException;
import com.pingplusplus.exception.AuthenticationException;
import com.pingplusplus.exception.ChannelException;
import com.pingplusplus.exception.InvalidRequestException;
import com.pingplusplus.exception.RateLimitException;
import com.pingplusplus.model.Charge;
import com.pingplusplus.model.Transfer;

/**
 * @describe TODO
 * @author liuxinyi
 * @date 2017年2月7日
 * @email liuxinyi@mousns.com
 * @version 1.0.0
 */
@Service
public class ChargeServiceImpl implements ChargeService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public ChargeEntity createWxCharge(String orderNo, int modeType, String ip,
			int amount, Map<String, Object> metadata) {
		ChargeEntity chargeEntity = null;
		Charge charge = null;
		Map<String, Object> chargeMap = new HashMap<String, Object>();
		chargeMap.put("amount", amount);// 订单总金额, 人民币单位：分（如订单总金额为 1 元，此处请填 100）
		chargeMap.put("currency", "cny");
		// String orderNo = new Date().getTime() + Main.randomString(7);
		chargeMap.put("order_no", orderNo);// 推荐使用 8-20 位，要求数字或字母，不允许其他字符
		chargeMap.put("channel", "wx");// 支付使用的第三方支付渠道取值，请参考：https://www.pingxx.com/api#api-c-new
		chargeMap.put("client_ip", ip); // 发起支付请求客户端的 IP 地址，格式为 IPV4，如:
										// 127.0.0.1
		Map<String, String> app = new HashMap<String, String>();
		app.put("id", CONSTANT.PINGXX_APPID);
		chargeMap.put("app", app);
		chargeMap.put("metadata", metadata);
		// extra.put("open_id", "USER_OPENID");
		// chargeMap.put("extra", extra);
		try {
			UserFundModeEnum modeEnum = UserFundModeEnum.getByValue(modeType);
			chargeMap.put("subject", modeEnum.getDesc());
			chargeMap.put("body", modeEnum.getDesc() + "[" + amount + "]分");
			// 发起交易请求
			charge = Charge.create(chargeMap);
			chargeEntity = new ChargeEntity();
			chargeEntity.setAmount(charge.getAmount());
			chargeEntity.setBody(charge.getBody());
			chargeEntity.setChannel(charge.getChannel());
			chargeEntity.setClient_ip(charge.getClientIp());
			chargeEntity.setCurrency(charge.getCurrency());
			chargeEntity.setDescription(charge.getDescription());
			chargeEntity.setExtra(charge.getExtra());
			chargeEntity.setId(charge.getId());
			chargeEntity.setMetadata(charge.getMetadata());
			chargeEntity.setOrder_no(charge.getOrderNo());
			chargeEntity.setSubject(charge.getSubject());
			chargeEntity.setTime_expire(charge.getTimeExpire());
			chargeEntity.setAmount_refunded(charge.getAmountRefunded());
			chargeEntity.setAmount_settle(charge.getAmountSettle());
			chargeEntity.setApp(CONSTANT.PINGXX_APPID);
			chargeEntity.setCredential(charge.getCredential());
			chargeEntity.setFailure_code(charge.getFailureCode());
			chargeEntity.setFailure_msg(charge.getFailureMsg());
			chargeEntity.setId(charge.getId());
			chargeEntity.setLivemode(charge.getLivemode());
			chargeEntity.setObject(charge.getObject());
			chargeEntity.setPaid(charge.getPaid());
			chargeEntity.setRefunded(charge.getRefunded());
			chargeEntity.setRefunds(charge.getRefunds());
			chargeEntity.setTime_paid(charge.getTimePaid());
			chargeEntity.setTransaction_no(charge.getTransactionNo());
			// 传到客户端请先转成字符串 .toString(), 调该方法，会自动转成正确的 JSON 字符串
			// String chargeString = charge.toString();
			// System.out.println(chargeString);
		} catch (APIConnectionException e) {
			logger.error(
					StringUtil.getTraceInfo() + ":["
							+ JsonUtil.toJson(chargeMap) + "]", e);
		} catch (ChannelException e) {
			logger.error(
					StringUtil.getTraceInfo() + ":["
							+ JsonUtil.toJson(chargeMap) + "]", e);
		} catch (RateLimitException e) {
			logger.error(
					StringUtil.getTraceInfo() + ":["
							+ JsonUtil.toJson(chargeMap) + "]", e);
		} catch (AuthenticationException e) {
			logger.error(
					StringUtil.getTraceInfo() + ":["
							+ JsonUtil.toJson(chargeMap) + "]", e);
		} catch (APIException e) {
			logger.error(
					StringUtil.getTraceInfo() + ":["
							+ JsonUtil.toJson(chargeMap) + "]", e);
		} catch (InvalidRequestException e) {
			logger.error(
					StringUtil.getTraceInfo() + ":["
							+ JsonUtil.toJson(chargeMap) + "]", e);
		}
		return chargeEntity;
	}

	@Override
	public Msg remitFundToWx(String orderNo, String wxOpenId, int fund,
			int userFundModeValue) {
		// 调用第三方开始打款
		Msg msg = Msg.makeMsg();
		String appFlag = PropertiesUtil.getValueByKey("app_flag", CONSTANT.SYS_CONF_PATH);
		if (!CONSTANT.APP_FLAG_OFFICIAL_VALUE.equals(appFlag)) {// 只在正式服才打款
			msg.setSuccess(false);
			return msg;
		}
		Transfer tfer = null;
		try {
			UserFundModeEnum userFundModeEnum = UserFundModeEnum
					.getByValue(userFundModeValue);
			tfer = transfer(orderNo, wxOpenId, fund, userFundModeEnum.getDesc());
			if (tfer.getStatus().equals("failed")) {// 兑现状态 1成功
													// 2失败3兑现打款成功4兑现打款失败
				msg.setSuccess(false);
				msg.setMsg(tfer.getFailureMsg());
			}
		} catch (Exception e) {
			logger.error(StringUtil.getTraceInfo() + ":[" + e + "]");
			msg.setSuccess(false);
			e.printStackTrace();
		} finally {
			logger.info(StringUtil.getTraceInfo() + ":["
					+ JsonUtil.toJson(tfer) + "]");
		}
		return msg;
	}

	/**
	 * 创建企业转账
	 *
	 * 创建企业转账需要传递一个 map 给 Transfer.create(); map
	 * 填写的具体介绍可以参考：https://pingxx.com/document/api#api-t-new
	 *
	 * @return
	 */
	public Transfer transfer(String orderNo, String openid, int fund,
			String desc) throws Exception {
		Map<String, Object> transferMap = new HashMap<String, Object>();
		transferMap.put("channel", "wx_pub");// 目前支持 wx(新渠道)、 wx_pub
		transferMap.put("order_no", orderNo);// 企业转账使用的商户内部订单号。wx(新渠道)、wx_pub
												// 规定为 1 ~ 50 位不能重复的数字字母组合
		transferMap.put("amount", fund);// 订单总金额, 人民币单位：分（如订单总金额为 1 元，此处请填
										// 100,企业付款最小发送金额为 1 元）
		transferMap.put("type", "b2c");// 付款类型，当前仅支持 b2c 企业付款
		transferMap.put("currency", "cny");
		transferMap.put("recipient", openid);// 接收者 id， 为用户在 wx(新渠道)、wx_pub 下的
												// open_id
		transferMap.put("description", desc);
		Map<String, String> app = new HashMap<String, String>();
		app.put("id", CONSTANT.PINGXX_APPID);
		transferMap.put("app", app);
		Transfer transfer = Transfer.create(transferMap);
		return transfer;
	}



	/**
	 * 获得公钥
	 * 
	 * @return
	 * @throws Exception
	 */
	public static PublicKey getPubKey() throws Exception {
		byte[] keyBytes = Base64.decodeBase64(CONSTANT.PINGXX_WEBHOOKS_PUBKEY_STRING);

		// generate public key
		X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicKey = keyFactory.generatePublic(spec);
		return publicKey;
	}
	public boolean verifyWebhooksData(String dataString, String signatureString) {
		byte[] signatureBytes = Base64.decodeBase64(signatureString);
		try {
			Signature signature = Signature.getInstance("SHA256withRSA");
			signature.initVerify(getPubKey());
			signature.update(dataString.getBytes("UTF-8"));
			return signature.verify(signatureBytes);
		} catch (Exception e) {
			logger.error("ping++ verifyData is fail", e);
		}
		logger.error("ping++ verifyData is fail");
		return false;

	}
	
	
	
}
