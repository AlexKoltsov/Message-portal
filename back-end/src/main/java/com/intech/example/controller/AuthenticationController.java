package com.intech.example.controller;

import com.intech.example.exception.MessagePortalException;
import com.intech.example.model.User;
import com.intech.example.model.UserSession;
import com.intech.example.service.AuthenticationService;
import com.intech.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "http://localhost:4200", maxAge = 3600)
public class AuthenticationController {

    private UserService userService;
    private AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(UserService userService,
                                    AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<User> login(@RequestBody User user) throws MessagePortalException {
        User foundUser = userService.findByLoginAndPassword(user.getLogin(), user.getPassword())
                .orElseThrow(() -> new MessagePortalException(String.format("Username %s or password is incorrect", user.getLogin())));
//        UserSession userSession = authenticationService.findByUser(foundUser)
//                .orElseGet(() -> {
//                    UserSession session = new UserSession(foundUser);
//                    authenticationService.save(session);
//                    return session;
//                });

        return ResponseEntity.ok(foundUser);
    }

    // TODO: doesn't work
    @PostMapping("/logout")
    @ResponseBody
    public ResponseEntity<?> logout(@RequestBody User user) {
        authenticationService.findByUser(user).ifPresent(userSession -> authenticationService.delete(userSession));
        return ResponseEntity.ok().build();
    }

    // TODO: check that user with new password saved
    @PostMapping("/changePassword")
    @ResponseBody
    public ResponseEntity<User> changePassword(@RequestBody User user) throws MessagePortalException {
        User foundUser = userService.findByLogin(user.getLogin())
                .orElseThrow(() -> new MessagePortalException(String.format("User %s not found", user.getLogin())));

        foundUser.setPassword(user.getPassword());
        userService.save(foundUser);

        return ResponseEntity.ok(foundUser);
    }
}
