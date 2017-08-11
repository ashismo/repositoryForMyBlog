package com.ashish.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:Spring-Beans.xml")
public class SpringUnitTest {
	@Test(expected=Exception.class)
	public void DITesting() {
		System.out.println("Basic Junit setup");
		int a = 0/0;
	}
}
