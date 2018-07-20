package com.gxh.apserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gxh.apserver.entity.User;
import com.gxh.apserver.repository.interfaces.UserRepository;

@Service
public class UserDetailsSecurityConfig implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@SuppressWarnings("unused")
	@Override
	public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
		
		final User user = userRepository.findByEmail(emailAddress);
		String userRole = "ROLE_"+user.getRole().getName();
		
		SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(userRole);
		
		if (user != null) {
			return org.springframework.security.core.userdetails.User
					.withUsername(emailAddress)
					.password(user.getPassword())
					.authorities(grantedAuthority)
			        .accountExpired(false)//
			        .accountLocked(false)//
			        .credentialsExpired(false)//
			        .disabled(false)//
			        .build();
		} else {
			throw new UsernameNotFoundException("User with '" + emailAddress + "' not found");
		}
		
	}

}
