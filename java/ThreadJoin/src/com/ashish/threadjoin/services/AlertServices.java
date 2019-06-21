package com.ashish.threadjoin.services;

import com.ashish.threadjoin.beans.AlertOutput;
import com.ashish.threadjoin.beans.User;

public class AlertServices {
	
	User user = null;
	
	public AlertOutput getAllAlerts(User user) throws Exception {
		System.out.println("START PARALLEL EXECUTION");
		
		long startTime = System.nanoTime();
		
		AlertOutput alertOutput = new AlertOutput();
		this.user = user;
		
		// Alert thread1
		Thread t1 = new Thread(()->
		{
			AlertOutput tempAlert = alert1(user);
			alertOutput.setAlert1(tempAlert.getAlert1());
		});
		
		// Alert thread2
		Thread t2 = new Thread(()->
		{
			AlertOutput tempAlert = alert2(user);
			alertOutput.setAlert2(tempAlert.getAlert2());
		});
		
		
		// Alert thread3
		Thread t3 = new Thread(()->
		{
			AlertOutput tempAlert = alert3(user);
			alertOutput.setAlert3(tempAlert.getAlert3());
		});
		
		
		t1.start();
		t2.start();
		t3.start();
		
		t1.join();
		t2.join();
		t3.join();
		
		System.out.println("END PARALLEL EXECUTION");
		long endTime = System.nanoTime();
		System.out.println("Execution time: " + (endTime - startTime)/1000000000 + " sec");
		return alertOutput;
	}
	
	
	
	public AlertOutput getAllAlertsSerialExecution(User user) throws Exception {
		
		System.out.println("START SERIAL EXECUTION");
	
		long startTime = System.nanoTime();
		
		AlertOutput alertOutput = new AlertOutput();
		this.user = user;
		
		AlertOutput tempAlert = alert1(user);
		alertOutput.setAlert1(tempAlert.getAlert1());
		
		tempAlert = alert2(user);
		alertOutput.setAlert2(tempAlert.getAlert2());
		
		tempAlert = alert3(user);
		alertOutput.setAlert3(tempAlert.getAlert3());
		
		System.out.println("END SERIAL EXECUTION");
		long endTime = System.nanoTime();
		System.out.println("Serial Execution time: " + (endTime - startTime)/1000000000 + " sec");
		return alertOutput;
	}
	
	
	
	
	private AlertOutput alert1(User u) {
		System.out.println("ALERT1 User: " + u.getUsername());
		AlertOutput alert = new AlertOutput();
		alert.setAlert1("ALERT1");
		// Assume each service takes 5 sec to execute
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alert;
	}
	
	private AlertOutput alert2(User u) {
		System.out.println("ALERT2 User: " + u.getUsername());
		AlertOutput alert = new AlertOutput();
		alert.setAlert2("ALERT2");
		// Assume each service takes 5 sec to execute
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alert;
	}
	
	private AlertOutput alert3(User u) {
		System.out.println("ALERT3 User: " + u.getUsername());
		AlertOutput alert = new AlertOutput();
		alert.setAlert3("ALERT3");
		// Assume each service takes 5 sec to execute
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alert;
	}
}
