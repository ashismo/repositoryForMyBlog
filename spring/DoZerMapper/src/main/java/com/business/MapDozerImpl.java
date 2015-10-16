package com.business;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.config.DozerConfig;
import com.dozerbean.ParentBean;
import com.entity.Parent;

@Component
public class MapDozerImpl implements MapDozer{

	public void mapBean(ParentBean srcParent, Parent destParent) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DozerConfig.class);
		Mapper dozerBeanMapper = (Mapper) context.getBean("org.dozer.Mapper", DozerBeanMapper.class);
		dozerBeanMapper.map(srcParent, destParent, "parentChildMapping");
	}
}
