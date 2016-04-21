package com.posts.services;

import com.posts.exceptions.ProfileServiceException;
import com.posts.vo.data.Profile;
import com.posts.vo.data.UserRole;

public interface ProfileService {

	public Profile createProfile(Profile newPost) throws ProfileServiceException;
	
	public UserRole createRole(UserRole newRole) throws ProfileServiceException;
	
}
