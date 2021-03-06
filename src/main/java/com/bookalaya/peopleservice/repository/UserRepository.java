package com.bookalaya.peopleservice.repository;

import com.bookalaya.peopleservice.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    public List<User> findByNameAndCollege(String name, String college);
}
