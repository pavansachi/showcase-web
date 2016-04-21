package com.posts.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.posts.services.security.ProfileUserDetailService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private ProfileUserDetailService userDetailService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth
//		.inMemoryAuthentication()
//		.withUser("pavansachi").password("pavan1").roles("USER")
//		.and()
//		.withUser("deepika").password("deepikas").roles("USER");
		
		auth.userDetailsService(userDetailService);
		
	}
	
	 @Override
     public void configure(WebSecurity web) throws Exception {
       web
         .ignoring()
            .antMatchers("/resources/**");
     }

	protected void configure(HttpSecurity http) throws Exception {
		
		 http
	        .csrf().disable()
	        .authorizeRequests()
	        .antMatchers("/profile/create").permitAll()
		       // .antMatchers("/login","/login/form**","/register","/logout").permitAll()
		        .anyRequest().permitAll()
            .and()
            .formLogin()
//	            .loginPage("/login/form")
//	            .loginProcessingUrl("/login")
//	            .failureUrl("/login/form?error")
	            .permitAll();
	}

}
