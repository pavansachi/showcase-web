package com.posts.controllers;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//	@Autowired
//	private ProfileUserDetailService userDetailService;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth
//		.inMemoryAuthentication()
//		.withUser("pavansachi").password("pavan1").roles("USER")
//		.and()
//		.withUser("deepika").password("deepikas").roles("USER");
		
//		auth.userDetailsService(userDetailService);
		
		auth.jdbcAuthentication()
		.passwordEncoder(passwordEncoder())
		.dataSource(dataSource)
		.usersByUsernameQuery("select username, password, enabled from profile_user where username=?")
		.authoritiesByUsernameQuery("select username, role_name from profile_user u inner join user_role r on (u.id = r.profileUser_id) where username=?");
		
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
		        .anyRequest().access("hasRole('ROLE_USER')")
            .and()
            .formLogin()
//	            .loginPage("/login/form")
//	            .loginProcessingUrl("/login")
//	            .failureUrl("/login/form?error")
	            .permitAll();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

}
