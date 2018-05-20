package com.inventory.mgmt.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.inventory.mgmt.beans.TaskMasterBean;

@Repository
public class TaskMasterRepository {
	@Autowired
    JdbcTemplate jdbcTemplate;

	public TaskMasterBean findById(long id) {
	    return jdbcTemplate.queryForObject("select * from task_master where task_id=?", new Object[] {
	            id
	        },
	        new BeanPropertyRowMapper <TaskMasterBean> (TaskMasterBean.class));
	}
}
