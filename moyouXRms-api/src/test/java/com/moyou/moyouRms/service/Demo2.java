package com.moyou.moyouRms.service;

/**
 * @author 作者:陈旭
 * @version 创建时间：2017年7月25日 上午11:36:51 类说明
 */
public class Demo2 implements Runnable
{

    Thread t;

    // 空构造函数
    Demo2()
    {
        t = new Thread(this, "测试线程");
        System.out.println("子线程" + t);
        t.start();
    }

    public void run()
    {
        try
        {
            for (int a = 5; a > 0; a--)
            {
                System.out.println("子线程" + a);
                Thread.sleep(1000);
            }
        }
        catch (InterruptedException e)
        {
            System.out.println("异常");
        }
        System.out.println("退出子线程");
    }
}

/**
 * 主类
 */
class ThreadDemo
{
    public static void main(String args[])
    {
        new Demo2();// 创建一个新线程
        try
        {
            for (int i = 5; i > 0; i--)
            {
                System.out.println("主线程:" + i);
                Thread.sleep(1000);
            }
        }
        catch (InterruptedException e)
        {
            System.out.println("主线程异常");
        }
        // 主线程退出
        System.out.println("主线程退出");

    }
}
