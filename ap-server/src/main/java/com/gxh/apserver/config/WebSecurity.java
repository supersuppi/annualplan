package com.gxh.apserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.gxh.apserver.security.JWTTokenFilterConfigurer;
import com.gxh.apserver.security.JWTTokenProvider;

import java.util.Arrays;
import java.util.Collections;

/**
 * 
 * Security configuration class providing Inmemoryauthentication using BcryptPassword,
 * configure the filters and authorization for the requests.
 *
 */
@EnableWebSecurity
@Configuration
public class WebSecurity extends WebSecurityConfigurerAdapter{
	
	private BCryptPasswordEncoder passwordEncoder;
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JWTTokenProvider jwtTokenProvider;
	
	public WebSecurity(BCryptPasswordEncoder passwordEncoder, 
			UserDetailsService userDetailsService) {
		this.passwordEncoder = passwordEncoder;
		this.userDetailsService = userDetailsService;
	}
	
	/**
	 * Adding spring-security to the project, will add an inmemoryauthentication.
	 * To provide the username and password before sending any request.
	 */
	public void configure(AuthenticationManagerBuilder auth)  {
		try {
			// Including bcrypt bean , the password will be encoded; So, encode() the password.
			auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Specify , which REST api needs to be allowed without authentication.
	 */
	public void configure(HttpSecurity http) throws Exception{
		http.csrf()
			.disable().cors().and()
			// Creation of tokens is 'stateless' 
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and().authorizeRequests()
			// Allow '/user/login' without authentication.
			.antMatchers("/user/login").permitAll()
			//TODO remove this line for prod , this is to bypass security
			.antMatchers(HttpMethod.OPTIONS,"/**").permitAll()//allow CORS option calls
			.anyRequest()
			.authenticated()
			.and()
			// Filter for validating request token before processing each request.
			.apply(new JWTTokenFilterConfigurer(jwtTokenProvider));
	}
	
	/**
	 * 
	 *  Ui is running on port 4200 and server is on port 8080,
	 *  to enable communication between them we need to include CORS configuration.
	 */
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
//		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
//		return source;

		final CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Collections.singletonList("*"));
		configuration.setAllowedMethods(Arrays.asList("HEAD",
				"GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
		configuration.setAllowCredentials(true);
		configuration.setAllowedHeaders(Collections.singletonList("*"));
		configuration.setExposedHeaders(Arrays.asList("X-Auth-Token","Authorization","Access-Control-Allow-Origin","Access-Control-Allow-Credentials"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	
    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

	/**
	 *  Add this to remove cross origin error at client and The request was
	 *  rejected because the URL was not normalized at server, when making post
	 *  call from modal service(ngx-modal)
	 *
	 */
	@Bean
	public HttpFirewall defaultHttpFirewall() {
		return new DefaultHttpFirewall();
	}
}
