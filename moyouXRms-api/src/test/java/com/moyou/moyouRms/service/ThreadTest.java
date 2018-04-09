package com.moyou.moyouRms.service;

/**
 * @author 作者:陈旭
 * @version 创建时间：2017年7月25日 上午11:09:39 类说明
 */
public class ThreadTest
{
    @SuppressWarnings("static-access")
    public static void main(String args[])
    {
        Thread thread = Thread.currentThread();
        System.out.println("当前线程:" + thread);

        thread.setName("hyh thread");// 修改线程名称

        System.out.println("修改名称之后:" + thread);

        try
        {
            for (int a = 5; a > 0; a--)
            {
                System.out.println(a);

                thread.sleep(1000L);
            }
        }
        catch (Exception e)
        {
            System.out.println("出现异常");
        }
    }
}
