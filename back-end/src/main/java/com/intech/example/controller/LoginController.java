package com.intech.example.controller;

import com.intech.example.exception.MessagePortalException;
import com.intech.example.model.Message;
import com.intech.example.model.User;
import com.intech.example.service.MessageService;
import com.intech.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RestController
public class LoginController {

    private final UserService userService;
    private final MessageService messageService;

    @Autowired
    public LoginController(UserService userService,
                           MessageService messageService) {
        this.userService = userService;
        this.messageService = messageService;
    }

    @GetMapping(value = {"/", "/login"})
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping(value = "/registration")
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @PostMapping(value = "/registration")
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) throws MessagePortalException {
        ModelAndView modelAndView = new ModelAndView();
        userService.findByLogin(user.getLogin()).ifPresent(foundUser -> bindingResult.rejectValue("login",
                "error.user",
                "There is already a user registered with the login provided")
        );
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            userService.save(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registration");
        }
        return modelAndView;
    }

    @GetMapping(value = "/home")
    public ModelAndView home() throws MessagePortalException {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByLogin(auth.getName())
                .orElseThrow(() -> new MessagePortalException("User not found"));

        user.getRoles().stream()
                .filter(role -> role.getRole().equals("ADMIN"))
                .findAny()
                .ifPresent((role) -> modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role"));

        List<Message> messages = messageService.findByUser(user);
        modelAndView.addObject("messages", messages);

        modelAndView.setViewName("home");
        return modelAndView;
    }
}
