package com.solartis.spring.service;

import java.util.List;

import com.solartis.spring.model.UserProfile;

public interface UserProfileService {

	UserProfile findById(int id);

	UserProfile findByType(String type);
	
	List<UserProfile> findAll();
	
}
