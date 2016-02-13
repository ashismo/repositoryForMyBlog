package com.org.test.coop.master.junit;

import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
import com.org.coop.canonical.master.beans.CountryMasterBean;
import com.org.coop.canonical.master.beans.DistrictMasterBean;
import com.org.coop.canonical.master.beans.MasterDataBean;
import com.org.coop.canonical.master.beans.StateMasterBean;
import com.org.coop.society.data.admin.entities.DistrictMaster;
import com.org.coop.society.data.admin.entities.StateMaster;
import com.org.coop.society.data.admin.repositories.CountryMasterRepository;
import com.org.coop.society.data.admin.repositories.DistrictMasterRepository;
import com.org.coop.society.data.admin.repositories.StateMasterRepository;
import com.org.test.coop.junit.JunitTestUtil;
import com.org.test.coop.society.data.transaction.config.TestDataAppConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan(basePackages = "com.org.test.coop")
@ContextHierarchy({
	  @ContextConfiguration(classes={TestDataAppConfig.class, DozerConfig.class})
})
//@SpringApplicationConfiguration(classes={TestDataAppConfig.class, DozerConfig.class})
public class CountryStateDistTest {
	private static final Logger logger = Logger.getLogger(CountryStateDistTest.class);
	private MasterDataBean createCountryBean;
	private MasterDataBean createStateBean;
	private MasterDataBean createDistBean;
	
	private String createCountryJson = null;
	private String createStateJson = null;
	private String createDistJson = null;
	
	private ObjectMapper om = null;
	
	@Autowired
	private MasterDataSetupServiceImpl masterDataSetupServiceImpl;
	
	@Autowired
	private CountryMasterRepository countryMasterRepository;
	
	@Autowired
	private StateMasterRepository stateMasterRepository;
	
	@Autowired
	private DistrictMasterRepository districtMasterRepository;
	
	@Before
	public void runBefore() {
		try {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			om = new ObjectMapper();
			om.setSerializationInclusion(Include.NON_NULL);
			om.setDateFormat(df);
			createCountryJson = JunitTestUtil.getFileContent("inputJson/master/countryStateDist/createCountry.json");
			createCountryBean = om.readValue(createCountryJson, MasterDataBean.class);
			
			createStateJson = JunitTestUtil.getFileContent("inputJson/master/countryStateDist/createState.json");
			createStateBean = om.readValue(createStateJson, MasterDataBean.class);
			
			createDistJson = JunitTestUtil.getFileContent("inputJson/master/countryStateDist/createDist.json");
			createDistBean = om.readValue(createDistJson, MasterDataBean.class);
			
		} catch (Exception e) {
			logger.error("Error while initializing: ", e);
		}
	}
	@Test
	public void testCountryStateDist() {
		addCountry();
		addState();
		addDist();
	}

	private void addCountry() {
		try {
			MasterDataBean moduleBean = masterDataSetupServiceImpl.saveCountryStateDist(createCountryBean);
			
			assertEquals(((CountryMasterBean)moduleBean.getCountries().toArray()[0]).getCountryCode(), "IND");
			
			CountryMasterBean countryMaster = ((CountryMasterBean)createCountryBean.getCountries().toArray()[0]);
			countryMaster.setCountryCode("USA");
			countryMaster.setCountryId(0);
			countryMaster.setCountryName("United States of America");
			
			moduleBean = masterDataSetupServiceImpl.saveCountryStateDist(createCountryBean);
			
			assertEquals(((CountryMasterBean)moduleBean.getCountries().toArray()[0]).getCountryCode(), "USA");
			
		} catch (Exception e) {
			logger.error("Error while adding Country: ", e);
			Assert.fail("Error while adding Country:");
		}
	}
	
	private void addState() {
		try {
			MasterDataBean moduleBean = masterDataSetupServiceImpl.saveCountryStateDist(createStateBean);
			StateMaster state = stateMasterRepository.findByStateCode("WB");
			assertEquals(state.getStateCode(), "WB");
			assertEquals(state.getStateName(), "West Bengal");
			
			StateMasterBean stateMaster = ((StateMasterBean)((CountryMasterBean)createStateBean.getCountries().toArray()[0]).getStates().toArray()[0]);
			stateMaster.setStateCode("KA");
			stateMaster.setStateId(0);
			stateMaster.setStateName("Karnataka");
			stateMaster.setCountryCode("IND");
			
			moduleBean = masterDataSetupServiceImpl.saveCountryStateDist(createStateBean);
			
			state = stateMasterRepository.findByStateCode("KA");
			assertEquals(state.getStateCode(), "KA");
			assertEquals(state.getStateName(), "Karnataka");
			
		} catch (Exception e) {
			logger.error("Error while adding State: ", e);
			Assert.fail("Error while adding State:");
		}
	}
	
	private void addDist() {
		try {
			MasterDataBean moduleBean = masterDataSetupServiceImpl.saveCountryStateDist(createDistBean);
			DistrictMaster dist = districtMasterRepository.findByDistrictCode("HLY");
			assertEquals(dist.getDistrictCode(), "HLY");
			assertEquals(dist.getDistrictName(), "Hooghly");
			
			StateMasterBean stateMaster = ((StateMasterBean)((CountryMasterBean)createDistBean.getCountries().toArray()[0]).getStates().toArray()[0]);
			DistrictMasterBean distBean = (DistrictMasterBean)stateMaster.getDistricts().toArray()[0];
			distBean.setCountryCode("IND");
			distBean.setStateCode("WB");
			distBean.setDistId(0);
			distBean.setDistrictCode("HOW");
			distBean.setDistrictName("Howrah");
			
			moduleBean = masterDataSetupServiceImpl.saveCountryStateDist(createDistBean);
			
			dist = districtMasterRepository.findByDistrictCode("HOW");
			assertEquals(dist.getDistrictCode(), "HOW");
			assertEquals(dist.getDistrictName(), "Howrah");
			
		} catch (Exception e) {
			logger.error("Error while adding State: ", e);
			Assert.fail("Error while adding State:");
		}
	}
	
}
