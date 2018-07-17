package com.gxh.apserver.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxh.apserver.entity.Role;
import com.gxh.apserver.repository.interfaces.RolesRepository;

@Service
public class UserService {
	
	@Autowired
	private RolesRepository rolesRepository;
	
	public Collection<Role> getAllRoles() {
		return rolesRepository.findAll();
	}
	
}
