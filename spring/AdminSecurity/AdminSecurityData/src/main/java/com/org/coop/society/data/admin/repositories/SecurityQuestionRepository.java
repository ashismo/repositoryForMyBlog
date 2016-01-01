package com.org.coop.society.data.admin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.coop.society.data.admin.entities.SecurityQuestion;
import com.org.coop.society.data.admin.entities.User;

public interface SecurityQuestionRepository extends JpaRepository<SecurityQuestion, Integer> {
	public SecurityQuestion findByQuestionId(int questionId);
}
