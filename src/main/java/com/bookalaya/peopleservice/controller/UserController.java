package com.bookalaya.peopleservice.controller;

import com.bookalaya.peopleservice.Service.UserService;
import com.bookalaya.peopleservice.annotations.LogExecutionTime;
import com.bookalaya.peopleservice.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.sql.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserController {


    final UserService service;

    ExecutorService ex = Executors.newCachedThreadPool();

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



    @LogExecutionTime
    @PostMapping(path="/generateDummyConcurrently/{size}")
    public List<User> generateDummyUser(@RequestBody User user, @PathVariable int size){
        long start = System. currentTimeMillis();
        List<User> users= new ArrayList<>();
        List<Callable<User>> userTask = new ArrayList<>();
        for(int i=0;i<500;i++){
            userTask.add(()->{

                return service.createUser(user);

            });

        }

        Thread th = new Thread(()->{
            service.createUser(user);
        });


        try {
            List<Future<User>> userListAdded = new ArrayList<>();

            userTask.stream().forEach(userCallable -> {
                userListAdded.add(ex.submit(userCallable));

            });

            //List<Future<User>> userListAdded = ex.invokeAll(userTask);
            users.addAll(handleFutureResult(userListAdded));

        } finally {
            ex.shutdown();
        }
        long end = System. currentTimeMillis();

    return users;
    }

    private List<User> handleFutureResult(List<Future<User>> userFuture){
        return userFuture.parallelStream().map((userFuture1 -> {
            try {
                return userFuture1.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            } catch (ExecutionException e) {
                e.printStackTrace();
                return null;
            }
        })).collect(Collectors.toList());

    };



}
