package com.gxh.apserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * Main Class and entry point to the app
 */
@RestController
@SpringBootApplication
public class ApServerApplication extends SpringBootServletInitializer {	

	//This is required for standalone app, to deploy in tomcat: uncomment for prod deploy
//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//		return application.sources(ApServerApplication.class);
//	}

	/*
	 * Main Method
	 */
	public static void main(String[] args) {
		SpringApplication.run(ApServerApplication.class, args);
	}
	
	/*
	 * Password Encode/Decode
	 */
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/*
	 * Health check endpoint
	 */
	@GetMapping("/hello")
	String home() {
		return "Hello World!";
	}

}
