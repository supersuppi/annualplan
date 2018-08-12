package com.gxh.apserver.controller;

import com.gxh.apserver.dto.HomeDTO;
import com.gxh.apserver.entity.UserContact;
import com.gxh.apserver.service.interfaces.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController extends BaseController {

    @Autowired
    private HomeService homeService;

    @GetMapping(value="/details/{email}")
    public ResponseEntity<HomeDTO> homeDetails(@PathVariable("email") String emailAddress ) {

        HomeDTO homeDetails = homeService.getUserHomeContent(emailAddress);

        return new ResponseEntity<HomeDTO>(homeDetails, HttpStatus.OK);
    }
}