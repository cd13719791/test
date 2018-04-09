/**
 */
package com.moyou.moyouRms.pingxx.entity;

import java.util.Map;

import com.pingplusplus.model.ChargeRefundCollection;

/**
 * @describe 支付对象实体 参数说明：https://www.pingxx.com/api#创建-charge-对象
 * @author liuxinyi
 * @date 2017年2月7日
 * @email liuxinyi@mousns.com
 * @version 1.0.0
 */
public class ChargeEntity {
	private String order_no ;// REQUIRED	商户订单号，适配每个渠道对此参数的要求，必须在商户系统内唯一。( alipay : 1-64 位， wx : 2-32 位， bfb : 1-20 位， upacp : 8-40 位，yeepay_wap :1-50 位， jdpay_wap :1-30 位， qpay :1-30 位， cmb_wallet :10 位纯数字字符串。注：除 cmb_wallet 外的其他渠道推荐使用 8-20 位，要求数字或字母，不允许特殊字符)。
	private String id; // EXPANDABLEREQUIRED	支付使用的 app 对象的 id ， expandable 可展开，查看 如何获取App ID 。
	private String channel; //REQUIRED	支付使用的第三方支付渠道。参考 支付渠道属性值 。
	private Integer amount ; // REQUIRED	订单总金额（必须大于0），单位为对应币种的最小货币单位，人民币为分。如订单总金额为 1 元， amount 为 100，么么贷商户请查看申请的借贷金额范围。
	private String client_ip ;// REQUIRED	发起支付请求客户端的 IPv4 地址，如: 127.0.0.1。
	private String currency; // REQUIRED	三位 ISO 货币代码，目前仅支持人民币 cny 。
	private String subject; // REQUIRED	商品的标题，该参数最长为 32 个 Unicode 字符，银联全渠道（ upacp /upacp_wap ）限制在 32 个字节。
	private String body; //REQUIRED	商品的描述信息，该参数最长为 128 个 Unicode 字符，yeepay_wap 对于该参数长度限制为 100 个 Unicode 字符。
	private Map<String, Object> extra; //optional	特定渠道发起交易时需要的额外参数，以及部分渠道支付成功返回的额外参数，详细参考 支付渠道 extra 参数说明 。
	private Long time_expire; // optional	订单失效时间，用 Unix 时间戳表示。时间范围在订单创建后的 1 分钟到 15 天，默认为 1 天，创建时间以 Ping++ 服务器时间为准。 微信对该参数的有效值限制为 2 小时内；银联对该参数的有效值限制为 1 小时内。
	private Map<String, Object> metadata; //optional	参考 元数据 。
	private String description; //optional	订单附加说明，最多 255 个 Unicode 字符。
	private String object;
	private String created;
	private Boolean livemode;
	private Boolean paid;
	private Boolean refunded;
	private String app;
	private Integer amount_settle;
	private Long time_paid;
	private String transaction_no;
	private ChargeRefundCollection refunds;
	private Integer amount_refunded;
	private String failure_code;
	private String failure_msg;
	private Map<String, Object> credential;
	/**
	 * @return the order_no
	 */
	public String getOrder_no() {
		return order_no;
	}
	/**
	 * @param order_no the order_no to set
	 */
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the channel
	 */
	public String getChannel() {
		return channel;
	}
	/**
	 * @param channel the channel to set
	 */
	public void setChannel(String channel) {
		this.channel = channel;
	}
	/**
	 * @return the amount
	 */
	public Integer getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	/**
	 * @return the client_ip
	 */
	public String getClient_ip() {
		return client_ip;
	}
	/**
	 * @param client_ip the client_ip to set
	 */
	public void setClient_ip(String client_ip) {
		this.client_ip = client_ip;
	}
	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}
	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}
	/**
	 * @param body the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}
	/**
	 * @return the extra
	 */
	public Map<String, Object> getExtra() {
		return extra;
	}
	/**
	 * @param extra the extra to set
	 */
	public void setExtra(Map<String, Object> extra) {
		this.extra = extra;
	}
	/**
	 * @return the time_expire
	 */
	public Long getTime_expire() {
		return time_expire;
	}
	/**
	 * @param time_expire the time_expire to set
	 */
	public void setTime_expire(Long time_expire) {
		this.time_expire = time_expire;
	}
	/**
	 * @return the metadata
	 */
	public Map<String, Object> getMetadata() {
		return metadata;
	}
	/**
	 * @param metadata the metadata to set
	 */
	public void setMetadata(Map<String, Object> metadata) {
		this.metadata = metadata;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the object
	 */
	public String getObject() {
		return object;
	}
	/**
	 * @param object the object to set
	 */
	public void setObject(String object) {
		this.object = object;
	}
	/**
	 * @return the created
	 */
	public String getCreated() {
		return created;
	}
	/**
	 * @param created the created to set
	 */
	public void setCreated(String created) {
		this.created = created;
	}
	/**
	 * @return the livemode
	 */
	public Boolean getLivemode() {
		return livemode;
	}
	/**
	 * @param livemode the livemode to set
	 */
	public void setLivemode(Boolean livemode) {
		this.livemode = livemode;
	}
	/**
	 * @return the paid
	 */
	public Boolean getPaid() {
		return paid;
	}
	/**
	 * @param paid the paid to set
	 */
	public void setPaid(Boolean paid) {
		this.paid = paid;
	}
	/**
	 * @return the refunded
	 */
	public Boolean getRefunded() {
		return refunded;
	}
	/**
	 * @param refunded the refunded to set
	 */
	public void setRefunded(Boolean refunded) {
		this.refunded = refunded;
	}
	/**
	 * @return the app
	 */
	public String getApp() {
		return app;
	}
	/**
	 * @param app the app to set
	 */
	public void setApp(String app) {
		this.app = app;
	}
	/**
	 * @return the amount_settle
	 */
	public Integer getAmount_settle() {
		return amount_settle;
	}
	/**
	 * @param amount_settle the amount_settle to set
	 */
	public void setAmount_settle(Integer amount_settle) {
		this.amount_settle = amount_settle;
	}
	/**
	 * @return the time_paid
	 */
	public Long getTime_paid() {
		return time_paid;
	}
	/**
	 * @param time_paid the time_paid to set
	 */
	public void setTime_paid(Long time_paid) {
		this.time_paid = time_paid;
	}
	/**
	 * @return the transaction_no
	 */
	public String getTransaction_no() {
		return transaction_no;
	}
	/**
	 * @param transaction_no the transaction_no to set
	 */
	public void setTransaction_no(String transaction_no) {
		this.transaction_no = transaction_no;
	}
	/**
	 * @return the refunds
	 */
	public ChargeRefundCollection getRefunds() {
		return refunds;
	}
	/**
	 * @param refunds the refunds to set
	 */
	public void setRefunds(ChargeRefundCollection refunds) {
		this.refunds = refunds;
	}
	/**
	 * @return the amount_refunded
	 */
	public Integer getAmount_refunded() {
		return amount_refunded;
	}
	/**
	 * @param amount_refunded the amount_refunded to set
	 */
	public void setAmount_refunded(Integer amount_refunded) {
		this.amount_refunded = amount_refunded;
	}
	/**
	 * @return the failure_code
	 */
	public String getFailure_code() {
		return failure_code;
	}
	/**
	 * @param failure_code the failure_code to set
	 */
	public void setFailure_code(String failure_code) {
		this.failure_code = failure_code;
	}
	/**
	 * @return the failure_msg
	 */
	public String getFailure_msg() {
		return failure_msg;
	}
	/**
	 * @param failure_msg the failure_msg to set
	 */
	public void setFailure_msg(String failure_msg) {
		this.failure_msg = failure_msg;
	}
	/**
	 * @return the credential
	 */
	public Map<String, Object> getCredential() {
		return credential;
	}
	/**
	 * @param credential the credential to set
	 */
	public void setCredential(Map<String, Object> credential) {
		this.credential = credential;
	}
	
}
