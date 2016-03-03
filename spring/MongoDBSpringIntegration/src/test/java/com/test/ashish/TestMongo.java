package com.test.ashish;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ashish.config.TestMongoDBConfig;
import com.ashish.service.MongoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan(basePackages = "com.ashish")
//@ContextHierarchy({
//	  @ContextConfiguration(classes={TestMongoDBConfig.class})
//})
//@WebAppConfiguration
@ContextConfiguration(classes={TestMongoDBConfig.class})
public class TestMongo {

	@Autowired
	private MongoService mongoService;
	
	@Test
	public void test() {
		mongoService.deleteMaterials();
		mongoService.addMaterial();
		mongoService.addAnotherMaterial();
		mongoService.getMaterial();
		System.out.println("Abc");
	}
	
}
