package com.posts.controllers;

import java.util.Set;

import javax.validation.Valid;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.posts.exceptions.ProfileServiceException;
import com.posts.services.ProfileService;
import com.posts.utils.aspects.Timed;
import com.posts.vo.data.Profile;
import com.posts.vo.data.ServiceResponse;
import com.posts.vo.data.UserRole;

@Controller
public class ProfileController {

	@Autowired
	private ProfileService profileService;

	@RequestMapping(path="/profile/create", method=RequestMethod.POST, consumes="application/json", produces="application/json")
	public @ResponseBody ResponseEntity<ServiceResponse> createProfile(@Valid @RequestBody Profile profile) {

		ServiceResponse response = null;

		try {

			profile = profileService.createProfile(profile);

			response = new ServiceResponse("The profile is successfully created", profile);
			return new ResponseEntity<ServiceResponse>(response, HttpStatus.OK);

		}
		catch (ProfileServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<ServiceResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ServiceResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@Timed
	@RequestMapping(path="/profile/create", method=RequestMethod.POST, consumes="application/x-www-form-urlencoded")
	public @ResponseBody ResponseEntity<ServiceResponse> createProfile(@RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("email") String email, @RequestParam("roles") Set<String> roles)  {

		ServiceResponse response = null;

		Profile profile = null;
			
		try {

			profile = profileService.createProfile(username, password, email, roles);

			response = new ServiceResponse("The profile is successfully created", profile);
			return new ResponseEntity<ServiceResponse>(response, HttpStatus.OK);

		}
		catch (ProfileServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<ServiceResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		catch (Exception e) {
			
			e.printStackTrace();
			
			if (e instanceof ValidationException) {
				
				ServiceResponse errorResponse = new ServiceResponse(e.getMessage());
				
				return new ResponseEntity<ServiceResponse>(errorResponse, HttpStatus.BAD_REQUEST);
			}
			
			return new ResponseEntity<ServiceResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(path="/role/create", method=RequestMethod.POST, consumes="application/json", produces="application/json")
	public @ResponseBody ResponseEntity<ServiceResponse> createRole(@Valid @RequestBody UserRole role) {

		ServiceResponse response = null;

		try {

			role = profileService.createRole(role);

			response = new ServiceResponse("The role is successfully created", role);
			return new ResponseEntity<ServiceResponse>(response, HttpStatus.OK);

		}
		catch (ProfileServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<ServiceResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ServiceResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ServiceResponse> handleMethodArgumentNotValidException( MethodArgumentNotValidException error ) {

		System.out.println(error);
		
		return new ResponseEntity<ServiceResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
