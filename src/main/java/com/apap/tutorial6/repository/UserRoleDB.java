package com.apap.tutorial6.repository;

import com.apap.tutorial6.model.UserRoleModel;

public interface UserRoleDB extends JpaRepository<UserRoleModel, Long>{
	UserRoleModel findByUsernam(String username);
}
