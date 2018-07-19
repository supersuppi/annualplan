package com.gxh.apserver.config;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.gxh.apserver.entity.Role;
import com.gxh.apserver.exceptions.SessionExpiredException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTTokenProvider {
	
	@Autowired
	private UserDetailsSecurityConfig detailsSecurityConfig;
	
	public String generateJwtToken(String emailAddress, Role role) throws UnsupportedEncodingException {
		
		String userRole = "ROLE_"+role.getName();
		
		Claims claims = Jwts.claims().setSubject(emailAddress);
		claims.put("auth", Arrays.asList(new SimpleGrantedAuthority(userRole)));
		
		
		String token = Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET.getBytes("UTF-8"))
				.compact();
		
		return token;
	}
	
	
	public Authentication getAuthentication(String token) {
		UserDetails userDetails = detailsSecurityConfig.loadUserByUsername(getUserName(token));
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}

	public String getUserName(String token) {
		return Jwts.parser().setSigningKey(SecurityConstants.SECRET)
				.parseClaimsJws(token).getBody().getSubject();
	}
	
	public String resolveToken(HttpServletRequest req){
		String bearer = req.getHeader(SecurityConstants.BEARER_STRING);
		if(bearer != null && bearer.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			return bearer.substring(7, bearer.length());
		}
		return null;
	}
	
	public boolean validateToken(String token) throws SessionExpiredException{
		try {
			Jwts.parser().setSigningKey(SecurityConstants.SECRET).parseClaimsJws(token);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			throw new SessionExpiredException("Expired or invalid JWT token");
		}
	}

}
