package com.sammygojs.Spring_React_Ecom.Controller;

import com.sammygojs.Spring_React_Ecom.Dto.SignupRequest;
import com.sammygojs.Spring_React_Ecom.Entity.User;
import com.sammygojs.Spring_React_Ecom.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SignupController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/hello1")
    public String hello1() {
        return "hello1";
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequest request) {
        // Validate email and password
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password and confirm password do not match");
        }

        // Check if email already exists
        if (userRepository.findByEmail(request.getEmail()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already exists");
        }

        // Create new user
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
    }
}
