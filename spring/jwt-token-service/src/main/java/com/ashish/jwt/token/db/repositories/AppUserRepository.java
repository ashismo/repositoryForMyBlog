package com.ashish.jwt.token.db.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ashish.jwt.token.db.model.User;

public interface AppUserRepository extends CrudRepository <User, Long>{

	User findByUsername(String username);
}
