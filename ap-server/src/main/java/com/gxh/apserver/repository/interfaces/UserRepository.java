package com.gxh.apserver.repository.interfaces;

import org.springframework.data.repository.CrudRepository;

import com.gxh.apserver.entity.User;

public interface UserRepository extends CrudRepository<User, Long>{

	public int countByEmail(String emailAddress);
	
}
