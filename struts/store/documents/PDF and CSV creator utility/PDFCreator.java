package com.ashish.medicine.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PDFCreator {
	
	/**
	 * This method takes Title of the list, array of columns name and their width separated by ^ 
	 * (e.g. col1^10, col2^8)and the pdf content in two dimensional string array as input 
	 * and returns InputStream of generated PDF 
	 * @param title
	 * @param header
	 * @param pdfContent
	 * @return
	 * @throws Exception
	 */
	public InputStream createPDF(String title, String[] header, String pdfContent[][]) throws Exception {
         try {
        	 final String SEPERATOR = "\\^";
			 Document document = new Document(PageSize.A3, 10, 10, 10, 10);
			 PdfPCell hCell;
			 String colNames[] = new String[header.length];
			 float[] colWidth = new float[header.length];
			 int count = 0;
			 for(String colNameAndWidth: header) {
				 if(colNameAndWidth != null) {
					 if(colNameAndWidth.split(SEPERATOR).length == 1) {
						 colNames[count] = colNameAndWidth.split(SEPERATOR)[0];
						 colWidth[count] = 8f;
					 } else {
						 colNames[count] = colNameAndWidth.split(SEPERATOR)[0];
						 colWidth[count] = Float.parseFloat(colNameAndWidth.split(SEPERATOR)[1]);
					 }
				 }
				 count++;
			 }
			 List<String> pdfHeader = getPDFHeader(colNames);
			 int columnCount = pdfHeader.size();
			 
			 
			 ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
			 PdfPTable ptable = new PdfPTable(columnCount);
			 ptable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			 ptable.setWidthPercentage(100);
			 ptable.setWidths(colWidth);
			 PdfWriter.getInstance(document, bos);
			document.open();
			hCell = new PdfPCell(new Phrase(title,
			        FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD)));
			hCell.setBorder(Rectangle.BOTTOM);
			ptable.addCell(hCell);
			
			for (int i = 1; i <= columnCount - 1; i++) {
                hCell = new PdfPCell(new Phrase("", FontFactory.getFont(
                        FontFactory.TIMES_ROMAN, 7, Font.BOLD)));
                hCell.setBorder(Rectangle.BOTTOM);
                ptable.addCell(hCell);
            }

            for (String pdfColumn : pdfHeader) {
                hCell = new PdfPCell(new Phrase(pdfColumn, FontFactory.getFont(
                        FontFactory.TIMES, 7, Font.BOLD)));
                hCell.setBorder(Rectangle.NO_BORDER);
                ptable.addCell(hCell);
            }
            String[] bdCell1 = null;
            
            if(pdfContent != null) {
            	for(String[] rowContent: pdfContent) {
            		bdCell1 = new String[columnCount];
            		int colIndex = 0;
            		for(String colContent: rowContent) {
            			bdCell1[colIndex] = colContent;
            			colIndex++;
                	}
            		for (String pdfbody : bdCell1) {

                        hCell = new PdfPCell(new Phrase(pdfbody,
                                FontFactory.getFont(FontFactory.TIMES, 6)));
                        hCell.setBorder(Rectangle.NO_BORDER);
                        ptable.addCell(hCell);

                    }
            	}
            }
            document.add(ptable);
            document.close();
            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            return bis;
		} catch (DocumentException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}
	/**
	 * This method takes the pdf columns name to be displayed
	 * as input and returns the list of columns name
	 * @param header
	 * @return
	 */
	private List<String> getPDFHeader(String[] header) {
		List<String> pdfHeader = new ArrayList<String>();
		for(String pdfColumn: header) {
			pdfHeader.add(pdfColumn);
		}
        return pdfHeader;
	}
}
