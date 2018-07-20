package com.gxh.apserver.config;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JWTTokenFilterConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>{

	private JWTTokenProvider jwtTokenProvider;
	
	public JWTTokenFilterConfigurer (JWTTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}
	
	/**
	 * Configure filter for the request received.
	 */
	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception{
		JWTAuthorizationFilter filter = new JWTAuthorizationFilter(jwtTokenProvider);
		httpSecurity.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
	}
}
