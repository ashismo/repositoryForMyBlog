package com.ashish.thread;

import com.ashish.bean.Message;

public class MyThread implements Runnable {

	private Message msg;
	
	public MyThread(Message msg) {
		this.msg = msg;
	}
	@Override
	public void run() {
		String threadName = Thread.currentThread().getName();
		
		System.out.println("Object level lock for Thread " + threadName +". Other thread will run concurrently");
			if("t1".equals(threadName)) {
				msg.setMsg("Thread1");
			} else {
				msg.setMsg("Thread2");
			}
			System.out.println("Thread " + threadName + " EXIT - object level lock");
			System.out.println("\n");
			synchronized (msg) {
				try {
					msg.wait(2000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			System.out.println("Class level lock for Thread " + threadName +". Other thread will wait until the first thread completes");
			if("t1".equals(threadName)) {
				Message.setsMsg("Thread1");
			} else {
				Message.setsMsg("Thread2");
			}
			System.out.println("Thread " + threadName + " EXIT - Class level lock");
	}
	
}
