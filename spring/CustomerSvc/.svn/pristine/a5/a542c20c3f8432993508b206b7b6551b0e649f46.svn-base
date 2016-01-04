package com.org.coop.junit;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.org.coop.admin.service.AdminLoginService;
import com.org.coop.admin.service.BranchSetupServiceImpl;
import com.org.coop.admin.service.CommonAdminServiceImpl;
import com.org.coop.bs.config.BusinessServiceConfig;
import com.org.coop.bs.config.DozerConfig;
import com.org.coop.canonical.beans.AddressBean;
import com.org.coop.canonical.beans.BranchBean;
import com.org.coop.canonical.beans.UIModel;
import com.org.coop.security.service.EmailService;
import com.org.coop.society.data.admin.entities.BranchAddress;
import com.org.coop.society.data.admin.entities.BranchMaster;
import com.org.coop.society.data.admin.repositories.BranchAddressRepository;
import com.org.coop.society.data.admin.repositories.BranchMasterRepository;
import com.org.coop.society.data.admin.repositories.SecurityQuestionRepository;
import com.org.coop.society.data.admin.repositories.UserAdminRepository;
import com.org.coop.society.data.transaction.config.DataAppConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={DataAppConfig.class, BusinessServiceConfig.class, DozerConfig.class})
public class CooperativeServiceTest {
	private static final Logger logger = Logger.getLogger(CooperativeServiceTest.class);
	
	@Autowired
	private AdminLoginService adminLoginService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private UserAdminRepository userRepository;
	
	@Autowired
	private SecurityQuestionRepository securityQuestionRepository;
	
	@Autowired
	private BranchSetupServiceImpl branchSetupServiceImpl;
	
	@Autowired
	private BranchAddressRepository branchAddressRepository;
	
	@Autowired
	private BranchMasterRepository branchMasterRepository;
	
	@Autowired
	private CommonAdminServiceImpl commonAdminServiceImpl;
	
//	@Test
	public void createNewBranchWithNewAddress() {
		UIModel ui = new UIModel();
		String date = "18-12-2015";
		BranchBean bb = new BranchBean();
		ui.setBranchBean(bb);
		bb.setBranchId(25);
		bb.setParentId(0);
		bb.setBankName("Test Bank");
		bb.setBranchName("Branch1");
		bb.setEmail1("ashismo@gmail.com");
		bb.setIfscCode("12345678");
		bb.setMicrCode("87654321");
		bb.setPhone1("9830625559");
		bb.setStartDate(commonAdminServiceImpl.getDateByString(date));
		bb.setCreateUser("ashish");
		
		List<AddressBean> addressBeanList = new ArrayList<AddressBean>();
		bb.setAddresses(addressBeanList);
		AddressBean address = new AddressBean();
//		addressBeanList.add(address);
//		address.setAddressId(26);
//		address.setAddressName("Test Address1");
//		address.setAddressLine1("Kalipur1");
//		address.setPin("712708");
//		address.setPhoneNo1("9830525559");
//		address.setDistId(1);
//		address.setStartDate(new Date());
//		address.setCreateUser("ashish");
		
		address = new AddressBean();
		addressBeanList.add(address);
		address.setAddressId(30);
		address.setAddressName("Test Address3");
		address.setAddressLine1("Naldighi17");
		address.setPin("712304");
		address.setDistId(1);
		address.setPhoneNo1("9830525559");
		address.setStartDate(commonAdminServiceImpl.getDateByString(date));
		address.setCreateUser("ashish");
		
		branchSetupServiceImpl.addOrUpdateBranch(ui);
		
		
		System.out.println("Branch master is saved");
	}
	
//	@Test
	public void fetchStaff1() {
		List<String> role = adminLoginService.getRole("ashish");
		System.out.println(role);
		logger.info("Role: " + role);
	}
	
//	@Test
	@Transactional("adminTransactionManager")
	public void checkWhenCond() {
		List<BranchAddress> baList = branchAddressRepository.findByBranchId(25);
		BranchMaster branch = branchMasterRepository.findByMicrCode("87654321");
		System.out.println(branch.getBranchAddresses());
		System.out.println(baList);
	}
	
//	@Test
	public void emailTester() {
		emailService.sendEmail("asmo00b1@gmail.com", "coop@gmail.com", "Test mail from java app", "This is a test mail");
	}
	
	@Test
	public void getBranch() {
		branchSetupServiceImpl.getBranch(25);
	}
}
