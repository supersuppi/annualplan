package com.gxh.apserver.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gxh.apserver.entity.Role;
import com.gxh.apserver.entity.User;
import com.gxh.apserver.exceptions.EmailAlreadyExistException;
import com.gxh.apserver.model.UserRequestModel;
import com.gxh.apserver.service.interfaces.UserService;
import com.gxh.apserver.serviceImpl.UserServiceImpl;

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
	public ResponseEntity registerUser(@RequestBody UserRequestModel body){
		
		User user = userService.getUserFromRequestBody(body);
		
		try {
			userService.registerUser(user);
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		} catch (EmailAlreadyExistException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}
}
