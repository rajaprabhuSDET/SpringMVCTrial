package com.solartis.spring.dao;

import java.util.List;

import com.solartis.spring.model.UserProfile;


public interface UserProfileDao {

	List<UserProfile> findAll();
	
	UserProfile findByType(String type);
	
	UserProfile findById(int id);
}
