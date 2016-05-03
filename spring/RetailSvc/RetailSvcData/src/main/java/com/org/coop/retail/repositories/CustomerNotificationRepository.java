package com.org.coop.retail.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.coop.retail.entities.CustomerNotification;

public interface CustomerNotificationRepository extends JpaRepository<CustomerNotification, Integer> {
	
	
}
