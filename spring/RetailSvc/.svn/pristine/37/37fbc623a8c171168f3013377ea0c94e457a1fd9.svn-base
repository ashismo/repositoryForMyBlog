package com.org.test.coop.master.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.coop.bs.config.DozerConfig;
import com.org.coop.bs.util.AdminSvcCommonUtil;
import com.org.coop.canonical.beans.RetailVendorBean;
import com.org.coop.canonical.beans.UIModel;
import com.org.coop.retail.bs.config.RetailDozerConfig;
import com.org.coop.retail.entities.VendorMaster;
import com.org.coop.retail.servicehelper.RetailBranchSetupServiceHelperImpl;
import com.org.coop.society.data.admin.repositories.BranchMasterRepository;
import com.org.coop.society.data.transaction.config.DataAppConfig;
import com.org.test.coop.junit.JunitTestUtil;
import com.org.test.coop.society.data.transaction.config.TestDataAppConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan(basePackages = "com.org.test.coop")
@EnableAutoConfiguration(exclude = { DataAppConfig.class, DozerConfig.class})
@ContextHierarchy({
	  @ContextConfiguration(classes={TestDataAppConfig.class, RetailDozerConfig.class})
})
@WebAppConfiguration
public class RetailCustomerDeleteWSTest {
	private static final Logger logger = Logger.getLogger(RetailCustomerDeleteWSTest.class);
	
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext wac;
	
	private String deleteUsedCustomerJson = null;
	private String deleteNonUsedCustomerJson = null;
	
	private ObjectMapper om = null;
	
	@Before
	public void runBefore() throws Exception {
		try {
			this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			om = new ObjectMapper();
			om.setSerializationInclusion(Include.NON_NULL);
			om.setDateFormat(df);
			deleteUsedCustomerJson = JunitTestUtil.getFileContent("inputJson/retail/branch/deleteUsedRetailCustomer.json");
			deleteNonUsedCustomerJson = JunitTestUtil.getFileContent("inputJson/retail/branch/deleteNonUsedRetailCustomer.json");
		} catch (Exception e) {
			logger.error("Error while initializing: ", e);
			Assert.fail("Error while initializing");
		}
	}
	@Test
	public void testRetailCustomer() {
		deleteUsedCustomer();
		deleteNonUsedCustomer();
	}

	private void deleteUsedCustomer() {
		try {
			assertNotNull(deleteUsedCustomerJson);
			MvcResult result = this.mockMvc.perform(delete("/rest/deleteRetailCustomer")
				 .contentType("application/json").header("Authorization", "Basic " + Base64.getEncoder().encodeToString("ashish:ashish".getBytes()))
				 .content(deleteUsedCustomerJson)
				).andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andReturn();
			
			UIModel uiModel = getUIModel(result, "outputJson/retail/branch/deleteUsedRetailCustomer.json");
			
			assertNotNull(uiModel.getErrorMsg());
		} catch(Exception e) {
			logger.error("Error while deleting used Customer", e);
			Assert.fail("Error while deleting used Customer");
		}
	}
	
	
	private void deleteNonUsedCustomer() {
		try {
			assertNotNull(deleteNonUsedCustomerJson);
			MvcResult result = this.mockMvc.perform(delete("/rest/deleteRetailCustomer")
				 .contentType("application/json").header("Authorization", "Basic " + Base64.getEncoder().encodeToString("ashish:ashish".getBytes()))
				 .content(deleteNonUsedCustomerJson)
				).andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andReturn();
			
			UIModel uiModel = getUIModel(result, "outputJson/retail/branch/deleteNonUsedRetailCustomer.json");
			
			assertNotNull(uiModel.getErrorMsg());
		} catch(Exception e) {
			logger.error("Error while deleting non-used Customer", e);
			Assert.fail("Error while deleting non-used Customer");
		}
	}
	
	private UIModel getUIModel(MvcResult result)
			throws UnsupportedEncodingException, IOException,
			JsonParseException, JsonMappingException {
		String json = result.getResponse().getContentAsString();
		UIModel createBranchBean = om.readValue(json, UIModel.class);
		return createBranchBean;
	}
	
	private UIModel getUIModel(MvcResult result, String path)
			throws UnsupportedEncodingException, IOException,
			JsonParseException, JsonMappingException {
		UIModel createBranchBean = getUIModel(result);
		JunitTestUtil.writeJSONToFile(createBranchBean, path);
		return createBranchBean;
	}
	
}
