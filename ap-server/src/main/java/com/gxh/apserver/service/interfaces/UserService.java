package com.gxh.apserver.service.interfaces;

import java.util.Collection;

import com.gxh.apserver.entity.Role;
import com.gxh.apserver.entity.User;
import com.gxh.apserver.exceptions.EmailAlreadyExistException;
import com.gxh.apserver.model.UserRequestModel;

public interface UserService {
	
	public Collection<Role> getAllRoles();
	
	public User getUserFromRequestBody(UserRequestModel requestBody);
	
	public void registerUser(User user) throws EmailAlreadyExistException;
	
	public boolean isUserEmailAddressUnique(String emailAddress);

}
