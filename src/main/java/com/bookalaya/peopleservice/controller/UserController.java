package com.bookalaya.peopleservice.controller;

import com.bookalaya.peopleservice.Service.UserService;
import com.bookalaya.peopleservice.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping(path = "/find/{id}")
    public User getUser(@PathVariable String id){
       return service.findUserById(id);

    }
    @GetMapping(path = "/findall")
    public List<User> getUser(){
        return service.findAll();

    }
    @PostMapping(path = "/create/")
    public User createUser(@RequestBody User user){
        return service.createUser(user);
    }



}
