package com.gxh.apserver.repository.interfaces;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import com.gxh.apserver.entity.Role;

public interface RolesRepository extends CrudRepository<Role, Long>{
	
	public Collection<Role> findAll();

}
