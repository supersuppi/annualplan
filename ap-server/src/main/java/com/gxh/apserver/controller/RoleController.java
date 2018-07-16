package com.gxh.apserver.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gxh.apserver.entity.Role;
import com.gxh.apserver.service.RolesService;

@RestController
@CrossOrigin
public class RoleController {

	@Autowired
	private RolesService roleService;
	
	@RequestMapping(value="/role", method=RequestMethod.GET)
	public ResponseEntity<Collection<Role>> getUserRoles(){
		
	 return ResponseEntity.status(HttpStatus.ACCEPTED).header("content-type", "application/json")
				.body(roleService.getAllRoles());
	}
}
