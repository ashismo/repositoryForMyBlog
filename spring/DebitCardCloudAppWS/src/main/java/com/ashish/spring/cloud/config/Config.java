package com.ashish.spring.cloud.config;

import javax.sql.DataSource;

public interface Config {
	public DataSource dataSource();
}
