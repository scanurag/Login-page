package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.Registration;
import com.example.demo.loginCredential.UserLogin;
import com.example.demo.service.UserServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@Controller
@CrossOrigin
@RequestMapping("api/v1/user")
public class UserRegistrationController {

    private static final Logger logger = LoggerFactory.getLogger(UserRegistrationController.class);

    @Autowired
    private UserServices us;

  
    @GetMapping("/save")
    public String saveUserPage() {
        return "save";
    }

    @GetMapping("/login")
    public String loginUserPage() {
        return "login";
    }

    @PostMapping(path = "/save")
    public ResponseEntity<String> saveUser(@RequestParam("email") String email, @RequestParam("password") String password) {
        try {
            Registration dto = new Registration();
            dto.setEmail(email);
            dto.setPassword(password);
            logger.info("Saving user with email: {}", email);
            String response = us.addUser(dto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error saving user", e);
            return new ResponseEntity<>("An error occurred while saving the user.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/login")
    public ResponseEntity<String> loginUser(@RequestParam("userEmail") String userEmail, @RequestParam("password") String password) {
        try {
            UserLogin loginDto = new UserLogin();
            loginDto.setUserEmail(userEmail);
            loginDto.setUserPassword(password);
            logger.info("Login attempt for user with email: {}", userEmail);
            boolean isValid = us.verifyLogin(loginDto);
            if (isValid) {
                return new ResponseEntity<>("Login successful!", HttpStatus.OK);
            } else {
                logger.warn("Invalid login attempt for user with email: {}", userEmail);
                return new ResponseEntity<>("Invalid email or password.", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            logger.error("Error verifying login", e);
            return new ResponseEntity<>("An error occurred while verifying login.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
