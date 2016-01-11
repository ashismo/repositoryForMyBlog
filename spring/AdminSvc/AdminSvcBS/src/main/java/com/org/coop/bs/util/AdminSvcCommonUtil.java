package com.org.coop.bs.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AdminSvcCommonUtil {
	public static long diffInTwoDates(Date fromDate, Date toDate) {
		if(fromDate == null || toDate == null) {
			return 0;
		}
		return (toDate.getTime() - fromDate.getTime())/(24 * 60 * 60 * 1000);
	}
	
	public static void main(String args[]) {
		String dateStart = "01/14/2012";
		String dateStop = "01/13/2012";

		//HH converts hour in 24 hours format (0-23), day calculation
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

		Date d1 = null;
		Date d2 = null;

		try {
			d1 = format.parse(dateStart);
			d2 = format.parse(dateStop);
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println(diffInTwoDates(d1, d2));
	}
}
