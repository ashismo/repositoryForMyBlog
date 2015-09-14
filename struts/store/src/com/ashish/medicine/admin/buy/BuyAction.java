package com.ashish.medicine.admin.buy;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.SqlTimestampConverter;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

public class BuyAction implements ModelDriven<BuyBean> {
	private BuyBean buyBean = new BuyBean();
	private static final long serialVersionUID = -2613425890762568273L;
	private int total;
	private List<BuyBean> rows; 
	private String msg;

	private int buyId;
	private String buyAddr1;
	private String buyAddr2;
	private String buyDesc;
	private String buyName;
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
	
	public BuyAction() {
		ConvertUtils.register(new SqlTimestampConverter(null), java.sql.Timestamp.class);
		ConvertUtils.register(new DateConverter(null), java.util.Date.class);
	}
	
	public String buyManagementDetails()
	{
		return "success";		
	}
	
	public String searchBuy()
	{
		List<BuyBean> searchList = buyBean.getSearchBuyList();
		BuyBean cBean = new BuyBean();
		cBean.setBuyName("Test1");
		cBean.setBuyAddr1("Kolkata");
		cBean.setPhone1("9830525559");
		for(int i = buyBean.getPage() * buyBean.getRows(); i < (buyBean.getPage() + 1) * buyBean.getRows(); i++) {
			cBean.setBuyId(i);
			searchList.add(cBean);
		}
		rows = searchList;
		total = 100;
		return Action.SUCCESS;		
	}
	
	public String viewBuy()
	{
		setBuyAddr1("Kolkata");
		setPhone1("9830525559");
		setBuyName("Test2");
		setBuyId(100);
		return "success";		
	}
	
	public String createBuy()
	{
		System.out.println("Create Buy");
		try {
			BeanUtils.copyProperties(buyBean, this);
			new BuyServiceImpl().addBuy(buyBean);
			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		setMsg("Successfully updated");
		return Action.SUCCESS;		
	}
	
	public String sellByMonths()
	{
		return "success";		
	}
	
	public BuyBean getBuyBean() {
		return buyBean;
	}

	public void setBuyBean(BuyBean buyBean) {
		this.buyBean = buyBean;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<BuyBean> getRows() {
		return rows;
	}

	public void setRows(List<BuyBean> rows) {
		this.rows = rows;
	}

	public BuyBean getModel() {
		return buyBean;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getBuyId() {
		return buyId;
	}

	public void setBuyId(int buyId) {
		this.buyId = buyId;
	}

	public String getBuyAddr1() {
		return buyAddr1;
	}

	public void setBuyAddr1(String buyAddr1) {
		this.buyAddr1 = buyAddr1;
	}

	public String getBuyAddr2() {
		return buyAddr2;
	}

	public void setBuyAddr2(String buyAddr2) {
		this.buyAddr2 = buyAddr2;
	}

	public String getBuyDesc() {
		return buyDesc;
	}

	public void setBuyDesc(String buyDesc) {
		this.buyDesc = buyDesc;
	}

	public String getBuyName() {
		return buyName;
	}

	public void setBuyName(String buyName) {
		this.buyName = buyName;
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

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
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

	
}
