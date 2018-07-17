package com.gxh.apserver.serviceImpl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxh.apserver.entity.Role;
import com.gxh.apserver.entity.User;
import com.gxh.apserver.exceptions.EmailAlreadyExistException;
import com.gxh.apserver.model.UserRequestModel;
import com.gxh.apserver.repository.interfaces.RolesRepository;
import com.gxh.apserver.repository.interfaces.UserRepository;
import com.gxh.apserver.service.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private RolesRepository rolesRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public Collection<Role> getAllRoles() {
		return rolesRepository.findAll();
	}
	
	@Override
	public User getUserFromRequestBody(UserRequestModel requestBody) {
		
		User user = new User();
		user.setFirstName(requestBody.getFirstName());
		user.setLastName(requestBody.getLastName());
		user.setEmail(requestBody.getEmail());
		user.setPassword(requestBody.getPassword());
		user.setRole(requestBody.getRole());
		
		return user;
	}
	
	public void registerUser(User user) throws EmailAlreadyExistException {
		
		if(isUserEmailAddressUnique(user.getEmail())) {
			throw new EmailAlreadyExistException("Email address already exist");
		}
		
		userRepository.save(user);
	}

	@Override
	public boolean isUserEmailAddressUnique(String emailAddress) {
		
		if(userRepository.countByEmail(emailAddress) > 0) {
			return true;
		}
		
		return false;
	}

}
