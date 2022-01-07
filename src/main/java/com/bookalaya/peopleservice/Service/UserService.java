package com.bookalaya.peopleservice.Service;

import com.bookalaya.peopleservice.entity.User;
import com.bookalaya.peopleservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User findUserById(String id){
        Optional<User> user =  userRepository.findById(id);
        return user.isPresent()?user.get():null;
    }

    public User createUser(User user){
        return userRepository.save(user);
    }
    public List<User> findAll(){
        return userRepository.findAll();
    }
}
