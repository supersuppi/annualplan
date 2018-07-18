package com.gxh.apserver.config;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter{

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res,
			FilterChain filterChain) throws IOException, ServletException{
		
		String header = req.getHeader(SecurityConstants.HEADER_STRING);
		if(header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			filterChain.doFilter(req, res);
			return;
		}
		
		UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(req);
		
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		filterChain.doFilter(req, res);
	}
	
	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest req)
			throws UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException,
			UnsupportedEncodingException {
		String token = req.getHeader(SecurityConstants.HEADER_STRING);
		
		if(token != null) {
			String user = Jwts.parser()
							.setSigningKey(SecurityConstants.SECRET.getBytes("UTF-8"))
							.parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX, ""))
							.getBody()
							.getSubject();
			
			if(user != null) {
				return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
			}
			return null;
		}	
		
		return null;
	}

}

