package com.ashish.customlog4j;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Layout;
import org.apache.log4j.RollingFileAppender;
import org.apache.log4j.spi.ErrorCode;



public class CustomLog4JAppender extends RollingFileAppender  {
	public CustomLog4JAppender() {
	}
	
	public CustomLog4JAppender(Layout layout, String filename,
	        boolean append) throws IOException {
	    super(layout, filename, append);
	}

	public CustomLog4JAppender(Layout layout, String filename)
	        throws IOException {
	    super(layout, filename);
	}
	
	public void activateOptions() {
		if (fileName != null) {
		    try {
		    	fileName = getNewLogFileName();
		        setFile(fileName, fileAppend, bufferedIO, bufferSize);
		    } catch (Exception e) {
		        errorHandler.error("Error while activating log options", e,
		                ErrorCode.FILE_OPEN_FAILURE);
		    }
		}
		}

		private String getNewLogFileName() {
			DateFormat df = new SimpleDateFormat("dd-MMM-yyyy-HH-mm-ss");
			
			if (fileName != null) {
			    final String DOT = ".";
			    final String HIPHEN = "-";
			    final File currentLogFile = new File(fileName);
			    final String fileName = currentLogFile.getName();
			    String newLogFileName = fileName;
			    
			    final int dotIndex = fileName.indexOf(DOT);
			    if (dotIndex != -1) {
			        // the file name has an extension. so, insert the time stamp
			        // between the file name and the extension
			        newLogFileName = fileName.substring(0, dotIndex)  + HIPHEN +
			                   df.format(new Date())  + DOT +
			                  fileName.substring(dotIndex+1);
			    } else {
			        // the file name has no extension. So, just append the timestamp
			        // at the end.
			        newLogFileName = fileName + HIPHEN  + System.currentTimeMillis();
			    }
			    return currentLogFile.getParent() + File.separator + newLogFileName;
			}
			return null;
		}
}
