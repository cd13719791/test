package com.moyou.moyouRms.constants;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.moyou.moyouRms.util.JsonUtil;
import com.moyou.moyouRms.util.PropertiesUtil;

/**
 * 常量
 * 
 * @author yubing
 * @time 下午6:45:59
 */
public class CONSTANT {
	public static String SYSTEM_USER_ID = "0";
	// 七牛 图片 视频 音频地址
	public static String IMAGE_URL = "";
	// video.immouo.com
	public static String VIDEO_URL = "";
	// audio.immouo.com
	public static String AUDIO_URL = "";
	// 验证码 key 存储
	public static final String REDIS_IDENTIFYING_CODE_KEY = "IdentifyingCode";
	// 配置文件名
	public static final String SYS_CONF_PATH = "sysconf";
	public static final String SYS_COMMON_PATH = "com.moyou.moyouRms.property.common-config";
	public static final String SYSTEM_H5_DIARY_PATH = "system_h5_diary_path";
	public static final String APP_FLAG_OFFICIAL_VALUE = "app_flag"; // 正式服应用标志
	public static String APP_FLAG_CURRENT_VALUE = "";// 当前应用标志 初始化的时候赋值
	public static final String SYSTEM_H5_ARTICLE_PATH = "system_h5_article_path";
	public static final String APP_FLAG_VALUE = "app_flag";
	public static final String APP_FLAG_VALUE_TEST = "app_flag_test";
	// moyouIdList 存储key
	public static final String MOYOUID_LIST = "moyouidList";
	// 群ID list 存储Key
	public static final String CROWDID_LIST = "crowdList";
	// userBaseInfoList 存储key
	public static final String USER_BASE_INFO_LIST = "userBaseInfoList";
	// 手机号登录
	public static final String LOGIN_BY_PHONE_NUMBER = "phoneNumber";
	// 第三方账号登录
	public static final String LOGIN_BY_THIRD_ACCOUNT = "thirdAccount";
	// 手机验证正则表达式
	public static final String REGEX_MOBILE = "^((14[0-9])|(17[0-9])|(13[0-9])|(15[0-9])|(18[0-9]))\\d{8}$";
	// 星座日期界限
	public static final int[] DAY_ARR = new int[] { 20, 19, 21, 20, 21, 22, 23, 23, 23, 24, 23, 22 };
	// 12星座
	public static final String[] CONSTELLATION_ARR = new String[] { "摩羯座", "水瓶座", "双鱼座", "白羊座",
			"金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座" };
	// alidayu 短信发送地址
	public static final String SMS_SEND_URL = "https://eco.taobao.com/router/rest";
	// alidayu app_key
	public static final String ALIDAYU_APP_KEY = "23497294";
	// alidayu app_secret
	public static final String ALIDAYU_APP_SECRET = "88aeac23a762ac478aacbb75c468203a";
	// alidayu 注册验证
	public static final String REG_VERIFY = "注册验证";
	// alidayu 注册验证短信模板
	public static final String REG_TEMPLATE = "SMS_23495023";
	// alidayu 注册验证短信模板2
	public static final String REG_TEMPLATE2 = "SMS_63115183";
	// alidayu 任务监控短信通知
	public static final String REG_SCHEDULE = "SMS_70090634";
	// alidayu 后台充值余额验证码短信通知
	public static final String REG_IDENTIFYING_CODE = "SMS_71221095";
	// alidayu 后台充值成功短信通知
	public static final String REG_IDENTIFYING_CODE_SUCCESS = "SMS_71161207";
	// 极光推送离线期限
	public static final long TIME_TO_LIVE = 604800;
	// iOS推送声音
	public static final String SOUND = "default.mp3";
	// iOS 推送角标
	public static final int BADGE = 1;
	// 需要覆盖推送的 msgSendType , 类型为List<String>
	public static final List<String> NEED_OVERRIDE_PUSH = JsonUtil.toCollection(
			PropertiesUtil.bundle("need.override"), new TypeReference<List<String>>() {
			});
	// 推送 content 获取 key
	public static final String SHORT_MSG_CONTENT_KEY = ".push.short.content";
	public static final String MSG_CONTENT_KEY = ".push.content";
	// concern 推送 key
	public static final String CONCERN_KEY = "concern.push";
	// contact 推送 key
	public static final String CONTACT_KEY = "contact.push";
	public static final String DYNAMIC_COMMENT = "dynamic.comment";
	public static final String DYNAMIC_RECOMMENT = "dynamic.recomment";
	public static final String CIRCLE_TOPIC_RECOMMENT = "circle.topic.recomment";
	public static final String CIRCLE_TOPIC_COMMENT = "circle.topic.comment";

