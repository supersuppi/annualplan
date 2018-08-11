package com.gxh.apserver.service.interfaces;

import com.gxh.apserver.dto.HomeDTO;
import com.gxh.apserver.entity.UserContact;
import com.gxh.apserver.exceptions.ResourceNotFoundException;

public interface HomeService {
    public HomeDTO getUserHomeContent(String emailAddress) throws ResourceNotFoundException;
}
