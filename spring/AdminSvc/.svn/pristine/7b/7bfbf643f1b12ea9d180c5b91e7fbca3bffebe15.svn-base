package com.org.test.coop.master.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

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
import com.org.coop.admin.service.BranchSetupServiceImpl;
import com.org.coop.bs.config.DozerConfig;
import com.org.coop.bs.util.AdminSvcCommonUtil;
import com.org.coop.canonical.beans.AddressBean;
import com.org.coop.canonical.beans.BranchBean;
import com.org.coop.canonical.beans.BranchLicenseDtlsBean;
import com.org.coop.canonical.beans.BranchRuleBean;
import com.org.coop.canonical.beans.RoleMasterBean;
import com.org.coop.canonical.beans.RolePermissionBean;
import com.org.coop.canonical.beans.UIModel;
import com.org.coop.canonical.beans.UserMasterBean;
import com.org.coop.canonical.beans.UserRoleBean;
import com.org.coop.canonical.beans.UserSecurityBean;
import com.org.coop.society.data.admin.repositories.BranchMasterRepository;
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
public class UserBranchWSTest {
	private static final Logger logger = Logger.getLogger(UserBranchWSTest.class);
	
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext wac;
	
	
	private ObjectMapper om = null;
	
	@Autowired
	private BranchSetupServiceImpl branchSetupServiceImpl;
	
	@Autowired
	private BranchMasterRepository branchMasterRepository;
	
	@Autowired
	private AdminSvcCommonUtil adminSvcCommonUtil;
	
	@Before
	public void runBefore() {
		try {
			this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			om = new ObjectMapper();
			om.setSerializationInclusion(Include.NON_NULL);
			om.setDateFormat(df);
			
		} catch (Exception e) {
			logger.error("Error while initializing: ", e);
		}
	}
	@Test
	public void testBranch() {
		getUserBranch();
	}

	private void getUserBranch() {
		try {
			MvcResult result = this.mockMvc.perform(get("/rest/getUserBranch?username=ashish")
					 .contentType("application/json").header("Authorization", "Basic " + Base64.getEncoder().encodeToString("ashish:ashish".getBytes()))
					).andExpect(status().isOk())
					.andExpect(content().contentType("application/json"))
					.andReturn();
				
			UIModel uiModel = getUIModel(result, "outputJson/master/user/getUserBranch.json");
			assertNull(uiModel.getErrorMsg());
			assertEquals(uiModel.getBranchBean().getBranchId(), 1);
			UserMasterBean user = uiModel.getBranchBean().getUsers().get(0);
			assertEquals("ashish",user.getUserName());
			assertEquals("Ashish",user.getFirstName());
		} catch(Exception e) {
			logger.error("Error while retrieving branch by username ", e);
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
