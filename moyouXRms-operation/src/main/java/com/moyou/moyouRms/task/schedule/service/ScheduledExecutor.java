/**
 */
package com.moyou.moyouRms.task.schedule.service;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @describe 调度服务任务类
 * @author liuxinyi
 * @date 2017年3月30日
 * @email liuxinyi@mousns.com
 * @version 1.0.0
 */
public class ScheduledExecutor {
	private ScheduledExecutorService scheduledExecutorService;
	public Date scheduExecStart;

	ScheduledExecutor(int threadNum) {
		this.scheduledExecutorService = Executors.newScheduledThreadPool(threadNum);
		this.scheduExecStart = new Date();
	}

	public void timerOne() {
		scheduledExecutorService.schedule(new Runnable() {
			public void run() {
//				System.out.println("timerOne ");
			}
		}, 1000, TimeUnit.MILLISECONDS);
	}

	public void timerTwo() {
		scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
			public void run() {
				System.out.println("timerTwo invoked .....");
			}
		}, 2000, 500, TimeUnit.MILLISECONDS);
	}

	public static void main(String[] args) {
		ScheduledExecutor test = new ScheduledExecutor(2);
		test.timerOne();
		// test.timerTwo();
	}

}