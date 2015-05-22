package com.ashish.log4jIntegration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.LogManager;

import com.ashish.anotherpackage.AnotherClass;

public class Log4JIntegration {
	// org.apache.commons.logging.Log and org.apache.log4j.spi.LoggerFactory comes from commons-logging.jar
	private static final Log LOG = LogFactory.getLog(Log4JIntegration.class);
	public static void main(String args[]) {
		// Want to print this below line always. So LOG.fatal() is used
		LOG.fatal("Log level; for com.ashish.log4jIntegration package is : " + LogManager.getRootLogger().getLevel());
		LOG.trace("TRACE: Rank 6");
		LOG.debug("DEBUG: Rank 5");
		LOG.info("INFO: Rank 4");
		LOG.warn("WARN: Rank 3");
		LOG.error("ERROR: Rank 2");
		LOG.fatal("FATAL: Rank 1");
		
		AnotherClass.anotherMethod();
	}
}
