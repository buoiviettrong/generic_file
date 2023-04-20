package com.nixagh.generic.controllers;

import com.nixagh.generic.models.UserModel;
import com.nixagh.generic.services.GenericService;
import com.nixagh.generic.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController extends GenericController<UserModel>{
    @Autowired
    public UserController(UserService userService) {
        super();
        this.service = userService;
    }
    @Override
    public GenericService<UserModel> getService() {
        return service;
    }
}
