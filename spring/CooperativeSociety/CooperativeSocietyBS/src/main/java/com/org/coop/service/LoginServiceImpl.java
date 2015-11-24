package com.org.coop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.coop.society.data.customer.entities.Staff;
import com.org.coop.society.data.customer.repositories.StaffRepository;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private StaffRepository staffRepository;
	
	public boolean validateLogin() {
		List<Staff> staffList = staffRepository.findAll();
		System.out.println("Staffsize:" + staffList.get(0).getFirstName());
		return true;
	}
}
