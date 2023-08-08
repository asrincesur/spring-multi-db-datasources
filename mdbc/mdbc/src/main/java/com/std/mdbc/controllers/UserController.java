package com.std.mdbc.controllers;


import com.std.mdbc.business.abstracts.UserService;
import com.std.mdbc.models.IBaseEntity;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/v1/get-users")
    public List<IBaseEntity> getUsers() {
        return userService.getAll();
    }

}
