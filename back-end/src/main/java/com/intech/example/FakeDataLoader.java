package com.intech.example;

import com.intech.example.exception.MessagePortalException;
import com.intech.example.model.Message;
import com.intech.example.model.Role;
import com.intech.example.model.User;
import com.intech.example.repository.MessageRepository;
import com.intech.example.repository.RoleRepository;
import com.intech.example.repository.UserRepository;
import com.intech.example.service.MessageService;
import com.intech.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class FakeDataLoader {

    private final UserService userService;
    private final MessageService messageService;
    private final RoleRepository roleRepository;

    @Autowired
    public FakeDataLoader(UserService userService,
                          MessageService messageService,
                          RoleRepository roleRepository) {
        this.userService = userService;
        this.messageService = messageService;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void init() throws MessagePortalException {
        persistRoles();

        persistUsers();

        persistMessages();
    }

    private void persistMessages() throws MessagePortalException {
        for (int i = 0; i < 100; i++) {
            Random random = new Random();
            Message message = new Message();
            message.setContent("content" + i);
            message.setSubject("subject" + i);
            message.setDateTime(LocalDateTime.now());
            if (random.nextBoolean()) {
                User user = userService.findById((long) random.nextInt(5) + 1)
                        .orElseThrow(() -> new MessagePortalException("User not found"));
                message.setUser(user);
                message.setFrom(user);
                User user1 = userService.findById((long) random.nextInt(5) + 1)
                        .orElseThrow(() -> new MessagePortalException("User not found"));
                message.setTo(user1);
            } else {
                User user = userService.findById((long) random.nextInt(5) + 1)
                        .orElseThrow(() -> new MessagePortalException("User not found"));
                message.setUser(user);
                message.setTo(user);
                User user1 = userService.findById((long) random.nextInt(5) + 1)
                        .orElseThrow(() -> new MessagePortalException("User not found"));
                message.setFrom(
                        user1);
            }
            messageService.save(message);
        }
    }

    private void persistUsers() throws MessagePortalException {
        for (int i = 0; i < 5; i++) {
            User user = new User();
            user.setFirstName("firstName" + i);
            user.setSecondName("secondName" + i);
            user.setLastName("lastName" + i);
            user.setEmail("email" + i + "@gmail.com");
            user.setLogin("login" + i);
            user.setPassword("password" + i);
            if (new Random().nextBoolean()) {
                Role adminRole = roleRepository.findByRole("ADMIN")
                        .orElseThrow(() -> new MessagePortalException("Role not found"));
                user.addRole(adminRole);
            } else {
                Role userRole = roleRepository.findByRole("USER")
                        .orElseThrow(() -> new MessagePortalException("Role not found"));
                user.addRole(userRole);
            }
            userService.save(user);
        }
    }

    private void persistRoles() {
        Role user = new Role();
        user.setRole("USER");
        roleRepository.save(user);

        Role admin = new Role();
        admin.setRole("ADMIN");
        roleRepository.save(admin);
    }
}
