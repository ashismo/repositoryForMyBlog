package com.org.coop.bs.mapper;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.org.coop.bs.config.DozerConfig;

@Component
public class BranchMappingImpl {
	public void mapBean(Object src, Object dest) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DozerConfig.class);
		Mapper dozerBeanMapper = (Mapper) context.getBean("org.dozer.Mapper", DozerBeanMapper.class);
		dozerBeanMapper.map(src, dest, "branchMap");
	}
}