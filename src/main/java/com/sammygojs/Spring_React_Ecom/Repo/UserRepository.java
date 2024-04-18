package com.sammygojs.Spring_React_Ecom.Repo;

import com.sammygojs.Spring_React_Ecom.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);
}
