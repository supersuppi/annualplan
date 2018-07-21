package com.gxh.apserver.repository.interfaces;

import org.springframework.data.repository.CrudRepository;

import com.gxh.apserver.entity.User;
import com.gxh.apserver.entity.UserContact;

public interface UserContactsRepository extends CrudRepository<UserContact, Long>{

	public UserContact findByUser(User user);

}
