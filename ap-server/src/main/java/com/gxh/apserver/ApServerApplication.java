package com.gxh.apserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class ApServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApServerApplication.class, args);
	}

	@RequestMapping("/hello")
	String home() {
		return "Hello World!";
	}

}
