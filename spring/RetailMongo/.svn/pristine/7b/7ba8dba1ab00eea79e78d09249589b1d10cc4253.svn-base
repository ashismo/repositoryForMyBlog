package com.org.coop.bs.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dozer.CustomConverter;
import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.org.coop.bs.mapper.converter.AddressCC;

@Configuration
@ComponentScan(value={"com.org.coop"})
public class CustomerDozerConfig {
	
	@Bean(name = "org.dozer.Mapper")
	  public DozerBeanMapper dozerBean() {
	    List mappingFiles = Arrays.asList(
	      "globalMapping.xml", 
	      "masterDataMapping.xml"
	    );
	
	    DozerBeanMapper dozerBean = new DozerBeanMapper();
	    dozerBean.setMappingFiles(mappingFiles);
	    return dozerBean;
	  }
}
