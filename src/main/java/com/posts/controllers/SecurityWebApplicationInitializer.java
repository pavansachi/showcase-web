package com.posts.controllers;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

//@Order(1)
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer{

	public SecurityWebApplicationInitializer() {
		System.out.println("SecurityWebApplicationInitializer");
	}
}
