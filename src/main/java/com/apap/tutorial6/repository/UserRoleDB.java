package com.apap.tutorial6.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apap.tutorial6.model.UserRoleModel;

public interface UserRoleDB extends JpaRepository<UserRoleModel, Long>{
	UserRoleModel findByUsername(String username);
}
