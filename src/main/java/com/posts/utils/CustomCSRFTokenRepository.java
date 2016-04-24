package com.posts.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;

public class CustomCSRFTokenRepository implements CsrfTokenRepository {

	private DefaultCsrfToken token = null;
	
	@Override
	public CsrfToken generateToken(HttpServletRequest arg0) {
		
		token = new DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", "ABCXYZ");
		
		return token;
	}

	@Override
	public CsrfToken loadToken(HttpServletRequest request	) {

		return token;
	}

	@Override
	public void saveToken(CsrfToken arg0, HttpServletRequest arg1, HttpServletResponse arg2) {
	}

}
