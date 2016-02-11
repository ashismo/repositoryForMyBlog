package com.org.test.coop.junit;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

public class JunitTestUtil {
	private static final Logger logger = Logger.getLogger(JunitTestUtil.class);
	/**
	 * This method returns file content into string
	 * @param fileName
	 * @return
	 */
	public static String getFileContent(String fileName) {

		String fieContent = "";

		ClassLoader classLoader = JunitTestUtil.class.getClassLoader();
		try {
			fieContent = IOUtils.toString(classLoader.getResourceAsStream(fileName));
		} catch (IOException e) {
			logger.error("Error while reading " + fileName + " file: ", e);
		}

		return fieContent;

	}
}
