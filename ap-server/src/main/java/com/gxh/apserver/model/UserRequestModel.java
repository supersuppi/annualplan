package com.gxh.apserver.model;

import com.gxh.apserver.entity.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestModel {
	
    private Long id;

    private String email;

    private String password;

    private int active; 
    
    private Role role;
    
    private String firstName;
    
    private String lastName;

}
