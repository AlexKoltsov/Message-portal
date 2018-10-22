package com.intech.example.controller;

import com.intech.example.exception.MessagePortalException;
import com.intech.example.model.User;
import com.intech.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(value = "http://localhost:4200", maxAge = 3600)
@PreAuthorize("hasRole('ADMIN')")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable("id") Long userId) throws MessagePortalException {
        return userService.findById(userId)
                .orElseThrow(() -> new MessagePortalException(String.format("User with id=%s not found", userId)));
    }

    @PostMapping("/users")
    public User registerUser(@RequestBody User user) throws MessagePortalException {
        Optional<User> byLogin = userService.findByLogin(user.getLogin());
        if (byLogin.isPresent()) {
            throw new MessagePortalException(String.format("Username %s has been already registered", user.getLogin()));
        }

        return userService.save(user);
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User user) throws MessagePortalException {
        return userService.save(user);
    }

    @DeleteMapping("/users")
    public void deleteUser(@RequestBody User user) {
        userService.delete(user);
    }
}