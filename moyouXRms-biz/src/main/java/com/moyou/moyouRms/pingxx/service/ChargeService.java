/**
 */
package com.moyou.moyouRms.pingxx.service;

import java.util.Map;

import com.moyou.moyouRms.pingxx.entity.ChargeEntity;
import com.moyou.moyouRms.response.Msg;

/**
 * @describe 支付对象操作
 * @author liuxinyi
 * @date 2017年2月7日
 * @email liuxinyi@mousns.com
 * @version 1.0.0
 */
public interface ChargeService {
	/**
	 * 创建微信支付对象信息，传递回客户端
	 * @param orderNo 订单号
	 * @param modeType 模式类型
	 * @param ip 
	 * @param amount 订单总金额, 人民币单位：分（如订单总金额为 1 元，此处请填 100）
	 * @param metadata 元数据信息， 一般存储业务信息
	 * @return
	 */
	ChargeEntity createWxCharge(String orderNo, int modeType, String ip, int amount, Map<String, Object> metadata);
	/**
	 * 微信结算 http://kf.qq.com/faq/161222A3eErM161222Jve2q2.html
	 * 接口调用规则：https://pay.weixin.qq.com/wiki/doc/api/tools/mch_pay.php?chapter=14_2
	◆ 给同一个实名用户付款，单笔单日限额2W/2W
	◆ 不支持给非实名用户打款
	◆ 一个商户同一日付款总额限额100W
	◆ 单笔最小金额默认为1元
	◆ 每个用户每天最多可付款10次，可以在商户平台--API安全进行设置
	◆ 给同一个用户付款时间间隔不得低于15秒
	 * 打款给用户
	 * @param orderNo 订单号
	 * @param wxOpenId 微信openid
	 * @param fund 打款金额单位 分
	 * @param userFundModeEnum 资金类型
	 * @return
	 */
	Msg remitFundToWx(String orderNo, String wxOpenId, int fund, int userFundModeValue);
	
	/**
	 * 验证签名
	 * 
	 * @param dataString
	 * @param signatureString
	 */
	public boolean verifyWebhooksData(String dataString, String signatureString);
}
