package com.gxh.apserver.service.interfaces;

import com.gxh.apserver.dto.HomeDTO;
import com.gxh.apserver.entity.UserContact;
import com.gxh.apserver.exceptions.ResourceNotFoundException;

import java.text.ParseException;

public interface HomeService {
    public HomeDTO getUserHomeContent(String emailAddress) throws ResourceNotFoundException,ParseException;
}
