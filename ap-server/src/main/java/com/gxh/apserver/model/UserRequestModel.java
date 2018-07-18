package com.gxh.apserver.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestModel {
	
    private Long id;

    private String email;

    private String password;

    private int active; 
    
    private Long roleId;
    
    private String firstName;
    
    private String lastName;
    
    private Long phone;

}
