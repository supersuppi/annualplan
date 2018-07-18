package com.gxh.apserver.service.interfaces;

import java.util.Collection;

import com.gxh.apserver.entity.Role;
import com.gxh.apserver.entity.UserContact;
import com.gxh.apserver.exceptions.EmailAlreadyExistException;
import com.gxh.apserver.model.UserRequestModel;

public interface UserService {
	
	public Collection<Role> getAllRoles();
	
	public UserContact getUserFromRequestBody(UserRequestModel requestBody);
	
	public void registerUser(UserContact userContact) throws EmailAlreadyExistException;
	
	public boolean isUserEmailAddressUnique(String emailAddress);

}
