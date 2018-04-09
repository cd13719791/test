/**
 */
package com.moyou.moyouRms.task.timer;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @describe TODO
 * @author liuxinyi
 * @date 2017年3月30日
 * @email liuxinyi@mousns.com
 * @version 1.0.0
 */
public class ScheduledExecutorTest {
	private ScheduledExecutorService scheduExec;
	public Date scheduExecStart;

	ScheduledExecutorTest() {
		this.scheduExec = Executors.newScheduledThreadPool(2);
		this.scheduExecStart = new Date();
	}

	public void timerOne() {
		scheduExec.schedule(new Runnable() {
			public void run() {
				System.out.println("timerOne ");
			}
		}, 1000, TimeUnit.MILLISECONDS);
	}

	public void timerTwo() {
		scheduExec.scheduleAtFixedRate(new Runnable() {
			public void run() {
				System.out.println("timerTwo invoked .....");
			}
		}, 2000, 500, TimeUnit.MILLISECONDS);
	}

	public static void main(String[] args) {
		ScheduledExecutorTest test = new ScheduledExecutorTest();
		test.timerOne();
		// test.timerTwo();
	}

}