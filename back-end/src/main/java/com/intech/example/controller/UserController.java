package com.intech.example.controller;

import com.intech.example.exception.MessagePortalException;
import com.intech.example.model.User;
import com.intech.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(value = "http://localhost:4200", maxAge = 3600)
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> users() {
        return userService.findAll();
    }

    @PostMapping("/users")
    public User registerUser(@RequestBody User user) throws MessagePortalException {
        Optional<User> byLogin = userService.findByLogin(user.getLogin());
        if (byLogin.isPresent()) {
            throw new MessagePortalException(String.format("Username %s has been already registered", user.getLogin()));
        }

        return userService.save(user);
    }
}
