package com.intech.example.service;

import com.intech.example.exception.MessagePortalException;
import com.intech.example.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User save(User user) throws MessagePortalException;

    void delete(User user);

    List<User> findAll();

    Optional<User> findById(Long id);

    Optional<User> findByLoginAndPassword(String login, String password);

    Optional<User> findByLogin(String login);
}
