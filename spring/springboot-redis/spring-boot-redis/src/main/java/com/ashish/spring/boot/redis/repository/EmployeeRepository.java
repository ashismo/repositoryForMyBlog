package com.ashish.spring.boot.redis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ashish.spring.boot.redis.dao.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, String> {}