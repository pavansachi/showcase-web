package com.posts.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.posts.exceptions.ProfileDataException;
import com.posts.exceptions.ProfileServiceException;
import com.posts.services.ProfileService;
import com.posts.services.data.ProfileDAO;
import com.posts.utils.PasswordEncoder;
import com.posts.vo.data.Profile;
import com.posts.vo.data.ProfileUser;
import com.posts.vo.data.UserRole;

@Service(value="profileService")
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	private ProfileDAO profileDAO;

	public Profile createProfile(Profile newProfile) throws ProfileServiceException {

		try {

			ProfileUser user = newProfile.getUser();

			user.setHashedPassword(PasswordEncoder.getHashedPassword(user.getPassword()));

			user.setProfile(newProfile);

			newProfile = profileDAO.createProfile(newProfile);
			
		} catch (ProfileDataException e) {
			e.printStackTrace();
			throw new ProfileServiceException(e);
		}

		return newProfile;
	}

	@Override
	public UserRole createRole(UserRole newRole) throws ProfileServiceException {

		try {

			newRole = profileDAO.createRole(newRole);
			
		} catch (ProfileDataException e) {
			e.printStackTrace();
			throw new ProfileServiceException(e);
		}

		return newRole;
	}

}
