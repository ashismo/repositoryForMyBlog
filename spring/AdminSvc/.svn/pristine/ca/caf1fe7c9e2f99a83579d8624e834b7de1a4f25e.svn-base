package com.org.test.coop.master.junit;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.coop.admin.service.MasterDataSetupServiceImpl;
import com.org.coop.bs.config.DozerConfig;
import com.org.coop.canonical.master.beans.MasterDataBean;
import com.org.coop.canonical.master.beans.SecurityQuestionsMasterBean;
import com.org.coop.society.data.admin.entities.SecurityQuestion;
import com.org.coop.society.data.admin.repositories.SecurityQuestionRepository;
import com.org.test.coop.junit.JunitTestUtil;
import com.org.test.coop.society.data.transaction.config.TestDataAppConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan(basePackages = "com.org.test.coop")
@ContextHierarchy({
	  @ContextConfiguration(classes={TestDataAppConfig.class, DozerConfig.class})
})
//@SpringApplicationConfiguration(classes={TestDataAppConfig.class, DozerConfig.class})
public class SecurityQuestionTest {
	private static final Logger logger = Logger.getLogger(SecurityQuestionTest.class);
	private MasterDataBean createSecurityQuesBean;
	private MasterDataBean modifySecurityQuesBean;
	private MasterDataBean deleteSecurityQuesBean;
	
	private String createSecurityQuesJson = null;
	private String modifySecurityQuesJson = null;
	private String deleteSecurityQuesJson = null;
	
	private ObjectMapper om = null;
	
	@Autowired
	private MasterDataSetupServiceImpl masterDataSetupServiceImpl;
	
	@Autowired
	private SecurityQuestionRepository securityQuestionRepository;
	
	@Before
	public void runBefore() {
		try {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			om = new ObjectMapper();
			om.setSerializationInclusion(Include.NON_NULL);
			om.setDateFormat(df);
			createSecurityQuesJson = JunitTestUtil.getFileContent("inputJson/master/security/addSecurityQuestion.json");
			createSecurityQuesBean = om.readValue(createSecurityQuesJson, MasterDataBean.class);
			
			modifySecurityQuesJson = JunitTestUtil.getFileContent("inputJson/master/security/modifySecurityQuestion.json");
			modifySecurityQuesBean = om.readValue(modifySecurityQuesJson, MasterDataBean.class);
			
			deleteSecurityQuesJson = JunitTestUtil.getFileContent("inputJson/master/security/deleteSecurityQuestion.json");
			deleteSecurityQuesBean = om.readValue(deleteSecurityQuesJson, MasterDataBean.class);
		} catch (Exception e) {
			logger.error("Error while initializing: ", e);
		}
	}
	@Test
	public void testSecurityQuestions() {
		addSecurityQuestions();
		getAllSecurityQuestions();
		getRandomSecurityQuestions();
		modifySecurityQuestions();
		deleteSecurityQuestions();
		getAllSecurityQuestionsAfter2QuestionsDeleted();
	}

	private void addSecurityQuestions() {
		try {
			MasterDataBean moduleBean = masterDataSetupServiceImpl.saveSecurityQuestions(createSecurityQuesBean);
			
			SecurityQuestionsMasterBean secQues = (SecurityQuestionsMasterBean)createSecurityQuesBean.getSecurityQuestions().toArray()[0];
			secQues.setQuestionId(0);
			secQues.setQuestion("What is your mother's maiden name");
			moduleBean = masterDataSetupServiceImpl.saveSecurityQuestions(createSecurityQuesBean);
			
			secQues.setQuestionId(0);
			secQues.setQuestion("What is your first Job");
			moduleBean = masterDataSetupServiceImpl.saveSecurityQuestions(createSecurityQuesBean);
			
			secQues.setQuestionId(0);
			secQues.setQuestion("Who is your best teacher");
			moduleBean = masterDataSetupServiceImpl.saveSecurityQuestions(createSecurityQuesBean);
			
			secQues.setQuestionId(0);
			secQues.setQuestion("What is your PAN number");
			moduleBean = masterDataSetupServiceImpl.saveSecurityQuestions(createSecurityQuesBean);
			
			assertEquals(moduleBean.getSecurityQuestions().size(), 5);
			
		} catch (Exception e) {
			logger.error("Error while adding security question: ", e);
			Assert.fail("Error while adding security question:");
		}
	}
	
	private void getAllSecurityQuestions() {
		MasterDataBean moduleBean = masterDataSetupServiceImpl.getSecurityQuestions(0);
		assertEquals(moduleBean.getSecurityQuestions().size(), 5);
	}
	
	private void getRandomSecurityQuestions() {
		MasterDataBean moduleBean = masterDataSetupServiceImpl.getSecurityQuestions(2);
		assertEquals(moduleBean.getSecurityQuestions().size(), 2);
		
		moduleBean = masterDataSetupServiceImpl.getSecurityQuestions(3);
		assertEquals(moduleBean.getSecurityQuestions().size(), 3);
	}
	
	private void modifySecurityQuestions() {
			MasterDataBean moduleBean = masterDataSetupServiceImpl.saveSecurityQuestions(modifySecurityQuesBean);
			Set<SecurityQuestionsMasterBean> secQues = moduleBean.getSecurityQuestions();
			for(SecurityQuestionsMasterBean ques : secQues) {
				if(ques.getQuestionId() == 1) {
					assertEquals(ques.getQuestion(), "What is your new passport number");
					SecurityQuestion secQuestion = securityQuestionRepository.findByQuestionId(ques.getQuestionId());
					assertEquals(secQuestion.getUpdateUser(), "ashish");
				}
			}
			
	}
	
	private void deleteSecurityQuestions() {
		MasterDataBean moduleBean = masterDataSetupServiceImpl.deleteSecurityQuestions(deleteSecurityQuesBean);
		Set<SecurityQuestionsMasterBean> secQues = moduleBean.getSecurityQuestions();
		for(SecurityQuestionsMasterBean ques : secQues) {
			if(ques.getQuestionId() == 1) {
				assertEquals(ques.getQuestion(), "What is your new passport number");
				SecurityQuestion secQuestion = securityQuestionRepository.findByQuestionId(ques.getQuestionId());
				assertEquals(secQuestion.getDeleteInd(), "Y");
				assertEquals(secQuestion.getUpdateUser(), "ashish");
			}else if(ques.getQuestionId() == 2) {
				assertEquals(ques.getQuestion(), "What is your mother's maiden name");
				SecurityQuestion secQuestion = securityQuestionRepository.findByQuestionId(ques.getQuestionId());
				assertEquals(secQuestion.getDeleteInd(), "Y");
				assertEquals(secQuestion.getUpdateUser(), "ashish");
			}
		}
		
	}
	
	private void getAllSecurityQuestionsAfter2QuestionsDeleted() {
		MasterDataBean moduleBean = masterDataSetupServiceImpl.getSecurityQuestions(0);
		assertEquals(moduleBean.getSecurityQuestions().size(), 3);
	}
}