	public static final String CONTACT_CONFIRM = "contact.confirm";
	public static final String CONTACT_REFUSE = "contact.refuse";
	public static final String REG_PUSH = "reg.push";

	/**
	 * 极光推送消息
	 */
	public static final String JPUSH_CUSTOM_MSG_PUSH_KEY = "jpush_custom_msg_push_key";// 极光推送自定义消息推送key
	public static final String JPUSH_CUSTOM_MSG_PUSH_KEY_BUSINESS_TYPE = "jpush_custom_msg_push_key_business_type";// 极光推送自定义消息推送业务类型key
	public static final String JPUSH_BAR_MSG_PUSH_KEY_BUSINESS_TYPE = "jpush_bar_msg_push_key_business_type";// 极光推送通知栏消息消息推送业务类型key
	public static final String JPUSH_CUSTOM_MSG_PUSH_CONTENT = "你收到一条系统消息";// 极光推送自定义消息推送key
	public static final String REG_LIMIT = "sys_reg_limit";// 限制用户
	public static final String FEEDBACK_SYSTEM = "sys_feedback_system";// 意见反馈处理
	public static final String SYS_USER_WARNING = "sys_user_warning";// 系统警告
	public static final String SYS_USER_WARNING_TITLE = "陌友—系统警告";
	public static final String SYS_USER_LIMIT_TITLE = "陌友—系统限制";
	public static final String SYS_CONSTOM_WARNING_TITLE = "陌友—系统警告";
	public static final String FEEDBACK_SYSTEM_TITLE = "陌友—意见反馈";
	public static final String SYS_MSG_PUSH_IMAGE_KEY = "sys_msg_push_image_key";// 系统消息推送图片key
	public static final String SYS_MSG_RED_PACKET_BACK = "sys_msg_red_packet_back";// 红包退还消息
	public static final String SYS_WELCOME_TITLE = "欢迎来到陌友";
	public static final String SYS_MSG_RED_PACKET_BACK_TITLE = "红包退还通知";// 红包退还消息
	public static final String SYS_ART_MSG = "sys_art_msg";// 【后台文章推送】
	public static final String SYS_AUDIT_ADSUPPLY = "陌友－广告审核结果";
	public static final String SYS_USER_WELCOME = "sys_user_welcome";// 系统欢迎
	public static final String SYS_USER_WELCOME_TITLE = "陌友--欢迎您";

	public static final String SYS_AUDIT_ADSUPPLY_TYPE = "sys_audit_adsupply_type";// 广告金币退还通知
	public static final String SYS_MSG_PUSH_KEY = "sys_msg_push_key";// 系统消息推送KEY
	public static final String SYS_MSG_PUSH_CMENT_KEY = "msg_content";// 自定义消息推送内容key
	public static final String SYS_MSG_PUSH_MODE_ID = "mode_id";// 自定义消息推送内容key
	public static final String JPUSH_AUDIT_FALSE_TYPE = "jpush_audit_false";// 提现失败回馈
	public static final String NOTIFICATION_MSG_PUSH_KEY = "notification_msg_push_key";// 通知消息推送KEY

	public static final String JPUSH_NOTIFICATION_MSG_PUSH_KEY = "jpush_notification_msg_push_key";// 极光推送自定义消息推送key
	public static final String JPUSH_NOTIFICATION_MSG_PUSH_KEY_BUSINESS_TYPE = "jpush_notification_msg_push_key_business_type";// 极光推送自定义消息推送业务类型key

	public static final String DEFAULT_MSG_PUSH_KEY = "default_msg_push_key";// 默认消息推送KEY类型

	public static final String APPLY_FRIEND_MSG_PUSH_KEY = "apply_friend_msg_push_key";// 申请好友消息推送KEY
	public static final String NOTIFICATION_BAR_PUSH = "notification_bar_push";// 通知栏推送key
	/**
	 * ===================================
	 */
	/**
	 * api code 文件路径
	 */
	public static final String DEFAULT_CODE_MSG_FILE_PATH = "com.moyou.moyouRms.property.common-config";
	public static final String API_CODE_MSG_PREFIX_KEY = "api_code_msg_";
	/**
	 * ping++
	 */
	public static final String PINGXX_APPID = "app_4SCSaPLq5GC408Gq";
	public static final String PINGXX_API_KEY = "sk_live_f5Gy9K9GGK8CSGeH4KHyDOC0";
	public static final String PINGXX_PRIVATE_KEY_FILE_PATH = "your_rsa_private_key.pem";
	public static final String PINGXX_PUBLIC_KEY_FILE_PATH = "your_rsa_public_key.pem";

