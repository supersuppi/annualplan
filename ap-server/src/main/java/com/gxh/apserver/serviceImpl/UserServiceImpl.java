package com.gxh.apserver.serviceImpl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gxh.apserver.entity.Role;
import com.gxh.apserver.entity.User;
import com.gxh.apserver.entity.UserContact;
import com.gxh.apserver.exceptions.EmailAlreadyExistException;
import com.gxh.apserver.model.UserRequestModel;
import com.gxh.apserver.repository.interfaces.RolesRepository;
import com.gxh.apserver.repository.interfaces.UserContactsRepository;
import com.gxh.apserver.repository.interfaces.UserRepository;
import com.gxh.apserver.service.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private RolesRepository rolesRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserContactsRepository userContactsRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public Collection<Role> getAllRoles() {
		return rolesRepository.findAll();
	}
	
	@Override
	public UserContact getUserFromRequestBody(UserRequestModel requestBody) {
		
		Role role = new Role();
		role.setId(requestBody.getRoleId());
		
		User user = new User();
		user.setEmail(requestBody.getEmail());
		user.setPassword(passwordEncoder.encode("Test@123"));
		user.setRole(role);
		
		UserContact userContact = new UserContact();
		userContact.setFirstName(requestBody.getFirstName());
		userContact.setLastName(requestBody.getLastName());
		userContact.setPhone(requestBody.getPhone());
		userContact.setUser(user);
		
		return userContact;
	}
	
	public void registerUser(UserContact userContact) throws EmailAlreadyExistException {
		
		if(isUserEmailAddressUnique(userContact.getUser().getEmail())) {
			throw new EmailAlreadyExistException("Email address already exist");
		}
		
		userContactsRepository.save(userContact);
	}

	@Override
	public boolean isUserEmailAddressUnique(String emailAddress) {
		
		if(userRepository.countByEmail(emailAddress) > 0) {
			return true;
		}
		
		return false;
	}

}
