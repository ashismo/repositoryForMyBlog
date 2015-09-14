package com.ashish.medicine.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;


public class CSVCreator {
	
	/**
	 * This method takes Title of the list, array of columns name 
	 * and the csv content in two dimensional string array as input 
	 * and returns InputStream of generated CSV 
	 * @param title
	 * @param header
	 * @param pdfContent
	 * @return
	 * @throws Exception
	 */
	public InputStream createCSV(String title, String[] header, String pdfContent[][]) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ByteArrayInputStream bis = null;
        final String DELIMETER = ",";
        StringBuilder csvHeader = getCSVHeader(header, DELIMETER);
		int columnCount = header.length;
		
		// Header row is created.
		baos.write(csvHeader.toString().getBytes()); 
		
		// Below code is to create content of the CSV file
		if(pdfContent != null) {
        	for(String[] rowContent: pdfContent) {
        		StringBuilder csvRecord1 = new StringBuilder();
        		for(String colContent: rowContent) {
        			csvRecord1.append(colContent + DELIMETER);
            	}
        		csvRecord1.append("\n");
        		baos.write(csvRecord1.toString().getBytes());
        	}
        }
		bis = new ByteArrayInputStream(baos.toByteArray());
        return bis;
	}
	/**
	 * This method takes the csv columns name to be displayed
	 * as input and returns the list of columns name
	 * @param header
	 * @return
	 */
	private StringBuilder getCSVHeader(String[] header, final String DELIMETER) {
		StringBuilder csvHeader = new StringBuilder();
		for(String csvColumn: header) {
			csvHeader.append(csvColumn + DELIMETER);
		}
		csvHeader.append('\n');
        return csvHeader;
	}
}
