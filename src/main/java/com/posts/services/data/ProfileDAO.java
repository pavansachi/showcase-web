package com.posts.services.data;

import org.springframework.transaction.annotation.Transactional;

import com.posts.exceptions.ProfileDataException;
import com.posts.vo.data.Profile;
import com.posts.vo.data.UserRole;

public interface ProfileDAO {

	@Transactional
	public Profile createProfile(Profile newProfile) throws ProfileDataException;
	
	@Transactional
	public UserRole createRole(UserRole newRole) throws ProfileDataException;
	
	@Transactional
	public void addRole(long userId, String role) throws ProfileDataException;
	
}
