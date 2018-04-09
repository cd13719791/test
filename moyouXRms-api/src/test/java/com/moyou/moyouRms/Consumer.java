package com.moyou.moyouRms;

import java.util.Vector;

public class Consumer implements Runnable{
	@SuppressWarnings("rawtypes")
	private Vector obj;
	@SuppressWarnings("rawtypes")
	public Consumer(Vector v) {
	this.obj = v;
	}
	public void run() {
	synchronized(obj) {
	while (true) {
	try {
	if (obj.size() ==0){
	obj.wait();
	}
	System.out.println("Consumer:goods have been taken"+"消费者");
	System.out.println("obj size: " + obj.size());
	obj.clear();
	obj.notify();
	}
	catch(Exception e) {
	e.printStackTrace();
	}
    }
	}
	}
}
