package com.ashish.test.junit;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ashish.canonical.SearchBean;
import com.ashish.config.WebConfig;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan(basePackages = "com.ashish.test")
@ContextHierarchy({
	  @ContextConfiguration(classes={WebConfig.class})
})
@WebAppConfiguration
public class WebserviceTest1 {
	
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext wac;
	
	private ObjectMapper om;
	
	@Before
	public void runBefore() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		om = new ObjectMapper();
	}

	@Test
	public void getPNR() {
		try {
			MvcResult result = this.mockMvc.perform(get("/rest/getPNR")).andExpect(status().isOk())
					.andExpect(content().contentType("application/json"))
					.andReturn();
			System.out.println(result.getResponse().getContentAsString());
			SearchBean sb = om.readValue(result.getResponse().getContentAsString(),  SearchBean.class);
			assertEquals("PNR", sb.getPnr());
		} catch(Exception e) {
			System.out.println("Error while retrieving PNR");
			e.printStackTrace();
		}
	}
	
	@Test
	public void getDummy() {
		System.out.println("Ashish");
	}
	
	@Test
	public void testDefaultException() {
		try {
			MvcResult result = this.mockMvc.perform(get("/rest/getDefaultException")).andExpect(status().is4xxClientError())
					.andExpect(content().contentType("application/json"))
					.andReturn();
			System.out.println(result.getResponse().getContentAsString());
			Map<String, String> error = om.readValue(result.getResponse().getContentAsString(), Map.class);
			assertEquals("417", error.get("code"));
			assertEquals("java.lang.NullPointerException", error.get("reason"));
		} catch(Exception e) {
			System.out.println("Error while Testing default exception");
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCustomException() {
		try {
			MvcResult result = this.mockMvc.perform(get("/rest/getCustomException")).andExpect(status().is4xxClientError())
					.andExpect(content().contentType("application/json"))
					.andReturn();
			System.out.println(result.getResponse().getContentAsString());
			Map<String, String> error = om.readValue(result.getResponse().getContentAsString(), Map.class);
			assertEquals("401", error.get("code"));
			assertEquals("Custom Exception", error.get("reason"));
			
		} catch(Exception e) {
			System.out.println("Error while Testing custom exception");
			e.printStackTrace();
		}
	}
	
}
