package com.intech.example.service.impl;

import com.intech.example.exception.MessagePortalException;
import com.intech.example.model.User;
import com.intech.example.repository.RoleRepository;
import com.intech.example.repository.UserRepository;
import com.intech.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User save(User user) throws MessagePortalException {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.addRole(roleRepository.findByRole("USER")
                .orElseThrow(() -> new MessagePortalException("USER role not found")));
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) {
        return userRepository.findByLoginAndPassword(login, bCryptPasswordEncoder.encode(password));
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return userRepository.findByLogin(login);
    }
}
