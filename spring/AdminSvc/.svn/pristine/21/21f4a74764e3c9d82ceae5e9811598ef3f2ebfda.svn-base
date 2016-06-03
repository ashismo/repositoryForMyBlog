package com.org.test.coop.master.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import com.org.coop.admin.service.MasterDataSetupServiceImpl;
import com.org.coop.bs.config.DozerConfig;
import com.org.coop.canonical.master.beans.MasterDataBean;
import com.org.coop.society.data.admin.repositories.SecurityQuestionRepository;
import com.org.coop.society.data.transaction.config.DataAppConfig;
import com.org.test.coop.junit.JunitTestUtil;
import com.org.test.coop.society.data.transaction.config.TestDataAppConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan(basePackages = "com.org.test.coop"/*, excludeFilters = { @Filter(type = FilterType.ANNOTATION, value = DataAppConfig.class) }*/)
@EnableAutoConfiguration(exclude = { DataAppConfig.class})
@ContextHierarchy({
	  @ContextConfiguration(classes={TestDataAppConfig.class, DozerConfig.class})
})
@WebAppConfiguration
public class DropdownMasterServiceTest {
	private static final Logger logger = Logger.getLogger(DropdownMasterServiceTest.class);
	
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext wac;
	
	private String addNewDropdown = null;
	private String deleteDropdownForABranch = null;
	
	private ObjectMapper om = null;
	
	@Autowired
	private MasterDataSetupServiceImpl masterDataSetupServiceImpl;
	
	@Autowired
	private SecurityQuestionRepository securityQuestionRepository;
	
	@Before
	public void runBefore() {
		try {
			this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			om = new ObjectMapper();
			om.setSerializationInclusion(Include.NON_NULL);
			om.setDateFormat(df);
			addNewDropdown = JunitTestUtil.getFileContent("inputJson/master/dropdown/addNewDropdown.json");
			deleteDropdownForABranch = JunitTestUtil.getFileContent("inputJson/master/dropdown/deleteDropdownForABranch.json");
		} catch (Exception e) {
			logger.error("Error while initializing: ", e);
		}
	}
	@Test
	public void testSecurityQuestions() {
		addNewDropdown();
		deleteDropdownForABranch();
	}

	private void addNewDropdown() {
		try {
			MvcResult result = this.mockMvc.perform(post("/rest/saveDropdown")
				 .contentType("application/json").header("Authorization", "Basic " + Base64.getEncoder().encodeToString("ashish:ashish".getBytes()))
				 .content(addNewDropdown)
				).andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andReturn();
			
			MasterDataBean uiModel = getUIModel(result, "outputJson/master/dropdown/addNewDropdown.json");
			assertNull(uiModel.getErrorMsg());
		} catch(Exception e) {
			logger.error("Error while creating branch", e);
		}
	}
	
	public void deleteDropdownForABranch() {
		try {
			MvcResult result = this.mockMvc.perform(post("/rest/saveDropdown")
				 .contentType("application/json").header("Authorization", "Basic " + Base64.getEncoder().encodeToString("ashish:ashish".getBytes()))
				 .content(deleteDropdownForABranch)
				).andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andReturn();
			
			MasterDataBean uiModel = getUIModel(result, "outputJson/master/dropdown/deleteDropdownForABranch.json");
			assertNull(uiModel.getErrorMsg());
		} catch(Exception e) {
			logger.error("Error while creating branch", e);
		}
	}
	
	private MasterDataBean getUIModel(MvcResult result)
			throws UnsupportedEncodingException, IOException,
			JsonParseException, JsonMappingException {
		String json = result.getResponse().getContentAsString();
		MasterDataBean masterDataBean = om.readValue(json, MasterDataBean.class);
		return masterDataBean;
	}
	
	private MasterDataBean getUIModel(MvcResult result, String path)
			throws UnsupportedEncodingException, IOException,
			JsonParseException, JsonMappingException {
		MasterDataBean masterDataBean = getUIModel(result);
		JunitTestUtil.writeJSONToFile(masterDataBean, path);
		return masterDataBean;
	}
	
}
