package com.ashish.poc.autojunit.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ashish.poc.autojunit.entities.Employees;

@Repository
public interface EmployeeRepository extends CrudRepository<Employees, Integer> {
	public Employees findById(int id);
	public List<Employees> findAll();
	public void deleteById(int id);
}
