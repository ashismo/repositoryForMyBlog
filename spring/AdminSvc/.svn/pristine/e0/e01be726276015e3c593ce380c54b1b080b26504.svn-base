package com.org.coop.junit;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.org.coop.bs.config.DozerConfig;
import com.org.coop.society.data.transaction.config.TestDataAppConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestDataAppConfig.class, DozerConfig.class})
@ComponentScan(basePackages = "com.org.coop"/*, excludeFilters = { @Filter(type = FilterType.ANNOTATION, value = Configuration.class) }*/) 
public class CooperativeServiceBSTest {
	private static final Logger logger = Logger.getLogger(CooperativeServiceBSTest.class);
	
//	@Autowired
//	private BranchSetupWSImpl branchSetupWSImpl;
	
	@Test
	public void getBranch() {
//		branchSetupWSImpl.getBranch(25);
	}
}
