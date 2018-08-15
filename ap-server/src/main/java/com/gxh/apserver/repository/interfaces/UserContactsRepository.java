package com.gxh.apserver.repository.interfaces;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gxh.apserver.entity.User;
import com.gxh.apserver.entity.UserContact;

public interface UserContactsRepository extends CrudRepository<UserContact, Long>{

	@Query("Select c from UserContact c, User u where c.user=u.id and u.email=?1")
	public UserContact findByEmailAddress(String emailAddress);

}
