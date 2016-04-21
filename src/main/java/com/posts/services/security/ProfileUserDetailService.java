package com.posts.services.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.posts.services.jpa.ProfileUserRepository;
import com.posts.vo.data.ProfileUser;
import com.posts.vo.data.SecurityUser;

@Service("profileUserDetailSvc")
public class ProfileUserDetailService implements UserDetailsService {

	@Autowired
	private ProfileUserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		ProfileUser user = repository.findByUsername(username);
		
		if(user == null){
            throw new UsernameNotFoundException("UserName "+username+" not found");
        }
		
		return new SecurityUser(user);
	}

}
