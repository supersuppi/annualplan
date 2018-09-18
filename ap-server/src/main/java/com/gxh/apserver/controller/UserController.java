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

import com.gxh.apserver.dto.UserDTO;
import com.gxh.apserver.entity.Role;
import com.gxh.apserver.entity.UserContact;
import com.gxh.apserver.exceptions.CustomException;
import com.gxh.apserver.exceptions.EmailAlreadyExistException;
import com.gxh.apserver.exceptions.InvalidEmailPasswordException;
import com.gxh.apserver.model.UserRequestModel;
import com.gxh.apserver.security.SecurityConstants;
import com.gxh.apserver.service.interfaces.UserService;

/*
 * A controller class for user management
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * Get all the available roles for new users by admin.
	 * @return
	 */
	@GetMapping(value="/role")
	public ResponseEntity<Collection<Role>> getUserRoles(){
		return new ResponseEntity<Collection<Role>>(userService.getAllRoles(), HttpStatus.OK);
	}
	
	/**
	 * Register a user.
	 * @param body
	 * @return
	 */
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
	
	/**
	 * Login a user.
	 * @param res
	 * @param payload
	 * @return
	 */
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
	
	/**
	 * Get user profile.
	 * @param emailAddress
	 * @return
	 */
	@GetMapping(value="/profile/{email}")
	public ResponseEntity<UserDTO> userProfile(@PathVariable("email") String emailAddress ) {
		UserDTO userDTO = userService.getUserProfile(emailAddress);
		return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
	}
	
	/**
	 * Get user information by email.
	 * @param emailAddress
	 * @return
	 */
	@GetMapping(value="/{email}")
	public ResponseEntity<UserDTO> getUserInfo( @PathVariable("email") String emailAddress ) {
		UserDTO userDTO = userService.getUserProfile(emailAddress);
		return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
	}
	
}
