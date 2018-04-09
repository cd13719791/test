/**
 */
package com.moyou.moyouRms.util.makeorder;

/**
 * @describe 订单号生成工具，生成非重复订单号，理论上限1毫秒1000个，可扩展 
 * @author liuxinyi
 * @date 2017年1月16日
 * @email liuxinyi@mousns.com
 * @version 1.0.0
 */
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.moyou.moyouRms.util.RandomStringUtil;
  
public class MakeOrderNum {  
    /** 
     * 锁对象，可以为任意对象 
     */  
    private static Object lockObj = "lockerOrder";  
    /** 
     * 订单号生成计数器 
     */  
    private static long orderNumCount = 0L;  
    /** 
     * 每毫秒生成订单号数量最大值 
     */  
    private int maxPerMSECSize=1000;  
    /** 
     * 生成非重复订单号，理论上限1毫秒1000个，可扩展 
     * @param tname 测试用 
     */  
    public String makeOrderNum() {  
    	 // 最终生成的订单号  
        StringBuffer orderNum = new StringBuffer("");  
        try {  
        	String finOrderNum = "";  
            synchronized (lockObj) {  
                // 取系统当前时间作为订单号变量前半部分，精确到毫秒  
                long nowLong = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));  
                // 计数器到最大值归零，可扩展更大，目前1毫秒处理峰值1000个，1秒100万  
                if (orderNumCount > maxPerMSECSize) {  
                    orderNumCount = 0L;  
                }  
                //组装订单号  
                String countStr=maxPerMSECSize +orderNumCount+"";  
                finOrderNum = nowLong+countStr.substring(1); 
                // 混淆订单号
                String ymd = finOrderNum.substring(0, 8);
                String subFinOrderNum = finOrderNum.substring(8);
                orderNum.append(TradeNumberUtil.yearMonthDayMix(ymd));
                orderNum.append(subFinOrderNum);
             /*   System.out.println(year1);
                System.out.println(year2);
                System.out.println(month);
                System.out.println(day);*/
                orderNumCount++;  
//                System.out.println(orderNum.toString());
//                System.out.println(finOrderNum + "--" + Thread.currentThread().getName() + "::" + tname );  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return orderNum.toString();
    }  
    public String makeOrderNum2() {  
    	// 最终生成的订单号  流水号：M + 年月日时分秒 + 6位数字
    	StringBuffer orderNum = new StringBuffer("");  
    	try {  
    		synchronized (lockObj) {  
    			// 取系统当前时间作为订单号变量前半部分，精确到毫秒  
    			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"); 
//                long nowLong = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));  
    			long nowLong = Long.parseLong(LocalDateTime.now().format(dtf));  
    			//组装订单号  
    			orderNum.append("M").append(nowLong).append(RandomStringUtil.randomStr(6));
    		}  
    	} catch (Exception e) {  
    		e.printStackTrace();  
    	}  
    	return orderNum.toString();
    }  
    public static void main(String[] args) {  
    	/*MakeOrderNum makeOrder = new MakeOrderNum();  
    	   String order = makeOrder.makeOrderNum();  
           System.out.println(order);*/
        // 测试多线程调用订单号生成工具  
        try {  
            for (int i = 0; i < 200; i++) {  
               Thread t1 = new Thread(new Runnable() {  
                    public void run() {  
                        MakeOrderNum makeOrder = new MakeOrderNum();  
                        String order = makeOrder.makeOrderNum();  
                        System.out.println(order);
                    }  
                }, "at" + i);  
                t1.start();  
  
             /*   Thread t2 = new Thread(new Runnable() {  
                    public void run() {  
                        MakeOrderNum makeOrder = new MakeOrderNum();  
                        makeOrder.makeOrderNum("b");  
                    }  
                }, "bt" + i);  
                t2.start();  */
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
    }  
  
}  