	/**
	 * h5跳转app生成token前缀
	 */
	public static final String H5_USER_TOKEN_PREFIX = "h5_user_token_prefix:";
	/**
	 * 占位 key 前缀rank id
	 */
	public static final String RANK_PREFIX = "rank";
	/**
	 * ping++ webhooks 白名单_
	 */
	public static final String PINGXX_WEBHOOKS_WHITE_IP = PropertiesUtil
			.getValueByKey("pingxx_webhooks_white_ip");
	/**
	 * pingxx webhooks 回调验签公钥
	 */
	public static String PINGXX_WEBHOOKS_PUBKEY_STRING;

	/**
	 * redis
	 */
	public static final String SYS_REWARD_DEDUCT_MONEY_PREFIX = "sys_reward_deduct_money_prefix:";// 存储系统打赏扣除金额key前缀
	public static final String CHARGE_TOTAL_TODAY_PREFIX = "charge_total_today_:";// 充值总量_每天
	/**
	 * 系统打赏 聊天消息
	 */
	public static final String I_GIVE_YOU_TALK = "我打赏了你的说说";
	public static final String I_GIVE_YOU_DIARY = "我打赏了你的故事";
	/**
	 * 用户提现 1000 单位 分 默认提现必须大于10元
	 */
	public static final Integer SYSTEM_WITHDRAW_DEPOSIT = 10 * 100;
	/**
	 * 单日提现额度 200元
	 */
	public static final BigDecimal ONE_DAY_WITHDRAW_SUM = new BigDecimal(200);

	public static final Integer USER_CROWD_RECOMMEND_COUNT = 9;
	/**
	 * 限制用户的头像
	 */
	public static final String LIMIT_USER_AVATAR = "https://myimage.immouo.com/user_limit_avatar.png";

	/**
	 * 今日头条重构url
	 */
	public static final String TOUTIAO_HTTP_URL = "toutiao_http_url";

	/**
	 * 活动小钱 redis key
	 */
	public static final String ACTIVITY_EARN_SMALLCHANGE_LIST_FOR_USER = "activity_earn_smallchange_list_for_user";
	/**
	 * 活动规则 hash表Key
	 */
	public static final String SYS_EVERY_ACTIVITY = "sys_every_activity";
	/**
	 * 每日活动总规则 redis key
	 */
	public static final String SYS_EVERY_ACTIVITY_RULE = "sys_every_activity_rule";
	/**
	 * 说说活动规则key
	 */
	public static final String SYS_EVERY_ACTIVITY_RULE_MODE_TYPE_TALK = "sys_every_activity_rule_mode_type_talk";
	/**
	 * 文章活动规则key
	 */
	public static final String SYS_EVERY_ACTIVITY_RULE_MODE_TYPE_DIARY = "sys_every_activity_rule_mode_type_diary";
	/**
	 * 秘密活动规则key
	 */
	public static final String SYS_EVERY_ACTIVITY_RULE_MODE_TYPE_SECRET = "sys_every_activity_rule_mode_type_secret";
	/**
	 * 评论活动规则key
	 */
	public static final String SYS_EVERY_ACTIVITY_RULE_MODE_TYPE_COMMENT = "sys_every_activity_rule_mode_type_comment";
	/**
	 * 系统分享活动规则key
	 */
	public static final String SYS_EVERY_ACTIVITY_RULE_MODE_TYPE_SYSTEM = "sys_every_activity_rule_mode_type_system";
	/**
	 * 第三方分享活动规则key
	 */
	public static final String SYS_EVERY_ACTIVITY_RULE_MODE_TYPE_BIZ = "sys_every_activity_rule_mode_type_biz";
	public static final String JPUSH_OFFLINE_USER_5_DAY_TEXT = "Hey，你的好友给你进行了一波打赏，现金红包别错过，赶快去领取吧！";
	public static final String JPUSH_OFFLINE_USER_10_DAY_TEXT = "Hey，你的好友给你进行了一波打赏，现金红包别错过，赶快去领取吧！";
	public static final String JPUSH_AUDIT_FALSE = "你好陌友，您的提现账户因为没有在微信客户端上进行实名认证，所以暂时是不能进行提现操作，赶快去认证吧！";

	public static final String MOMENT_TIME_FAKE_ALL_USER = "moment_time_fake_all_user";// 附近时刻假用户坐标存储key不区分市
	public static final String LIMIT_LIVE_USER = "limit_live_user";// 限制主播
	public static final String STOP_LIVE_USER = "stop_live_user";// 停播主播

}
