package com.ashish.jwt.token.db.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ashish.jwt.token.db.model.Role;

public interface AppRoleRepository extends CrudRepository <Role, Long>{

	Role findByRolename(String rolename);
}
