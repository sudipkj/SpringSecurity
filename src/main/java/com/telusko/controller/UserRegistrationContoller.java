package com.telusko.controller;

import com.telusko.model.Users;
import com.telusko.service.MyRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRegistrationContoller {

    @Autowired
    private MyRegistrationService myRegistrationService;


    @PostMapping("/register-user")
    public Users registerUser(@RequestBody Users user) {
        myRegistrationService.registerUser(user);
        return user;
    }
}
