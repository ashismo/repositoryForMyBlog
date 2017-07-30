package com.test;

import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ashish.poc.model.UserDataModel;
import com.test.util.JunitTestUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan(basePackages = "com.test"/*, excludeFilters = { @Filter(type = FilterType.ANNOTATION, value = DataAppConfig.class) }*/)
//@EnableAutoConfiguration(exclude = { DataAppConfig.class})
@ContextHierarchy({
//	  @ContextConfiguration(classes={H2DataSource.class, Initializer.class})
	  @ContextConfiguration(locations = {"classpath:application-context.xml"})
//	  @ContextConfiguration(loader=AnnotationConfigWebContextLoader.class)
})
//@ContextConfiguration(locations = {"classpath:application-context.xml"}, loader=AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public class UserManagementTest {
	
	
//	@Configuration
//	@EnableWebMvc
//	@ComponentScan(basePackages = {"..."})
//	static class ContextConfiguration {
//		
//	}

	@Autowired
	private WebApplicationContext wac;
	
	private ObjectMapper om = null;
	private MockMvc mockMvc;
	
	
	private String createUserJson = null;
	
//	@InjectMocks
//    private UserGuideWSImpl userController;
	
	@Before
	public void runBefore() {
		try {
			
//			MockitoAnnotations.initMocks(this);
//	        mockMvc = MockMvcBuilders
//	                .standaloneSetup(userController)
//	                //.addFilters(new CORSFilter())
//	                .build();
	        
			this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

			DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			om = new ObjectMapper();
			om.setSerializationInclusion(Inclusion.NON_NULL);
			om.setDateFormat(df);
			createUserJson = JunitTestUtil.getFileContent("inputJson/usermgmt/addUser.json");
			
			UserDataModel udm = om.readValue(createUserJson, UserDataModel.class);
			System.out.println(udm);
		} catch (Exception e) {
			System.out.println("Error while initializing: ");
			e.printStackTrace();
		}
	}
	//@Test
	public void createUser() {
		try {
			MvcResult result = this.mockMvc.perform(post("/rest/svc/guide/createUser")
				 .contentType("application/json")
				 .content(createUserJson)
				).andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andReturn();
			
			UserDataModel uiModel = getUIModel(result, "outputJson/usermgmt/addUser.json");
			assertNull(uiModel.getErrorMsg());
		} catch(Exception e) {
			System.out.println("Error while creating branch");
			e.printStackTrace();
		}
	}
	
	@Test
	public void getUser() {
		try {
			MvcResult result = this.mockMvc.perform(get("/rest/svc/guide/getHtmlData")
//				 .accept("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
					.accept(MediaType.APPLICATION_JSON)
//					.contentType("application/json")
//				 .content(createUserJson)
				).andExpect(status().isOk())
//				.andExpect(content().contentType("application/json"))
				.andReturn();
			
//			MvcResult result = this.mockMvc.perform(get("/admin.html")
//					 //.contentType("application/json")
//					 //.content(createUserJson)
//					).andExpect(status().isOk())
//					//.andExpect(content().contentType("application/json"))
//					.andReturn();
			
			//UserDataModel uiModel = getUIModel(result, "outputJson/usermgmt/addUser.json");
			//assertNull(uiModel.getErrorMsg());
		} catch(Exception e) {
			System.out.println("Error while creating branch");
			e.printStackTrace();
		}
	}
	
	
	private UserDataModel getUIModel(MvcResult result)
			throws UnsupportedEncodingException, IOException,
			JsonParseException, JsonMappingException {
		String json = result.getResponse().getContentAsString();
		UserDataModel createBranchBean = om.readValue(json, UserDataModel.class);
		return createBranchBean;
	}
	
	private UserDataModel getUIModel(MvcResult result, String path)
			throws UnsupportedEncodingException, IOException,
			JsonParseException, JsonMappingException {
		UserDataModel createBranchBean = getUIModel(result);
		JunitTestUtil.writeJSONToFile(createBranchBean, path);
		return createBranchBean;
	}
}
