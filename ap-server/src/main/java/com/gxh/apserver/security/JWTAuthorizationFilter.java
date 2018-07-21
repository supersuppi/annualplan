package com.gxh.apserver.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.gxh.apserver.exceptions.CustomException;
import com.gxh.apserver.exceptions.SessionExpiredException;

/**
 * 
 * Filter class to validate the token from request header.
 *
 */
public class JWTAuthorizationFilter extends GenericFilterBean {

	@Autowired
	private JWTTokenProvider jwtTokenProvider;
	
	public JWTAuthorizationFilter(JWTTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}

	/**
	 * Filters for request received from external source.
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
				
		String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
		
		try {
			if(token != null && jwtTokenProvider.validateToken(token)) {
		      Authentication auth = token != null ? jwtTokenProvider.getAuthentication(token) : null;
		      SecurityContextHolder.getContext().setAuthentication(auth);
			}
			chain.doFilter(request, response);
		} catch (SessionExpiredException | CustomException e) {
			e.printStackTrace();
		}
	}

}

