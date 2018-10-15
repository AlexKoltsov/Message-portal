package com.intech.example.service.impl;

import com.intech.example.model.User;
import com.intech.example.model.UserSession;
import com.intech.example.repository.AuthenticationRepository;
import com.intech.example.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private AuthenticationRepository authenticationRepository;

    @Autowired
    public AuthenticationServiceImpl(AuthenticationRepository authenticationRepository) {
        this.authenticationRepository = authenticationRepository;
    }

    @Override
    public UserSession save(UserSession userSession) {
        return authenticationRepository.save(userSession);
    }

    @Override
    public void delete(UserSession userSession) {
        authenticationRepository.delete(userSession);
    }

    @Override
    public Optional<UserSession> findByUser(User user) {
        return authenticationRepository.findByUser(user);
    }
}
