package com.gxh.apserver.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import com.gxh.apserver.entity.Role;
import com.gxh.apserver.repository.interfaces.RolesRepository;

public class UserService {
	
	@Autowired
	private RolesRepository rolesRepository;
	
	public Collection<Role> getAllRoles() {
		return rolesRepository.findAll();
	}
	
}
