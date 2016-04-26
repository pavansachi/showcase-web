package com.posts.services.impl;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.posts.exceptions.ProfileDataException;
import com.posts.exceptions.ProfileServiceException;
import com.posts.services.ProfileService;
import com.posts.services.data.ProfileDAO;
import com.posts.utils.SecurityUtil;
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

			user.setPassword(SecurityUtil.getHashedPassword(user.getPassword()));

			user.setProfile(newProfile);

			newProfile = profileDAO.createProfile(newProfile);
			
		} catch (ProfileDataException e) {
			e.printStackTrace();
			throw new ProfileServiceException(e);
		}
		catch (Exception e) {
			e.printStackTrace();
			
			if (e instanceof ValidationException) {
				throw new ValidationException(e);
			}
			
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

	@Override
	public Profile createProfile(String username, String password, String email, Set<String> roles)
			throws ProfileServiceException {
		
		Profile profile = new Profile();
		
		ProfileUser user = new ProfileUser();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		
		// java 8 lambdas
		
		Set<UserRole> userRoles = roles.stream().map(new Function<String, UserRole>() {

			@Override
			public UserRole apply(String role) {
				
				UserRole urole = new UserRole();
				urole.setName(role);
				urole.setProfileUser(user);
				return urole;
			}
		}).collect(Collectors.toSet());
		
		profile.setUser(user);
		user.setRoles(userRoles);
		user.setProfile(profile);
		
		profile = createProfile(profile);
		
		return profile;
	}

}
