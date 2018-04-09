/**
 */
package com.moyou.moyouRms.util.schedule;

/**
 * @describe 计划表表达式工具类
 * @author liuxinyi
 * @date 2017年3月10日
 * @email liuxinyi@mousns.com
 * @version 1.0.0
 */
public class CronExpressionUtil
{
    public static final Integer TALK_MODE_DAY = 3;
    public static final Integer TALK_MODE_NIGHT = 5;

    /**
     * 根据秒生成计划表达式
     * 
     * @param second
     * @return
     */
    public static String getCronExpressionBySecond(int second)
    {
        String cronExpression = "/5 * * * * ?";// 默认5秒执行一次
        if (second < 60)
        {// 按秒生成表达式
            cronExpression = "/" + second + " * * * * ?";
        }
        else if (second >= 60 && second < (60 * 60))
        {// 按分生成表达式
            int minute = second / 60;
            cronExpression = "0 /" + minute + " * * * ?";
        }
        else if (second >= (60 * 60) && second < (60 * 60 * 24))
        {// 按时生成表达式
            int hour = second / 60 / 60;
            cronExpression = "0 0 /" + hour + " * * ?";
        }
        return cronExpression;
    }

    /**
     * 根据秒生成计划表达式 说说白天晚上执行模式
     * 
     * @param second
     * @return
     */
    public static String getCronExpressionBySecondForTalk(int second, int dayOrNight)
    {
        String cronExpression = "/5 * 7-22 * * ?";// 默认5秒执行一次
        if (dayOrNight == TALK_MODE_DAY)
        {
            if (second < 60)
            {// 按秒生成表达式
                cronExpression = "/" + second + " * 7-22 * * ?";
            }
            else if (second >= 60 && second < (60 * 60))
            {// 按分生成表达式
                int minute = second / 60;
                cronExpression = "0 */" + minute + " 7-22 * * ?";
            }
            else
            {// 按时生成表达式
             // cronExpression = "0 0 0/1 * * ?";
                cronExpression = "0 30/59 7-22 * * ?";
            }
        }
        else
        {
            if (second < 60)
            {// 按秒生成表达式
                cronExpression = "/" + second + " * 23-6 * * ?";
            }
            else if (second >= 60 && second < (60 * 60))
            {// 按分生成表达式
                int minute = second / 60;
                cronExpression = "0 */" + minute + " 23-6 * * ?";
            }
            else
            {// 按时生成表达式
                cronExpression = "0 30/59 23-6 * * ?";
            }
        }
        return cronExpression;
    }
}
