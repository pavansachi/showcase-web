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
import org.springframework.security.web.csrf.CsrfTokenRepository;

import com.posts.utils.CustomCSRFTokenRepository;

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
		.usersByUsernameQuery("select username, password, enabled from PROFILE_USER where username=?")
		.authoritiesByUsernameQuery("select username, role_name from PROFILE_USER u inner join USER_ROLE r on (u.id = r.profileUser_id) where username=?");
		
	}
	
	 @Override
     public void configure(WebSecurity web) throws Exception {
       web
         .ignoring()
            .antMatchers("/resources/**");
     }

	protected void configure(HttpSecurity http) throws Exception {
		
		 http
		 // test csrf
	        .csrf().csrfTokenRepository(csrfTokenRepository())
	        .and()
	        .authorizeRequests()
	        .antMatchers("/profile/create").permitAll()
		       // .antMatchers("/login","/login/form**","/register","/logout").permitAll()
		    .anyRequest().access("hasRole('ROLE_USER')")
            .and()
            .formLogin()
	            .loginPage("/login")
//	            .loginProcessingUrl("/login")
//	            .failureUrl("/login/form?error")
	            .permitAll();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	
	public CsrfTokenRepository csrfTokenRepository() {
		
		CsrfTokenRepository repo = new CustomCSRFTokenRepository();
		
		return repo;
		
	}

}
