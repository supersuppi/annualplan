package com.gxh.apserver.repository.interfaces;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gxh.apserver.entity.User;
import com.gxh.apserver.entity.UserContact;

public interface UserContactsRepository extends CrudRepository<UserContact, Long>{

//	@Query("Select c from user_contact c, user u where c.user_id=u.id and u.email='email'")
	public UserContact findByUser(User user);

}
