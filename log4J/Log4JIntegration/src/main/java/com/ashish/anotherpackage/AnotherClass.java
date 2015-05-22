package com.ashish.anotherpackage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.LogManager;

public class AnotherClass {
	// org.apache.commons.logging.Log and org.apache.log4j.spi.LoggerFactory comes from commons-logging.jar
	private static final Log LOG = LogFactory.getLog(AnotherClass.class);
	public static void anotherMethod() {
		LOG.fatal("Log level; for com.ashish.anotherpackage package is : " + LogManager.getLogger(AnotherClass.class).getEffectiveLevel());
		LOG.trace("TRACE: Rank 6");
		LOG.debug("DEBUG: Rank 5");
		LOG.info("INFO: Rank 4");
		LOG.warn("WARN: Rank 3");
		LOG.error("ERROR: Rank 2");
		LOG.fatal("FATAL: Rank 1");
	}
}
