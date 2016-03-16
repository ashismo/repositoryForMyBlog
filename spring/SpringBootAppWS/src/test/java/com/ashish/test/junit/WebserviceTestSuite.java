package com.ashish.test.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	WebserviceTest1.class,
	WebserviceTest2.class
})
public class WebserviceTestSuite {
	
}
