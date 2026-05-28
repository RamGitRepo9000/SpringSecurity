package com.Security.Securitytest.controller;

import com.Security.Securitytest.Entity.User;
import com.Security.Securitytest.Repositories.UserRepository;
import com.Security.Securitytest.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User saveNewUser(@RequestBody  User user){
        return userService.saveNewUser(user);
    }
}
