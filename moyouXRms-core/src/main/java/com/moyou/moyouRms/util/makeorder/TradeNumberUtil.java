/**
 */
package com.moyou.moyouRms.util.makeorder;

import com.moyou.moyouRms.util.StringUtil;


/**
 * @describe 订单工具类
 * @author liuxinyi
 * @date 2017年1月16日
 * @email liuxinyi@mousns.com
 * @version 1.0.0
 */
public class TradeNumberUtil {
	
	/**
	 * 获取一个订单号
	 * @return
	 */
	public static String getTradeNumber() {
		  MakeOrderNum makeOrder = new MakeOrderNum();  
          String tradeNumber = makeOrder.makeOrderNum2();
          return tradeNumber;
	}
	/**
	 * 混淆年月日
	 * @param ymd
	 * @return
	 */
	public static String yearMonthDayMix(String ymd) {
		StringBuilder str = new StringBuilder("");
		if (StringUtil.isNotEmpty(ymd)) {
			if (ymd.contains("-")) {
				ymd = ymd.replace("-", "");
			}
            str.append(ymd.substring(0, 2));
            str.append(ymd.substring(4, 6));
            str.append(ymd.substring(2, 4));
            str.append(ymd.substring(6));
			return Integer.valueOf(str.toString(), 16)+"";
		}
		return str.toString();
	}
	public static void main(String[] args) {
		System.out.println(getTradeNumber());
		System.out.println(Integer.toHexString(536942360));
	}
}
