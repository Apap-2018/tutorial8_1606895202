package com.apap.tutorial6.service;

import com.apap.tutorial6.model.UserRoleModel;

import com.apap.tutorial6.repository.UserRoleDB;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;



@Service
public class UserRoleServiceImpl implements UserRoleService {
	
    @Autowired
    private UserRoleDB userDb;
    
    @Override
    public UserRoleModel addUser(UserRoleModel user) {
        String password = encrypt(user.getPassword());
        user.setPassword(password);
        return userDb.save(user);
    }

    @Override
    public String encrypt(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    @Override
    public UserRoleModel getUserByUsername(String username) {
        return userDb.findByUsername(username);
    }

    @Override
    public boolean checkIfChangePasswordValid(UserRoleModel user, NewPassword newPassword) {
        return newPassword.isNewPasswordValid() && newPassword.isOldPasswordValid(user);
    }

    @Override
    public void changeUserPassword(UserRoleModel user, NewPassword newPassword) {
        user.setPassword(newPassword.getEncryptedNewPassword());
        userDb.save(user);
    }
}