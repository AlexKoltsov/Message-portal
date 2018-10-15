package com.intech.example;

import com.intech.example.model.Message;
import com.intech.example.model.User;
import com.intech.example.repository.MessageRepository;
import com.intech.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DataLoader {

    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    @Autowired
    public DataLoader(UserRepository userRepository,
                      MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    @PostConstruct
    public void init() {
        List<User> users = getUsers();
        users.forEach(userRepository::save);

        List<Message> messages = getMessages(users);
        messages.forEach(messageRepository::save);

    }

    private List<Message> getMessages(List<User> users) {
        List<Message> messages = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Random random = new Random();
            Message message = new Message();
            message.setContent("content" + i);
            message.setSubject("subject" + i);
            message.setDateTime(LocalDateTime.now());
            if (random.nextBoolean()) {
                User user = users.get(random.nextInt(users.size()));
                message.setUser(user);
                message.setFrom(user);
                message.setTo(users.get(random.nextInt(users.size())));
            } else {
                User user = users.get(random.nextInt(users.size()));
                message.setUser(user);
                message.setTo(user);
                message.setFrom(users.get(random.nextInt(users.size())));
            }
            messages.add(message);
        }
        return messages;
    }

    private List<User> getUsers() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            users.add(new User("firstName" + i, "secondName" + i, "lastName" + i, "email" + i, "login" + i, "password" + i));
        }
        return users;
    }
}
