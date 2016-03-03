package com.org.test.coop.master.junit;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;

import org.apache.log4j.Logger;
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
import com.org.coop.canonical.beans.UIModel;
import com.org.coop.society.data.customer.transaction.config.RetailDataAppConfig;
import com.org.coop.society.data.transaction.config.DataAppConfig;
import com.org.test.coop.junit.JunitTestUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan(basePackages = "com.org.test.coop"/*, excludeFilters = { @Filter(type = FilterType.ANNOTATION, value = DataAppConfig.class) }*/)
@EnableAutoConfiguration(exclude = { DataAppConfig.class})
@ContextHierarchy({
	  @ContextConfiguration(classes={RetailDataAppConfig.class})
})
@WebAppConfiguration
public class RetailMasterWSTest {
	private static final Logger logger = Logger.getLogger(RetailMasterWSTest.class);
	
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext wac;
	
	private String createBranchJson = null;
	
	private ObjectMapper om = null;
	
	
	@Before
	public void runBefore() {
		try {
			this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			om = new ObjectMapper();
			om.setSerializationInclusion(Include.NON_NULL);
			om.setDateFormat(df);
			createBranchJson = JunitTestUtil.getFileContent("inputJson/master/branch/addBranch.json");
			
		} catch (Exception e) {
			logger.error("Error while initializing: ", e);
		}
	}
	@Test
	public void testBranch() {
		addNewBranch();
	}

	private void addNewBranch() {
		try {
			MvcResult result = this.mockMvc.perform(get("/rest/masterData?branchId=1")
					 .contentType("application/json").header("Authorization", "Basic " + Base64.getEncoder().encodeToString("ashish:ashish".getBytes()))
					).andExpect(status().isOk())
					.andExpect(content().contentType("application/json"))
					.andReturn();
				
			UIModel uiModel = getUIModel(result);
			if(uiModel.getErrorMsg() != null) {
				return;
			}
			
//			MvcResult result = this.mockMvc.perform(post("/rest/createBranch")
//				 .contentType("application/json").header("Authorization", "Basic " + Base64.getEncoder().encodeToString("ashish:ashish".getBytes()))
//				 .content(createBranchJson)
//				).andExpect(status().isOk())
//				.andExpect(content().contentType("application/json"))
//				.andReturn();
//			
//			UIModel uiModel = getUIModel(result, "outputJson/master/branch/addBranch.json");
//			assertNull(uiModel.getErrorMsg());
//			assertEquals(uiModel.getBranchBean().getBranchId(), 1);
//			assertEquals("Kalipur",uiModel.getBranchBean().getBankName());
//			assertEquals("Kalipur",uiModel.getBranchBean().getBranchName());
//			assertEquals("9830625559",uiModel.getBranchBean().getPhone1());
//			assertEquals("12345743",uiModel.getBranchBean().getIfscCode());
//			assertEquals("87656321",uiModel.getBranchBean().getMicrCode());
//			assertEquals("ashismo@gmail.com",uiModel.getBranchBean().getEmail1());
//			assertEquals("coop_kalipur",uiModel.getBranchBean().getDbName());
//			assertEquals("CustomerSvcWS",uiModel.getBranchBean().getContextRoot());
		} catch(Exception e) {
			logger.error("Error while creating branch", e);
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
