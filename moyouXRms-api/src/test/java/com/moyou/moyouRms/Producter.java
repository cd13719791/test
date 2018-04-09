package com.moyou.moyouRms;

import java.util.Vector;

public class Producter implements Runnable{

	@SuppressWarnings("rawtypes")
	private Vector obj;
	@SuppressWarnings("rawtypes")
	public Producter(Vector v) {
	this.obj = v;
	}
	@SuppressWarnings("unchecked")
	public void run() {
	synchronized(obj) {
	while (true) {
	try {
	if (obj.size() !=0){
	obj.wait();
	}

	obj.add(new String("apples"));
	obj.notify();
	System.out.println("Producter:obj are ready"+"生产者");
	Thread.sleep(500);
	}
	catch(Exception e) {
	e.printStackTrace();
	}
	}
	}
	}
}
