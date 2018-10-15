package com.intech.example.service;

import com.intech.example.model.Message;
import com.intech.example.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MessageService {

    Message save(Message message);

    void deleteById(Long id);

    List<Message> findAll();

    Page<Message> findAll(Pageable pageable);

    List<Message> findByUser(User user);
}
