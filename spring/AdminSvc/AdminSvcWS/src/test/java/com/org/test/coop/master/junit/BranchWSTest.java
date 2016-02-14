package com.org.test.coop.master.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.coop.admin.service.BranchSetupServiceImpl;
import com.org.coop.bs.config.DozerConfig;
import com.org.coop.canonical.beans.UIModel;
import com.org.coop.society.data.admin.entities.BranchMaster;
import com.org.coop.society.data.admin.repositories.BranchMasterRepository;
import com.org.coop.society.data.transaction.config.DataAppConfig;
import com.org.test.coop.junit.JunitTestUtil;
import com.org.test.coop.junit.TestUtil;
import com.org.test.coop.society.data.transaction.config.TestDataAppConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan(basePackages = "com.org.test.coop"/*, excludeFilters = { @Filter(type = FilterType.ANNOTATION, value = DataAppConfig.class) }*/)
@EnableAutoConfiguration(exclude = { DataAppConfig.class})
@ContextHierarchy({
	  @ContextConfiguration(classes={TestDataAppConfig.class, DozerConfig.class})
})
@WebAppConfiguration
public class BranchWSTest {
	private static final Logger logger = Logger.getLogger(BranchWSTest.class);
	
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext wac;
	
	private UIModel createBranchBean;
	private UIModel createAnotherBranchBean;
	private UIModel updateBranchBean;
	
	private String createBranchJson = null;
	private String createAnotherBranchJson = null;
	private String updateBranchJson = null;
	
	private ObjectMapper om = null;
	
	@Autowired
	private BranchSetupServiceImpl branchSetupServiceImpl;
	
	@Autowired
	private BranchMasterRepository branchMasterRepository;
	
	@Before
	public void runBefore() {
		try {
			this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			om = new ObjectMapper();
			om.setSerializationInclusion(Include.NON_NULL);
			om.setDateFormat(df);
			createBranchJson = JunitTestUtil.getFileContent("inputJson/master/branch/addBranchAndAddress.json");
			createBranchBean = om.readValue(createBranchJson, UIModel.class);
			
			createAnotherBranchJson = JunitTestUtil.getFileContent("inputJson/master/branch/addAnotherBranchAndAddress.json");
			createAnotherBranchBean = om.readValue(createAnotherBranchJson, UIModel.class);
			
			updateBranchJson = JunitTestUtil.getFileContent("inputJson/master/branch/updateBranchAndAddress.json");
			updateBranchBean = om.readValue(updateBranchJson, UIModel.class);
		} catch (Exception e) {
			logger.error("Error while initializing: ", e);
		}
	}
	@Test
	public void testCountryStateDist() {
		try {
			this.mockMvc.perform(get("/rest/getBranch?branchId=2")
				 .contentType("application/json").header("Authorization", "Basic " + Base64.getEncoder().encodeToString("ashish:ashish".getBytes()))
				).andExpect(status().isOk());
		} catch(Exception e) {
			logger.error("Error while retriving branch", e);
		}
//		addNewBranchAndAddress();
//		addDuplicateBranch();
//		addAnotherNewBranchAndAddress();
//		updateBranchAndAddress();
	}

	private void addNewBranchAndAddress() {
		try {
			UIModel uiModel = branchSetupServiceImpl.addOrUpdateBranch(createBranchBean);
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			om = new ObjectMapper();
			om.setDateFormat(df);
			
			String srcJson = om.writeValueAsString(uiModel);
			uiModel = branchSetupServiceImpl.getBranch(uiModel.getBranchBean().getBranchId());
			String destJson = om.writeValueAsString(uiModel);
			assertEquals(srcJson, destJson);
			
		} catch (Exception e) {
			logger.error("Error while adding Branch: ", e);
			Assert.fail("Error while adding Branch:");
		}
	}
	
	private void addDuplicateBranch() {
		try {
			createBranchBean.getBranchBean().setBranchId(0);
			UIModel uiModel = branchSetupServiceImpl.addOrUpdateBranch(createBranchBean);
			assertNotNull(uiModel.getErrorMsg());
			
		} catch (Exception e) {
			logger.error("Error while adding duplicate branch: ", e);
			Assert.fail("Error while adding duplicate branch:");
		}
	}
	
	private void addAnotherNewBranchAndAddress() {
		try {
			UIModel uiModel = branchSetupServiceImpl.addOrUpdateBranch(createAnotherBranchBean);
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			om = new ObjectMapper();
			om.setDateFormat(df);
			
			String srcJson = om.writeValueAsString(uiModel);
			uiModel = branchSetupServiceImpl.getBranch(uiModel.getBranchBean().getBranchId());
			String destJson = om.writeValueAsString(uiModel);
			assertEquals(srcJson, destJson);
			
		} catch (Exception e) {
			logger.error("Error while adding Branch: ", e);
			Assert.fail("Error while adding Branch:");
		}
	}
	
	private void updateBranchAndAddress() {
		try {
			UIModel uiModel = branchSetupServiceImpl.addOrUpdateBranch(updateBranchBean);
			BranchMaster branch = branchMasterRepository.findByBranchId(1);
			assertEquals(branch.getPhone1(), "9830525559");
			assertEquals(branch.getUpdateUser(), "ashish");
			assertEquals(branch.getBranchAddresses().get(0).getAddress().getAddressLine1(), "Kalipur Check Post");
			assertEquals(branch.getBranchAddresses().get(0).getAddress().getUpdateUser(), "ashish");
			
		} catch (Exception e) {
			logger.error("Error while updating Branch: ", e);
			Assert.fail("Error while updating Branch:");
		}
	}
	
}
