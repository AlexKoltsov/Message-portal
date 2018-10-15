package com.intech.example.service;

import com.intech.example.model.User;
import com.intech.example.model.UserSession;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface AuthenticationService {

    UserSession save(UserSession userSession);

    void delete(UserSession userSession);

    Optional<UserSession> findByUser(User user);
}
