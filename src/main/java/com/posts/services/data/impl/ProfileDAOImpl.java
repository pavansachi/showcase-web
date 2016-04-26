package com.posts.services.data.impl;

import javax.validation.ValidationException;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.posts.exceptions.ProfileDataException;
import com.posts.services.data.ProfileDAO;
import com.posts.services.jpa.ProfileRepository;
import com.posts.services.jpa.ProfileUserRepository;
import com.posts.services.jpa.RoleRepository;
import com.posts.vo.data.Profile;
import com.posts.vo.data.ProfileUser;
import com.posts.vo.data.UserRole;

@Service(value="profileDao")
public class ProfileDAOImpl implements ProfileDAO {

	@Autowired
	private ProfileRepository profileRepo;
	
	@Autowired
	private ProfileUserRepository profileUserRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	public Profile createProfile(Profile newProfile) throws ProfileDataException {

		try {

			profileRepo.save(newProfile);
			
			return newProfile;

		} 
		catch (Exception e) {
			
			e.printStackTrace();
			
			if (e instanceof ConstraintViolationException) {
				throw new ValidationException(e.getMessage());
			}
			
			throw new ProfileDataException();
		}
	}

	@Override
	public UserRole createRole(UserRole newRole) throws ProfileDataException {
		
		try {

			roleRepo.save(newRole);
			
			return newRole;

		} catch (Exception e) {
			
			e.printStackTrace();
			throw new ProfileDataException(e);
		}
	}

	@Override
	public void addRole(long userId, String role) throws ProfileDataException {
		
		try {

			UserRole userRole = new UserRole();
			userRole.setName(role);
			
			ProfileUser profileUser = profileUserRepo.findOne(userId);

			userRole.setProfileUser(profileUser);
			
			roleRepo.save(userRole);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new ProfileDataException(e);
		}
		
	}

}
