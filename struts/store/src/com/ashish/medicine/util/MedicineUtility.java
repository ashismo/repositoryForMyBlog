package com.ashish.medicine.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.type.NullableType;

public class MedicineUtility {
	public static void main(String args[]) {
		MedicineUtility mUtil = new MedicineUtility();
		System.out.println(mUtil.getLastDayOfWeek(new Date()));
	}
	public Date getDateFromString(String dateFormat) {
	   DateFormat formatter ; 
	   Date date = null; 
	   dateFormat = dateFormat.replaceAll("/", "-");
	   formatter = new SimpleDateFormat("dd-MM-yyyy");
	   try {
			date = (Date)formatter.parse(dateFormat);
		} catch (ParseException e) {
			e.printStackTrace();
		}  
		return date;
	}
	
	public String getDateStrFromDate(Date date) {
		if(date==null) return null;
		String dateStr = "";
		try {
			 DateFormat formatter ; 
			  formatter = new SimpleDateFormat("dd/MM/yyyy");
			  dateStr = formatter.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}  
		return dateStr;
	}
	
	public String getMfgAndExpDateFormat(String date) {
		if(date==null) return null;
		date = date.substring(date.trim().indexOf("/")+1, date.trim().length());
		return date;
	}
	
	public Date getfirstDayOfWeek(Date inputDate) {
		if(inputDate != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(inputDate);
			int day = cal.get(Calendar.DAY_OF_WEEK);
			return getDateSubtractedByDays(inputDate, (day - 1));
			
		} 
		return null;
	}
	
	public Date getLastDayOfWeek(Date inputDate) {
		if(inputDate != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(inputDate);
			int day = cal.get(Calendar.DAY_OF_WEEK);
			return getDateSubtractedByDays(inputDate, (day - 7));
			
		} 
		return null;
	}
	
	public Date getDateSubtractedByDays(int noOfDays) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.format(calendar.getTime());
		    
		// How do something like this (notice the minus sign):
		calendar.add(Calendar.DAY_OF_MONTH, - noOfDays);
		Date subStracteddate = calendar.getTime();
		
		return subStracteddate;
	}
	
	public Date getDateSubtractedByDays(Date inputDate, int noOfDays) {
		Calendar calendar = Calendar.getInstance();
		if(inputDate != null) {
			calendar.setTime(inputDate);
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.format(calendar.getTime());
		
		// How do something like this (notice the minus sign):
		calendar.add(Calendar.DAY_OF_MONTH, - noOfDays);
		Date subStracteddate = calendar.getTime();
		
		return subStracteddate;
	}
	
	public String getFormattedAmount(double amt) {
		DecimalFormat df = new DecimalFormat("##.00");
		return(df.format(amt));
	}
	
	public List<String> getDayAndDatesOfAWeek(Date inputDate) {
		List<String> dayAndDateList = new ArrayList<String>();
		List<Date> dateList = new ArrayList<Date>();
		Date firstDayOfWeek = getfirstDayOfWeek(inputDate);
		
		for(int i = 0; i < 7; i++) {
			dateList.add(getDateSubtractedByDays(firstDayOfWeek, -i));
		}
		String dayAndDate = "";
		Calendar cal = Calendar.getInstance();
		
		int day = 0;
		
		for(Date date: dateList) {
			cal.setTime(date);
			day = cal.get(Calendar.DAY_OF_WEEK);
			if(day == Calendar.SUNDAY){
			  dayAndDate = "Sunday-" + getDateStrFromDate(date);
			  dayAndDateList.add(dayAndDate);
			} else if(day == Calendar.MONDAY){
			  dayAndDate = "Monday-" + getDateStrFromDate(date);
			  dayAndDateList.add(dayAndDate);
			} else if(day == Calendar.TUESDAY){
			  dayAndDate = "Tuesday-" + getDateStrFromDate(date);
			  dayAndDateList.add(dayAndDate);
			} else if(day == Calendar.WEDNESDAY){
			  dayAndDate = "Wednesday-" + getDateStrFromDate(date);
			  dayAndDateList.add(dayAndDate);
			} else if(day == Calendar.THURSDAY){
			  dayAndDate = "Thursday-" + getDateStrFromDate(date);
			  dayAndDateList.add(dayAndDate);
			} else if(day == Calendar.FRIDAY){
			  dayAndDate = "Friday-" + getDateStrFromDate(date);
			  dayAndDateList.add(dayAndDate);
			} else if(day == Calendar.SATURDAY){
			  dayAndDate = "Saturday-" + getDateStrFromDate(date);
			  dayAndDateList.add(dayAndDate);
			}
		}
		
		return dayAndDateList;
	}
	
	public void setScalarInQuery(SQLQuery query, String propertyString) {

        StringTokenizer scalarMatch = new StringTokenizer(propertyString, ",");
        while(scalarMatch.hasMoreTokens()){
                String token = scalarMatch.nextToken();
                String[] nameDataType = token.split("-");
                if(nameDataType.length == 2) {
                        String attrName =  nameDataType[0].trim();
                        String attrDataType =  nameDataType[1].trim();
                        NullableType hibernateDataType = null;
                        if("long".equalsIgnoreCase(attrDataType)) {
                                hibernateDataType = Hibernate.LONG;
                        } else if("integer".equalsIgnoreCase(attrDataType)) {
                                hibernateDataType = Hibernate.INTEGER;
                        } else if("String".equalsIgnoreCase(attrDataType)) {
                                hibernateDataType = Hibernate.STRING;
                        } else if("date".equalsIgnoreCase(attrDataType)) {
                                hibernateDataType = Hibernate.DATE;
                        } else if("double".equalsIgnoreCase(attrDataType)) {
                                hibernateDataType = Hibernate.DOUBLE;
                        }
                        else if("BigInteger".equalsIgnoreCase(attrDataType)) {
                                hibernateDataType = Hibernate.BIG_INTEGER;
                        }
                        else if("short".equalsIgnoreCase(attrDataType)) {
                                hibernateDataType = Hibernate.SHORT;
                        }
                        else if("Timestamp".equalsIgnoreCase(attrDataType)) {
                                hibernateDataType = Hibernate.TIMESTAMP;
                        }
                        else if("float".equalsIgnoreCase(attrDataType)) {
                                hibernateDataType = Hibernate.FLOAT;
                        }
                        query.addScalar(attrName, hibernateDataType);
                }
        }
}
}
