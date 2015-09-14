package com.ashish.test1.test;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringBufferInputStream;
import java.util.List;
import java.util.Scanner;

import javax.servlet.ServletContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.struts2.ServletActionContext;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.ashish.medicine.util.PDFCreator;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class LinkAction extends ActionSupport {
//		implements ServletContextAware, SessionAware, ServletRequestAware, ServletResponseAware {

	private POCBean pocBean = new POCBean();
	private static final long serialVersionUID = -2613425890762568273L;
	private int total;
	private List<POCBean> rows; 
	private String companyName;
	private String phone1;
	private String companyAddr1;
	private long companyId;
	private String msg;
	private InputStream inputStream;
	
	public String generatePDF()
	{
		String header[] = {"Column1^10", "Column2^10", "Column3^14"};
		String pdfContent[][] = {{"val11", "val12", "val13"}, 
						{"val21", "val22", "val23"}, 
						{"val31", "val32", "val33"}
					};
		String title = "POC PDF";
		try {
			inputStream = new PDFCreator().createPDF(title, header, pdfContent);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;		
	}
	
	public String generateInvoice()
	{
		
		try {
			ServletContext context = ServletActionContext.getServletContext();
			
	        String htmlFilePath = context.getRealPath("") + "\\resources\\jsp\\poc\\InvoiceTemplate.html";
	        String pdfFilePath = context.getRealPath(".") + "\\Invoice.pdf";
	        FileInputStream fstream = new FileInputStream(htmlFilePath);
	        // Get the object of DataInputStream
	        DataInputStream in = new DataInputStream(fstream);
	        BufferedReader br = new BufferedReader(new InputStreamReader(in));

			String fileContent = "";
			String line = "";
			while ((line = br.readLine()) != null)   {
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
	            
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        } finally {
	        	os.close();
	        }
	        inputStream = new FileInputStream(pdfFilePath);

		}catch(Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;		
	}

	public String companyManagement()
	{
		return "success";		
	}
	
	public String listCompany()
	{
		List<POCBean> searchList = pocBean.getSearchCompanyList();
		POCBean cBean = new POCBean();
		cBean.setCompanyName("Test1");
		cBean.setCompanyAddr1("Kolkata");
		cBean.setPhone1("9830525559");
		for(int i = pocBean.getPage() * pocBean.getRows(); i < (pocBean.getPage() + 1) * pocBean.getRows(); i++) {
			cBean.setCompanyId(i);
			searchList.add(cBean);
		}
		rows = searchList;
		total = 100;
		return Action.SUCCESS;		
	}
	
	public String viewCompany()
	{
		setCompanyAddr1("Kolkata");
		setPhone1("9830525559");
		setCompanyName("Test2");
		setCompanyId(100);
		return "success";		
	}
	
	public String createCompany()
	{
		setMsg("Successfully updated");
		return Action.SUCCESS;		
	}
	
	public String sellByMonths()
	{
		return "success";		
	}
	

	public POCBean getPocBean() {
		return pocBean;
	}

	public void setPocBean(POCBean pocBean) {
		this.pocBean = pocBean;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<POCBean> getRows() {
		return rows;
	}

	public void setRows(List<POCBean> rows) {
		this.rows = rows;
	}

	public POCBean getModel() {
		return pocBean;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getCompanyAddr1() {
		return companyAddr1;
	}

	public void setCompanyAddr1(String companyAddr1) {
		this.companyAddr1 = companyAddr1;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String welcome()
	{
		return "welcome";		
	}
	
	public String friends()
	{
		return "friends";		
	}
	
	public String office()
	{
		return "office";		
	}
	
	public String getPOCPage()
	{
		return "success";		
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
}
