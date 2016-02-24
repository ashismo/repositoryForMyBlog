package com.org.coop.society.data.admin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.coop.society.data.admin.entities.User;
import com.org.coop.society.data.admin.entities.UserCredentialOtp;

public interface UserCredentialOtpRepository extends JpaRepository<UserCredentialOtp, Integer> {
}
