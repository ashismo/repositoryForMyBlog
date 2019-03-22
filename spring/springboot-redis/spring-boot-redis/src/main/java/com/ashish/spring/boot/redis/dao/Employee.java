package com.ashish.spring.boot.redis.dao;

import java.io.Serializable;

import org.springframework.data.redis.core.RedisHash;

@RedisHash("Employee")
public class Employee implements Serializable {
   
    private String id;
    private String name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}