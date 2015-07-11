package com.ashish.bean;

public class Message {
	private String msg;
	private static String sMsg;
	
	public String getMsg() {
		return msg;
	}
	public synchronized void setMsg(String msg) { // non static synchronized method
		this.msg = msg;
		for(int i = 0; i < 5; i++) {
			System.out.println(msg + " " + i);
		}
	}
	public static String getsMsg() {
		return sMsg;
	}
	public static synchronized void setsMsg(String sMsg) { // static synchronized method
		Message.sMsg = sMsg;
		for(int i = 0; i < 5; i++) {
			System.out.println(sMsg + " " + i);
		}
	}
	
	
}
