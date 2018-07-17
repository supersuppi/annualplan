package com.gxh.apserver.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gxh.apserver.entity.Role;
import com.gxh.apserver.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value="/role")
	public ResponseEntity<Collection<Role>> getUserRoles(){
		
//	 return ResponseEntity.status(HttpStatus.ACCEPTED).header("content-type", "application/json")
//				.body(userService.getAllRoles());
		return new ResponseEntity<Collection<Role>>(userService.getAllRoles(), HttpStatus.OK);
	}
}
