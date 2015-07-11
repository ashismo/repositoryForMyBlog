package com.ashish.thread;

import com.ashish.bean.Message;

public class Main {
	public static void main(String args[]) {
		Message msg1 = new Message();
		Message msg2 = new Message();
		Runnable r1 = new MyThread(msg1);
		Runnable r2 = new MyThread(msg2);
		
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		
		t1.setName("t1");
		t2.setName("t2");
		
		t1.start();
		t2.start();
	}		
}
