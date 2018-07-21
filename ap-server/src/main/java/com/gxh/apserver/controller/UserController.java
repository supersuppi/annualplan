package com.gxh.apserver.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gxh.apserver.entity.Role;
import com.gxh.apserver.entity.UserContact;
import com.gxh.apserver.exceptions.CustomException;
import com.gxh.apserver.exceptions.EmailAlreadyExistException;
import com.gxh.apserver.exceptions.InvalidEmailPasswordException;
import com.gxh.apserver.model.UserRequestModel;
import com.gxh.apserver.security.SecurityConstants;
import com.gxh.apserver.service.interfaces.UserService;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value="/role")
	public ResponseEntity<Collection<Role>> getUserRoles(){
		return new ResponseEntity<Collection<Role>>(userService.getAllRoles(), HttpStatus.OK);
	}
	
	@PostMapping(value="/register")
	public ResponseEntity<?> registerUser(@RequestBody UserRequestModel body){
		
		UserContact userContact = userService.getUserFromRequestBody(body);
		
		try {
			userService.registerUser(userContact);
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		} catch (EmailAlreadyExistException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}
	
	@PostMapping(value="/login")
	public ResponseEntity<?> loginUser(HttpServletResponse res,
			@RequestBody UserRequestModel payload) {
		
		try {
			String token = userService.loginUser(payload.getEmail(), payload.getPassword());
			
			res.addHeader("access-control-expose-headers", "Authorization");
			res.addHeader(SecurityConstants.BEARER_STRING, SecurityConstants.TOKEN_PREFIX + token);
			
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		} catch (CustomException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} catch (InvalidEmailPasswordException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		
	}
	
	@GetMapping(value="/profile/{email}")
	public ResponseEntity<UserContact> userProfile(@PathVariable("email") String emailAddress ) {
		
		UserContact userContact = userService.getUserProfile(emailAddress);
		
		return new ResponseEntity<UserContact>(userContact, HttpStatus.OK);
	}
	
}
