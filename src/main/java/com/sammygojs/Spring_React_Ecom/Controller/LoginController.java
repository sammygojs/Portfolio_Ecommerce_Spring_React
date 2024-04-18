package com.sammygojs.Spring_React_Ecom.Controller;
import com.sammygojs.Spring_React_Ecom.Dto.LoginRequest;
import com.sammygojs.Spring_React_Ecom.Entity.User;
import com.sammygojs.Spring_React_Ecom.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        // Find user by email
        User user = userRepository.findByEmail(request.getEmail());

        // Check if user exists and password matches
        if (user == null || !user.getPassword().equals(request.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }

        return ResponseEntity.ok("Login successful");
    }
}
