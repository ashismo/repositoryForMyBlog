package com.ashish.threadjoin.services;

import com.ashish.threadjoin.beans.AlertOutput;
import com.ashish.threadjoin.beans.User;

public class MainClass {
	public static void main(String[] args) {
		AlertServices alertServices = new AlertServices();
		User u = new User();
		u.setUsername("ASHISH");
		
		// PARALLEL EXECUTION
		try {
			AlertOutput alert = alertServices.getAllAlerts(u);
			System.out.println("1---> " + alert.getAlert1());
			System.out.println("2---> " + alert.getAlert2());
			System.out.println("3---> " + alert.getAlert3());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		// SERIAL EXECUTION
		try {
			AlertOutput alert = alertServices.getAllAlertsSerialExecution(u);
			System.out.println("1---> " + alert.getAlert1());
			System.out.println("2---> " + alert.getAlert2());
			System.out.println("3---> " + alert.getAlert3());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
