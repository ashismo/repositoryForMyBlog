package com.test.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.ObjectMapper;

public class JunitTestUtil {
	public static String getFileContent(String fileName) {

		String fieContent = "";

		ClassLoader classLoader = JunitTestUtil.class.getClassLoader();
		try {
			fieContent = IOUtils.toString(classLoader.getResourceAsStream(fileName));
		} catch (IOException e) {
			System.out.println("Error while reading " + fileName + " file: ");
			e.printStackTrace();
		}

		return fieContent;

	}

	public static void writeJSONToFile(Object json, String path) {
		try {
			ObjectMapper om = new ObjectMapper();
			String indented = om.writerWithDefaultPrettyPrinter()
					.writeValueAsString(json);
			path = new File(".").getAbsolutePath()
					+ "\\..\\userguide\\src\\test\\resources\\" + path;

			String absolutePath = path.substring(0, path.lastIndexOf("/"));

			File f = new File(absolutePath);
			if (!f.isDirectory()) {
				System.out.println("Create directory: " + absolutePath);
				f.mkdirs();
			}
			PrintWriter print = new PrintWriter(path);
			print.println(indented);
			print.close();
			System.out.println("Output file: " + path);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error: ");
			e.printStackTrace();
		}

	}
}
