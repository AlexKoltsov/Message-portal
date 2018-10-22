package com.intech.example.controller;

import com.intech.example.exception.MessagePortalException;
import com.intech.example.model.Message;
import com.intech.example.model.User;
import com.intech.example.service.MessageService;
import com.intech.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:4200", maxAge = 3600)
public class MessageController {

    private final UserService userService;
    private final MessageService messageService;

    @Autowired
    public MessageController(UserService userService,
                             MessageService messageService) {
        this.userService = userService;
        this.messageService = messageService;
    }

    @GetMapping("/messages")
    public List<Message> listMessages() {
        return messageService.findAll();
    }

//    @GetMapping(value = "/messages", params = {"page", "size"})
//    public ResponseEntity<List<Message>> listMessagesPageable(
//            @RequestParam("page") int page,
//            @RequestParam("size") int size) {
//        Page<Message> resultPage = messageService.findAll(PageRequest.of(page, size));
//        if (page > resultPage.getTotalPages()) {
//            throw new ResourceNotFoundException();
//        }
//        return ResponseEntity.ok(resultPage.getContent());
//    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<List<Message>> listMessagesByUserId(@PathVariable("id") Long userId) throws MessagePortalException {
        User user = userService.findById(userId).orElseThrow(() -> new MessagePortalException(String.format("User with %s not found", userId)));
        return ResponseEntity.ok(messageService.findByUser(user));
    }

    @DeleteMapping("/messages")
    public void deleteMessage(@RequestBody Message message) {
        messageService.delete(message);
    }
}
