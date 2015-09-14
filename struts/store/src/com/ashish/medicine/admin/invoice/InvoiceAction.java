package com.ashish.medicine.admin.invoice;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringBufferInputStream;
import java.net.MalformedURLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.SqlTimestampConverter;
import org.apache.struts2.ServletActionContext;
//import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.ashish.medicine.admin.base.MedicineBaseAction;
import com.ashish.medicine.admin.company.CompanyBean;
import com.ashish.medicine.admin.company.CompanyServiceImpl;
import com.ashish.medicine.admin.medicine.MedicineBean;
import com.ashish.medicine.admin.medicine.MedicineServiceImpl;
import com.ashish.medicine.admin.myaccount.MyAccountBean;
import com.ashish.medicine.admin.myaccount.MyAccountServiceImpl;
import com.ashish.medicine.exception.AppException;
import com.ashish.medicine.util.MedicineUtility;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class InvoiceAction extends MedicineBaseAction implements ModelDriven<InvoiceBean> {
	private InvoiceBean invoiceBean = new InvoiceBean();
	private MyAccountBean myaccountBean = new MyAccountBean();
	private static final long serialVersionUID = -2613425890762568273L;
	private InputStream inputStream;
	private String filename;
	private int total;
	private List<InvoiceBean> footer; 
	private List<InvoiceBean> totalRecords; 
	private String msg;
	private final String SUCCESS_MSG = "Successfully updated";
	private final String FAILED_MSG = "Operation failed!!!!!";

	private int invoiceId;
	private String schedule;
	private Timestamp dbAddTs;
	private int dbAddUser;
	private Timestamp dbUpdTs;
	private int dbUpdUser;
	private String emailId;
	private String fax;
	private String mob1;
	private String mob2;
	private String phone1;
	private String phone2;
	private String pin;
	private String state;
	private String website;
	private String desc;
	private double totalPrice;
	private double totalAmt;
	private double totalPaid;
	private String paymentMode;
	
	private int invoiceDetailsId;
	private int medicineDetailsId;
	private int medicineId;
	private String batchId;
	private String batchName;
	private int companyId;
	private String companyName;
	private int stock;
	private int availableStock;
	private int soldoutStock;
	private double unitPrice;
	private String mfgDate;
	private String expDate;
	private Date mfgDateActual;
	private Date expDateActual;
	private String billDate;
	private Date billDateActual;
	
	private int ownerId;
	private String ownerAddr1;
	private String ownerAddr2;
	private String ownerDesc;
	private String ownerName;
	private int doctorId;
	private String doctorName;
	private int customerId;
	private String customerAddr1;
	private String customerAddr2;
	private String customerName;
	
//	public InvoiceAction() {
//		ConvertUtils.register(new SqlTimestampConverter(null), java.sql.Timestamp.class);
//		ConvertUtils.register(new DateConverter(null), java.util.Date.class);
//	}
	
	public String invoiceManagementDetails()
	{
		CompanyBean cBean = new CompanyBean();
		try {
			new CompanyServiceImpl().getAllCompanies(cBean);
			invoiceBean.setCompanyList(cBean.getSearchCompanyList());
			
			// fetch all batches
			MedicineBean medicineBean = new MedicineBean();
			if(invoiceBean.getCompanyList() != null && invoiceBean.getCompanyList().size() > 0) {
				medicineBean.setCompanyId(invoiceBean.getCompanyList().get(0).getCompanyId());
			}
			new MedicineServiceImpl().getMedicinesByCompanyId(medicineBean);
		} catch (AppException e) {
			e.printStackTrace();
		}
		return "success";		
	}
	
	/*public String invoiceItems()
	{
		List<InvoiceBean> searchList = invoiceBean.getSearchInvoiceList();
		InvoiceBean invoiceDetails = new InvoiceBean();
		invoiceDetails.setMedicineName("Test1");
		invoiceDetails.setExpDate("20/07/2012");
		invoiceDetails.setPhone1("9830525559");
		invoiceDetails.setPhone2("9830625559");
		invoiceDetails.setMob1("9830625559");
//		try {
//			new InvoiceServiceImpl().searchInvoice(invoiceBean);
//			setMsg(SUCCESS_MSG);
//		} catch (AppException e) {
//			e.printStackTrace();
//			setMsg(invoiceBean.getMsg());
//		}
		setInvoiceId(1);
		for(int i = invoiceBean.getPage() * invoiceBean.getRows(); i < (invoiceBean.getPage() + 1) * invoiceBean.getRows(); i++) {
			invoiceDetails.setInvoiceDetailsId(i);
			searchList.add(invoiceDetails);
		}
		totalRecords = invoiceBean.getSearchInvoiceList();
//		total = invoiceBean.getTotal();
		
		total = 4;
		
		
		return Action.SUCCESS;		
	}*/
	
	public String searchInvoice()
	{
		try {
			new InvoiceServiceImpl().searchInvoice(invoiceBean);
			setMsg(SUCCESS_MSG);
		} catch (AppException e) {
			e.printStackTrace();
			setMsg(invoiceBean.getMsg());
		}
		totalRecords = invoiceBean.getSearchInvoiceList();
		total = invoiceBean.getTotal();
		return Action.SUCCESS;	
	}
	
	public String viewInvoice()
	{
		try {
			new InvoiceServiceImpl().viewInvoice(invoiceBean);
			List<InvoiceBean> searchList = invoiceBean.getSearchInvoiceList();
			List<InvoiceBean> footerList = new ArrayList<InvoiceBean>();
			double grandTotal = invoiceBean.getGrandTotalPrice();
			InvoiceBean invoiceDetails = new InvoiceBean();
			invoiceDetails.setSchedule("Grand Total");
			invoiceDetails.setTotalPrice(grandTotal);
			footerList.add(invoiceDetails);
			footer = footerList;
			totalRecords = searchList;
			total = totalRecords.size();
			
			setMsg(SUCCESS_MSG);
		} catch (Exception e) {
			setMsg("Unable to view invoice");
			e.printStackTrace();
		}
		return "success";		
	}
	
	public String addInvoiceForm() {
		return ActionSupport.SUCCESS;
	}
	
	public String addOrUpdateInvoice()
	{
		System.out.println("Create/Update a new Invoice");
		try {
			new InvoiceServiceImpl().addOrUpdateInvoice(invoiceBean);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			setMsg(invoiceBean.getMsg());
		}
		
		return Action.SUCCESS;		
	}
	
	/**
	 * This adds or modifies the medicine list into invoice
	 * @return
	 */
	public String addOrUpdateInvoiceDetails()
	{
		System.out.println("Create/Update a new Invoice Details");
		try {
			new InvoiceServiceImpl().addOrUpdateInvoiceDetails(invoiceBean);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			setMsg(invoiceBean.getMsg());
		}
		
		return Action.SUCCESS;		
	}
	
	public String deleteInvoice()
	{
		System.out.println("Delete Invoice");
		try {
			new InvoiceServiceImpl().deleteInvoice(invoiceBean);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			setMsg(invoiceBean.getMsg());
		}
		
		return Action.SUCCESS;		
	}
	
	public String deleteMedicineIntoInvoice()
	{
		System.out.println("Delete Medicine from invoice");
		try {
			if(invoiceBean.getMedicineDetailsId() != 0) {
				new InvoiceServiceImpl().deleteMedicineIntoInvoice(invoiceBean);
			} else {
				invoiceBean.setMsg("Medicine Does not exist in our record");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			setMsg(invoiceBean.getMsg());
		}
		
		return Action.SUCCESS;		
	}

	
	public String printInvoice()
	{
		
		try {
			ServletContext context = ServletActionContext.getServletContext();
			
	        String logoPath = context.getRealPath("") + "\\resources\\js\\dashboard\\images\\shared\\logo.png";
	        String pdfFilePath = context.getRealPath(".") + "\\Invoice.pdf";
	        invoiceBean.setPdfPath(pdfFilePath);
	        invoiceBean.setLogoPath(logoPath);
	        Document document = new Document();
	        PdfWriter.getInstance(document, new FileOutputStream(pdfFilePath));
	        document.open();
		    
	        new InvoiceServiceImpl().printInvoice(invoiceBean);
	        new MyAccountServiceImpl().myAccount(myaccountBean);
	        viewInvoice();
	        addInvoiceContentIntoPDF(document);
	        
	        document.close();
			try {
				filename = "Invoice";
	            inputStream = new FileInputStream(pdfFilePath);
	            if(totalRecords != null && totalRecords.size() > 0) {
	            	InvoiceBean iBean = totalRecords.get(0);
	            	filename = iBean.getCustomerName() + "-" + iBean.getBillDate();
	            }
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            throw new AppException("");
	        } finally {
//	        	os.close();
	        }

		}catch(Exception e) {
			e.printStackTrace();
			return "failure";
		}
		return Action.SUCCESS;		
	}
	
	private void addInvoiceContentIntoPDF(Document document) throws DocumentException {
		  try {
//			document.setPageSize(PageSize.A7);
			addEmptyLine(document, 20);
//			addImage(document);
			addCustomerDetails(document);
			addEmptyLine(document, 35);
			createTable(document);
			addEmptyLine(document, 50);
			addSignature(document);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	  }

	  private void addSignature(Document document) 
	  		throws DocumentException, MalformedURLException, IOException {
		  Font headerFont = new Font(Font.FontFamily.TIMES_ROMAN, 12f, Font.BOLD);
		  Phrase p = new Phrase("" + getSpace(120) + "Signature" , headerFont);
      	  document.add(p);
	  }
	  private String getSpace(final int NO_OF_SPACE) {
		  String totalSpace = "";
		  for(int i = 0; i < NO_OF_SPACE; i++) {
			  totalSpace = totalSpace + " ";
		  }
		  return totalSpace;
	  }
	  private void addCustomerDetails(Document document)
  			throws DocumentException, MalformedURLException, IOException {
		  Font headerFont = new Font(Font.FontFamily.TIMES_ROMAN, 10f, Font.BOLD);
		  Font shopNameFont = new Font(Font.FontFamily.TIMES_ROMAN, 18f, Font.BOLD);
		  Font normalTextFont = new Font(Font.FontFamily.TIMES_ROMAN, 10f, Font.NORMAL);
		  Phrase p = null;
		  String customerName = "";
		  String doctorName = "";
		  String doctorAddr = "";
		  String customerAddr = "";
		  String billNo = "";
		  String billDate = "";
		  if(totalRecords != null && totalRecords.size() > 0) {
	        	for(InvoiceBean iBean : totalRecords) {
	        		if(iBean.getDoctorName() != null) doctorName = iBean.getDoctorName();
	        		if(iBean.getDoctorAddr1() != null) doctorAddr = iBean.getDoctorAddr1();
	        		customerName = iBean.getCustomerName();
	        		customerAddr = iBean.getCustomerAddr1();
	        		billNo = String.valueOf(iBean.getBillNo());
	        		billDate = iBean.getBillDate();
	        	}
	        	p = new Phrase("Bill No:" + billNo + getSpace(120) , headerFont);
	        	document.add(p);
	        	p = new Phrase("Date:" + billDate + "\n", headerFont);
	        	document.add(p);
	        	addBlankLine(document);
	        	addImage(document);
	        	addBlankLine(document);
	        	String shopName = myaccountBean.getShopName();
	        	p = new Phrase(shopName, shopNameFont);
	        	document.add(p);
	        	addBlankLine(document);
	        	// Add store details
	        	String address = myaccountBean.getOwnerAddr1() + "," + myaccountBean.getOwnerAddr2() + 
	        				"," + myaccountBean.getState() + ",PIN-" + myaccountBean.getPin();
	        	p = new Phrase("Shop No - " + myaccountBean.getShopNo() + "," + address, normalTextFont);
	        	document.add(p);
	        	
	        	String contactNo = myaccountBean.getMob1() + "/" + myaccountBean.getPhone1();
	        	p = new Phrase(",Contact:" + contactNo, normalTextFont);
	        	document.add(p);
	        	addBlankLine(document);
	        	
	        	p = new Phrase("Licence No:" + myaccountBean.getLicenceNo() + getSpace(20), normalTextFont);
	        	document.add(p);
	        	p = new Phrase("Baby Food Licence No:" + myaccountBean.getBabyFoodLcNo(), normalTextFont);
	        	document.add(p);
	        	addBlankLine(document);
	        	
	        	addEmptyLine(document, 25);
	        	p = new Phrase("Name:", headerFont);
	        	document.add(p);
	        	p = new Phrase(customerName + getSpace(5), normalTextFont);
	        	document.add(p);
	        	p = new Phrase("Address:", headerFont);
	        	document.add(p);
	        	p = new Phrase(customerAddr + "\n", normalTextFont);
	        	document.add(p);
	        	p = new Phrase("Doctor's Name:", headerFont);
	        	document.add(p);
	        	p = new Phrase(doctorName+ getSpace(5), normalTextFont);
	        	document.add(p);
	        	p = new Phrase("Doctor's Address:", headerFont);
	        	document.add(p);
	        	p = new Phrase(doctorAddr, normalTextFont);
	        	document.add(p);
		  }
	  }
	  private void createTable(Document document)
	      throws DocumentException, MalformedURLException, IOException {
		
	    PdfPTable table = new PdfPTable(8);
	    table.setWidths(new int[]{5,10,30,15,15,15,25,20});
	    // t.setBorderColor(BaseColor.GRAY);
	    // t.setPadding(4);
	    // t.setSpacing(4);
	    // t.setBorderWidth(1);
//	    addLogo(table);
	    String[] headerTitle = {"Sl","Qty","Medicine Name", "Mfg Date", "Batch", "Exp Date","Schedule","Price"};
	    Font headerFont = new Font(Font.FontFamily.TIMES_ROMAN, 10f, Font.BOLD);
	    for (String header: headerTitle) {
	    	Phrase p = new Phrase(header, headerFont);
	    	PdfPCell c1 = new PdfPCell(p);
		    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		    c1.setLeading(0f, 1.5f);
		    c1.setBackgroundColor(BaseColor.GRAY);
		    table.addCell(c1);
	    }
	    
	    Font contentFont = new Font(Font.FontFamily.TIMES_ROMAN, 9f);
	    MedicineUtility mUtil = new MedicineUtility();
//	    table.setSpacingAfter(20);
	    double vat = 0.0;
	    double discount = 0.0;
	    int count = 1;
	    double totalAmt = 0.0;
	    if(totalRecords != null && totalRecords.size() > 0) {
        	for(InvoiceBean iBean : totalRecords) {
//        		replaceDoctor = iBean.getDoctorName();
//        		replaceCustomer = iBean.getCustomerName();
//        		replaceCustAddr1 = iBean.getCustomerAddr1();
//        		replaceBillNo = iBean.getBillNo();
//        		replaceBillDate = iBean.getPurchaseDate();
        		vat = iBean.getVat();
        		discount = iBean.getDiscount();
        		PdfPCell c = new PdfPCell(new Phrase(count++ +"",contentFont));
        		table.addCell(c);
        		c = new PdfPCell(new Phrase(iBean.getSoldoutStock()+"",contentFont));
        	    table.addCell(c);
        	    table.addCell(new PdfPCell(new Phrase(iBean.getMedicineName(),contentFont)));
        	    table.addCell(new PdfPCell(new Phrase(iBean.getMfgDate(),contentFont)));
        	    table.addCell(new PdfPCell(new Phrase(iBean.getBatchName(),contentFont)));
        	    table.addCell(new PdfPCell(new Phrase(iBean.getExpDate(),contentFont)));
        	    table.addCell(new PdfPCell(new Phrase(iBean.getSchedule(),contentFont)));
        	    totalAmt = totalAmt + (iBean.getSoldoutStock()*iBean.getSoldoutUnitPrice());
        	    table.addCell(new PdfPCell(new Phrase(mUtil.getFormattedAmount((iBean.getSoldoutStock()*iBean.getSoldoutUnitPrice())),contentFont)));
        	}
	    }
	    double grandTotal = 0.0;
	    
	    // Total
	    PdfPCell c1 = new PdfPCell(new Phrase("Total"));
	    c1.setColspan(7);
	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(c1);
	    
	    c1 = new PdfPCell(new Phrase(mUtil.getFormattedAmount(totalAmt)));
	    c1.setColspan(1);
	    table.addCell(c1);
	    
	    // Vat
	    c1 = new PdfPCell(new Phrase("Vat(" + vat + "%)"));
	    c1.setColspan(7);
	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(c1);
	    
	    double vatAmt = totalAmt*vat/100;
	    c1 = new PdfPCell(new Phrase( mUtil.getFormattedAmount(vatAmt)));
	    c1.setColspan(1);
	    table.addCell(c1);
	    
	    // Discount
	    c1 = new PdfPCell(new Phrase("Discount(" + discount + "%)"));
	    c1.setColspan(7);
	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(c1);
	    
	    double discountAmt = totalAmt*discount/100;
	    c1 = new PdfPCell(new Phrase( mUtil.getFormattedAmount(discountAmt)));
	    c1.setColspan(1);
	    table.addCell(c1);
	   
	    // Grand Total
	    c1 = new PdfPCell(new Phrase("Grand Total"));
	    c1.setColspan(7);
	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(c1);
	    
	    grandTotal = (totalAmt + vatAmt - discountAmt);
	    c1 = new PdfPCell(new Phrase(mUtil.getFormattedAmount(grandTotal)));
	    c1.setColspan(1);
	    table.addCell(c1);
	    
	    document.add(table);
	  }


	  private void addEmptyLine(Document document, int noOfLines) throws DocumentException {
		  document.add(new Paragraph(noOfLines, " "));
	  }
	  
	  public void addLogo (PdfPTable table) throws BadElementException, MalformedURLException, IOException {
			String logoFilePath = "C:/Ashish/Java/logo.png";
			Image logo = Image.getInstance(logoFilePath);

			table.setWidthPercentage(100);
			
			PdfPCell logocell = new PdfPCell();
			logocell.setBorder(Rectangle.NO_BORDER);
			logocell.addElement(new Chunk(logo, 0, 5));
			
			table.addCell(logocell);
			
			logocell = new PdfPCell();
			logocell.setBorder(Rectangle.NO_BORDER);
			
			table.addCell(logocell);
			table.addCell(logocell);
			table.addCell(logocell);
			table.addCell(logocell);
			table.addCell(logocell);
		}
	  
	  public void addImage(Document document) throws DocumentException, MalformedURLException, IOException  {
			Image logo = Image.getInstance(invoiceBean.getLogoPath());
			document.add(new Chunk(logo, 0, 5));
	}
	  
	  public void addBlankLine(Document document) throws DocumentException, MalformedURLException, IOException  {
			Phrase p = new Phrase("\n");
        	document.add(p);
	}
	  
	/*public String printInvoiceFromHtml()
	{
		
		try {
			ServletContext context = ServletActionContext.getServletContext();
			
	        String htmlFilePath = context.getRealPath("") + "\\resources\\template\\InvoiceTemplate.html";
	        String pdfFilePath = context.getRealPath(".") + "\\Invoice.pdf";
	        FileInputStream fstream = new FileInputStream(htmlFilePath);
	        // Get the object of DataInputStream
	        DataInputStream in = new DataInputStream(fstream);
	        BufferedReader br = new BufferedReader(new InputStreamReader(in));
	        new InvoiceServiceImpl().printInvoice(invoiceBean);
	        viewInvoice();
	        MyAccountBean myaccountBean = new MyAccountBean();
	        new MyAccountServiceImpl().myAccount(myaccountBean);
	        String templateMedicineListTr = "<tr class=\"REPLACE_STYLE\">" +
										      	"<td width=\"5%\">REPLACE_QTY</td>" +
										      	"<td width=\"25%\">REPLACE_DESC</td>" +
										      	"<td width=\"10%\">REPLACE_MFG</td>" +
										      	"<td width=\"10%\">REPLACE_BATCH</td>" +
										      	"<td width=\"10%\">REPLACE_EXP</td>" +
										      	"<td width=\"15%\">REPLACE_SCHEDULE</td>" +
//										      	"<td width=\"10%\">REPLACE_UNIT_PRICE</td>" +
										      	"<td width=\"10%\">REPLACE_PRICE</td>" +
										      	"<td width=\"5%\"></td>" +
										      "</tr>";
	        String replaceTR = "";
	        String replaceDoctor = "";
	        String replaceCustomer = "";
	        String replaceCustAddr1 = "";
	        int replaceBillNo = 0;
	        String replaceBillDate = "";
	        
	        double grandTotal = 0.0;
	        if(totalRecords != null && totalRecords.size() > 0) {
	        	int count = 1;
	        	for(InvoiceBean iBean : totalRecords) {
	        		String tempStr = templateMedicineListTr;
	        		if(count%2 == 0) {
	        			tempStr = tempStr.replace("REPLACE_STYLE", "odd");
	        		} else {
	        			tempStr = tempStr.replace("REPLACE_STYLE", "even");
	        		}
	        		count++;
	        		tempStr = tempStr.replace("REPLACE_QTY", iBean.getSoldoutStock()+"");
	        		tempStr = tempStr.replace("REPLACE_DESC", iBean.getMedicineName());
	        		tempStr = tempStr.replace("REPLACE_MFG", iBean.getMfgDate());
	        		tempStr = tempStr.replace("REPLACE_BATCH", iBean.getBatchName());
	        		tempStr = tempStr.replace("REPLACE_EXP", iBean.getExpDate());
	        		tempStr = tempStr.replace("REPLACE_SCHEDULE", iBean.getSchedule());
//	        		tempStr = tempStr.replace("REPLACE_UNIT_PRICE", iBean.getSoldoutUnitPrice() + "");
	        		tempStr = tempStr.replace("REPLACE_PRICE", (iBean.getSoldoutStock()*iBean.getSoldoutUnitPrice())+"");
	        		replaceTR = replaceTR + "\n" + tempStr;
	        		
	        		replaceDoctor = iBean.getDoctorName();
	        		replaceCustomer = iBean.getCustomerName();
	        		replaceCustAddr1 = iBean.getCustomerAddr1();
	        		replaceBillNo = iBean.getBillNo();
	        		replaceBillDate = iBean.getPurchaseDate();
	        	}
	        }
	        if(footer != null && footer.size() > 0) {
	        	grandTotal = footer.get(0).getTotalPrice();
	        }
			String fileContent = "";
			String line = "";
			while ((line = br.readLine()) != null)   {
				if(line != null && line.trim().equals("REPLACE_TR")) {
					line = replaceTR;
				}
				if(line != null && line.contains("REPLACE_DOCTOR")) {
					line = line.replace("REPLACE_DOCTOR", replaceDoctor);
				}
				if(line != null && line.contains("REPLACE_NAME")) {
					line = line.replace("REPLACE_NAME",replaceCustomer);
				}
				if(line != null && line.contains("REPLACE_ADDRESS")) {
					line = line.replace("REPLACE_ADDRESS",replaceCustAddr1);
				}
				if(line != null && line.contains("REPLACE_TOTAL_AMT")) {
					line = line.replace("REPLACE_TOTAL_AMT",grandTotal+"");
				}
				if(line != null && line.contains("REPLACE_BILL_NO")) {
					line = line.replace("REPLACE_BILL_NO",replaceBillNo+"");
				}
				if(line != null && line.contains("REPLACE_BILL_DATE")) {
					line = line.replace("REPLACE_BILL_DATE",replaceBillDate);
				}
				if(line != null && line.contains("REPLACE_SHOP_NAME")) {
					line = line.replace("REPLACE_SHOP_NAME",myaccountBean.getShopName().trim());
				}
				if(line != null && line.contains("REPLACE_LICENCE")) {
					String licenseNo = "Licence: " + myaccountBean.getLicenceNo();
					if(myaccountBean.getBabyFoodLcNo() != null && 
							!myaccountBean.getLicenceNo().trim().equals("") &&
							!myaccountBean.getLicenceNo().trim().equals("NA")) {
						licenseNo = licenseNo + "/Baby Food Licence: " + myaccountBean.getBabyFoodLcNo().trim();
					}
					line = line.replace("REPLACE_LICENCE",licenseNo);
				}
				if(line != null && line.contains("REPLACE_SHOP_ADDRESS")) {
					line = line.replace("REPLACE_SHOP_ADDRESS",myaccountBean.getOwnerAddr1());
				}
				if(line != null && line.contains("REPLACE_PHONE")) {
					line = line.replace("REPLACE_PHONE",myaccountBean.getPhone1());
				}
				if(line != null && line.contains("REPLACE_MOBILE")) {
					line = line.replace("REPLACE_MOBILE",myaccountBean.getMob1());
				}
				fileContent += line;                      
			} 
			in.close();

			OutputStream os = new FileOutputStream(pdfFilePath);
			try {
	            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	            Document doc = builder.parse(new StringBufferInputStream(fileContent));
	            ITextRenderer renderer = new ITextRenderer();
	            renderer.setDocument(doc, null);
	            renderer.layout();
	            renderer.createPDF(os);
	            inputStream = new FileInputStream(pdfFilePath);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            throw new AppException("");
	        } finally {
	        	os.close();
	        }

		}catch(Exception e) {
			e.printStackTrace();
			return "failure";
		}
		return Action.SUCCESS;		
	}*/
	
	public String sellByMonths()
	{
		return "success";		
	}
	
	public InvoiceBean getInvoiceBean() {
		return invoiceBean;
	}

	public void setInvoiceBean(InvoiceBean invoiceBean) {
		this.invoiceBean = invoiceBean;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<InvoiceBean> getTotalRecords() {
		return totalRecords;
	}

	public List<InvoiceBean> getRows() {
		return totalRecords;
	}
	
	public void setTotalRecords(List<InvoiceBean> totalRecords) {
		this.totalRecords = totalRecords;
	}

	public InvoiceBean getModel() {
		updateUserDetailsFromSession(invoiceBean);
		return invoiceBean;
	}


	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}


	public int getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public Timestamp getDbAddTs() {
		return dbAddTs;
	}

	public void setDbAddTs(Timestamp dbAddTs) {
		this.dbAddTs = dbAddTs;
	}

	public int getDbAddUser() {
		return dbAddUser;
	}

	public void setDbAddUser(int dbAddUser) {
		this.dbAddUser = dbAddUser;
	}

	public Timestamp getDbUpdTs() {
		return dbUpdTs;
	}

	public void setDbUpdTs(Timestamp dbUpdTs) {
		this.dbUpdTs = dbUpdTs;
	}

	public int getDbUpdUser() {
		return dbUpdUser;
	}

	public void setDbUpdUser(int dbUpdUser) {
		this.dbUpdUser = dbUpdUser;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getMob1() {
		return mob1;
	}

	public void setMob1(String mob1) {
		this.mob1 = mob1;
	}

	public String getMob2() {
		return mob2;
	}

	public void setMob2(String mob2) {
		this.mob2 = mob2;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getInvoiceDetailsId() {
		return invoiceDetailsId;
	}

	public void setInvoiceDetailsId(int invoiceDetailsId) {
		this.invoiceDetailsId = invoiceDetailsId;
	}

	public int getMedicineDetailsId() {
		return medicineDetailsId;
	}

	public void setMedicineDetailsId(int medicineDetailsId) {
		this.medicineDetailsId = medicineDetailsId;
	}

	public int getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(int medicineId) {
		this.medicineId = medicineId;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getAvailableStock() {
		return availableStock;
	}

	public void setAvailableStock(int availableStock) {
		this.availableStock = availableStock;
	}

	public int getSoldoutStock() {
		return soldoutStock;
	}

	public void setSoldoutStock(int soldoutStock) {
		this.soldoutStock = soldoutStock;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getMfgDate() {
		return mfgDate;
	}

	public void setMfgDate(String mfgDate) {
		this.mfgDate = mfgDate;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	public Date getMfgDateActual() {
		return mfgDateActual;
	}

	public void setMfgDateActual(Date mfgDateActual) {
		this.mfgDateActual = mfgDateActual;
	}

	public Date getExpDateActual() {
		return expDateActual;
	}

	public void setExpDateActual(Date expDateActual) {
		this.expDateActual = expDateActual;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public String getOwnerAddr1() {
		return ownerAddr1;
	}

	public void setOwnerAddr1(String ownerAddr1) {
		this.ownerAddr1 = ownerAddr1;
	}

	public String getOwnerAddr2() {
		return ownerAddr2;
	}

	public void setOwnerAddr2(String ownerAddr2) {
		this.ownerAddr2 = ownerAddr2;
	}

	public String getOwnerDesc() {
		return ownerDesc;
	}

	public void setOwnerDesc(String ownerDesc) {
		this.ownerDesc = ownerDesc;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerAddr1() {
		return customerAddr1;
	}

	public void setCustomerAddr1(String customerAddr1) {
		this.customerAddr1 = customerAddr1;
	}

	public String getCustomerAddr2() {
		return customerAddr2;
	}

	public void setCustomerAddr2(String customerAddr2) {
		this.customerAddr2 = customerAddr2;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getSUCCESS_MSG() {
		return SUCCESS_MSG;
	}

	public String getFAILED_MSG() {
		return FAILED_MSG;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public double getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(double totalAmt) {
		this.totalAmt = totalAmt;
	}

	public double getTotalPaid() {
		return totalPaid;
	}

	public void setTotalPaid(double totalPaid) {
		this.totalPaid = totalPaid;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public List<InvoiceBean> getFooter() {
		return footer;
	}

	public void setFooter(List<InvoiceBean> footer) {
		this.footer = footer;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

	public Date getBillDateActual() {
		return billDateActual;
	}

	public void setBillDateActual(Date billDateActual) {
		this.billDateActual = billDateActual;
	}
}
