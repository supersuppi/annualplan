package com.gxh.apserver.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.gxh.apserver.exceptions.SessionExpiredException;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter{

	private JWTTokenProvider jwtTokenProvider;
	
//	public JWTAuthorizationFilter(JWTTokenProvider jwtTokenProvider) {
//		this.jwtTokenProvider = jwtTokenProvider;
//	}
	
	public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res,
			FilterChain filterChain) throws IOException, ServletException{
		
		String token = jwtTokenProvider.resolveToken(req);
		
		try {
			if(token != null && jwtTokenProvider.validateToken(token)) {
		      Authentication auth = token != null ? jwtTokenProvider.getAuthentication(token) : null;
		      SecurityContextHolder.getContext().setAuthentication(auth);
		      filterChain.doFilter(req, res);
			}
		} catch (SessionExpiredException e) {
			e.printStackTrace();
		}
		
	}

}

