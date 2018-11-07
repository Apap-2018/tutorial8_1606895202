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
	public void updateUser(String newPassword, UserRoleModel user) {
		String newEncryptedPassword = encrypt(newPassword);
		user.setPassword(newEncryptedPassword);
		userDb.save(user);
	}

	@Override
	public boolean validateRequirements(String password) {
		char[] pass = password.toCharArray();
		int alfabet = 0;
		int digit = 0;
		for (char character : pass) {
			if (Character.isLetter(character))
				alfabet++;
			else if (Character.isDigit(character))
				digit++;;
		}
		return (((alfabet + digit) >= 8) && (alfabet > 0) && (digit > 0)) ? true : false;
	}
}