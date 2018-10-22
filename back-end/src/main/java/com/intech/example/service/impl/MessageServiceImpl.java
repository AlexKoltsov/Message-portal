package com.intech.example.service.impl;

import com.intech.example.model.Message;
import com.intech.example.model.User;
import com.intech.example.repository.MessageRepository;
import com.intech.example.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Message save(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public void delete(Message message) {
        messageRepository.delete(message);
    }

    @Override
    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    @Override
    public Page<Message> findAll(Pageable pageable) {
        return messageRepository.findAll(pageable);
    }

    @Override
    public List<Message> findByUser(User user) {
        return messageRepository.findByUser(user);
    }
}
