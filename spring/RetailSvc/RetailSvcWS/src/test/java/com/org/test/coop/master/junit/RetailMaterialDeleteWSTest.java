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
import com.org.coop.canonical.beans.UIModel;
import com.org.coop.retail.bs.config.RetailDozerConfig;
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
public class RetailMaterialDeleteWSTest {
	private static final Logger logger = Logger.getLogger(RetailMaterialDeleteWSTest.class);
	
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext wac;
	
	private String deleteUsedMaterialJson = null;
	private String deleteNonUsedMaterialJson = null;
	private String deleteUsedMaterialGroupJson = null;
	private String deleteNonUsedMaterialGroupJson = null;
	
	private ObjectMapper om = null;
	
	@Autowired
	private RetailBranchSetupServiceHelperImpl branchSetupServiceImpl;
	
	@Autowired
	private BranchMasterRepository branchMasterRepository;
	
	@Autowired
	private AdminSvcCommonUtil adminSvcCommonUtil;
	
	@Before
	public void runBefore() throws Exception {
		try {
			this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			om = new ObjectMapper();
			om.setSerializationInclusion(Include.NON_NULL);
			om.setDateFormat(df);
			deleteUsedMaterialJson = JunitTestUtil.getFileContent("inputJson/retail/branch/deleteUsedMaterial.json");
			deleteNonUsedMaterialJson = JunitTestUtil.getFileContent("inputJson/retail/branch/deleteNonUsedMaterial.json");
			deleteUsedMaterialGroupJson = JunitTestUtil.getFileContent("inputJson/retail/branch/deleteUsedMaterialGroup.json");
			deleteNonUsedMaterialGroupJson = JunitTestUtil.getFileContent("inputJson/retail/branch/deleteNonUsedMaterialGroup.json");
		} catch (Exception e) {
			logger.error("Error while initializing: ", e);
			Assert.fail("Error while initializing");
		}
	}
	@Test
	public void testMaterials() {
		deleteUsedMaterialGroup();
		deleteNonUsedMaterialGroup();
		deleteUsedMaterial();
		deleteNonUsedMaterial();
	}

	private void deleteUsedMaterialGroup() {
		try {
			assertNotNull(deleteUsedMaterialGroupJson);
			MvcResult result = this.mockMvc.perform(delete("/rest/deleteMaterialGroup")
				 .contentType("application/json").header("Authorization", "Basic " + Base64.getEncoder().encodeToString("ashish:ashish".getBytes()))
				 .content(deleteUsedMaterialJson)
				).andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andReturn();
			
			UIModel uiModel = getUIModel(result, "outputJson/retail/branch/deleteUsedMaterialGroup.json");
			
			assertNotNull(uiModel.getErrorMsg());
		} catch(Exception e) {
			logger.error("Error while deleting material Group which is already used", e);
			Assert.fail("Error while deleting material Group which is already used");
		}
	}
	
	private void deleteNonUsedMaterialGroup() {
		try {
			assertNotNull(deleteNonUsedMaterialGroupJson);
			MvcResult result = this.mockMvc.perform(delete("/rest/deleteMaterialGroup")
				 .contentType("application/json").header("Authorization", "Basic " + Base64.getEncoder().encodeToString("ashish:ashish".getBytes()))
				 .content(deleteNonUsedMaterialGroupJson)
				).andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andReturn();
			
			UIModel uiModel = getUIModel(result, "outputJson/retail/branch/deleteNonUsedMaterialGroup.json");
			
			assertNull(uiModel.getErrorMsg());
		} catch(Exception e) {
			logger.error("Error while deleting material Group", e);
			Assert.fail("Error while deleting material Group");
		}
	}
	
	private void deleteUsedMaterial() {
		try {
			assertNotNull(deleteUsedMaterialJson);
			MvcResult result = this.mockMvc.perform(delete("/rest/deleteMaterial")
				 .contentType("application/json").header("Authorization", "Basic " + Base64.getEncoder().encodeToString("ashish:ashish".getBytes()))
				 .content(deleteUsedMaterialJson)
				).andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andReturn();
			
			UIModel uiModel = getUIModel(result, "outputJson/retail/branch/deleteUsedMaterial.json");
			
			assertNotNull(uiModel.getErrorMsg());
		} catch(Exception e) {
			logger.error("Error while deleting material which is already used", e);
			Assert.fail("Error while deleting material which is already used");
		}
	}
	
	private void deleteNonUsedMaterial() {
		try {
			assertNotNull(deleteNonUsedMaterialJson);
			MvcResult result = this.mockMvc.perform(delete("/rest/deleteMaterial")
				 .contentType("application/json").header("Authorization", "Basic " + Base64.getEncoder().encodeToString("ashish:ashish".getBytes()))
				 .content(deleteNonUsedMaterialJson)
				).andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andReturn();
			
			UIModel uiModel = getUIModel(result, "outputJson/retail/branch/deleteNonUsedMaterial.json");
			
			assertNull(uiModel.getErrorMsg());
		} catch(Exception e) {
			logger.error("Error while deleting material", e);
			Assert.fail("Error while deleting material");
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
