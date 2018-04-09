package com.moyou.moyouRms.constants.enums;

/**
 * 故事来源Enum
 */
public enum DiarySourceTypeEnum {

	FROM_APP(0, "陌友"),
	/**
	 * 来自html
	 */
	FROM_HTML(1, "后台发布"), FROM_NETEASY(2, "网易新闻"), FROM_TOUTIAO(3, "今日头条"), ;

	private Integer sourceType;
	private String sourceTypeStr;

	private DiarySourceTypeEnum(Integer sourceType, String sourceTypeStr) {
		this.sourceType = sourceType;
		this.sourceTypeStr = sourceTypeStr;
	}

	/**
	 * 根据类型获取枚举实例
	 * 
	 * @param sourceType
	 * @return
	 */
	public static DiarySourceTypeEnum getByType(Integer sourceType) {
		for (DiarySourceTypeEnum e : values()) {
			if (e.getSourceType() == sourceType) {
				return e;
			}
		}
		return null;
	}

	/**
	 * 根据字符串获取枚举实例
	 * 
	 * @param str
	 * @return
	 */
	public static DiarySourceTypeEnum getEnumByStr(String str,
			UserDeviceClientTypeEnum userDeviceClientTypeEnum) {
		switch (userDeviceClientTypeEnum) {
		case ANDROID:
			if (str == null) {
				return null;
			} else if (str.indexOf("网易新闻") > 0) {
				return FROM_NETEASY;
			} else if (str.indexOf("今日头条") > 0) {
				return FROM_TOUTIAO;
			}
			break;
		case IOS:
			if (str == null) {
				return null;
			} else if (str.indexOf("163.com/news") > 0) { // 网易
				return FROM_NETEASY;
			} else if (str.indexOf("iid=") > 0 && str.indexOf("&app=news_article") > 0) { // 头条
				return FROM_TOUTIAO;
			}
			break;
		// else if (str.indexOf("guancha.cn/toutiao") > 0) { // 观察者网
		// return FROM_TOUTIAO;
		// } else if (str.indexOf("youth.cn/transfer") > 0) { // 中国青年网
		// return FROM_TOUTIAO;
		// } else if (str.indexOf("m.gmw.cn/") > 0) { // 光明网
		// return FROM_TOUTIAO;
		// } else if (str.indexOf("m.huanqiu.com/") > 0) { // 环球网
		// return FROM_TOUTIAO;
		// } else if (str.indexOf("m.ce.cn/") > 0) { // 中国经济网
		// return FROM_TOUTIAO;
		// } else if (str.indexOf("m.k618.cn/jrtt/") > 0) { // 未来网
		// return FROM_TOUTIAO;
		// } else if (str.indexOf("snssdk.com/") > 0) { // 会生活
		// return FROM_TOUTIAO;
		// }
		default:
			break;
		}
		return null;
	}

	public static DiarySourceTypeEnum getEnumByStr(String str) {
		if (str == null) {
			return null;
		} else if (str.equals("网易新闻")) {
			return FROM_NETEASY;
		} else if (str.equals("今日头条")) {
			return FROM_TOUTIAO;
		}
		return null;
	}

	/**
	 * 根据字符串获取字符串中的url
	 * 
	 * @param str
	 * @return
	 */
	public static String getUrlByStr(String str, UserDeviceClientTypeEnum userDeviceClientTypeEnum) {
		String url = null;
		try {
			switch (userDeviceClientTypeEnum) {
			case ANDROID:
				DiarySourceTypeEnum diarySourceTypeEnum = DiarySourceTypeEnum.getEnumByStr(str,
						userDeviceClientTypeEnum);
				switch (diarySourceTypeEnum) {
				case FROM_NETEASY:
					String[] arr1 = str.split("\n");
					String url2 = "";
					if (arr1.length == 1) {
						url2 = arr1[0];
					} else if (arr1.length == 5 && str.indexOf("https://c.m.163.com/news/") > 0) {
						url2 = arr1[2];
					} else {
						url2 = "https://c.m.163.com/news/a/" + arr1[2]
								+ ".html?spss=newsapp&spsw=1";
					}
					return url2;
				case FROM_TOUTIAO:
					String[] arr = str.split("\n");
					String url1 = "";
					if (arr.length == 1) {

						url1 = arr[0];
					} else {
						url1 = arr[1];
					}
					/*
					 * String groupId = url1.split("group/")[1].substring(0,
					 * url1.split("group/")[1].indexOf("/")); String toutiaoUrl
					 * = "http://www.toutiao.com/group/"+groupId+"/";
					 */
					return url1;
				default:
					break;
				}
				break;
			case IOS:
				return str;
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}

	public static String getUrlForDb(String str, Integer type, Integer urlFromType) {
		String url = null;
		try {
			switch (type) {
			case 1:
				switch (urlFromType) {
				case 2:
					String[] arr1 = str.split("\n");
					String url2 = "";
					if (arr1.length == 1) {
						url2 = arr1[0];
					} else if (arr1.length == 5 && str.indexOf("https://c.m.163.com/news/") > 0) {
						url2 = arr1[2];
					} else {
						url2 = "https://c.m.163.com/news/a/" + arr1[2]
								+ ".html?spss=newsapp&spsw=1";
					}
					return url2;
				case 3:
					String[] arr = str.split("\n");
					String url1 = "";
					if (arr.length == 1) {

						url1 = arr[0];
					} else {
						url1 = arr[1];
					}
					/*
					 * String groupId = url1.split("group/")[1].substring(0,
					 * url1.split("group/")[1].indexOf("/")); String toutiaoUrl
					 * = "http://www.toutiao.com/group/"+groupId+"/";
					 */
					return url1;
				default:
					break;
				}
				break;
			case 2:
				return str;
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}

	public Integer getSourceType() {
		return sourceType;
	}

	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}

	public String getSourceTypeStr() {
		return sourceTypeStr;
	}

	public void setSourceTypeStr(String sourceTypeStr) {
		this.sourceTypeStr = sourceTypeStr;
	}

	public static void main(String[] args) {
		DiarySourceTypeEnum.getEnumByStr("", UserDeviceClientTypeEnum.IOS);
		System.out.println("========");
	}
}
