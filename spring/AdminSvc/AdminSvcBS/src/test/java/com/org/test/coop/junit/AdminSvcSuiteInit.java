package com.org.test.coop.junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.org.coop.bs.config.DozerConfig;
import com.org.test.coop.society.data.transaction.config.TestDataAppConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan(basePackages = "com.org.test.coop")
//@SpringApplicationConfiguration(classes={TestDataAppConfig.class, DozerConfig.class})
// Below configuration loads the application context once for all test classes
@ContextHierarchy({
	  @ContextConfiguration(classes={TestDataAppConfig.class, DozerConfig.class})
})
public class AdminSvcSuiteInit {
	@Test
	public void init() {
		
	}
}
