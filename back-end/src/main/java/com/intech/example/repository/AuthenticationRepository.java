package com.intech.example.repository;

import com.intech.example.model.User;
import com.intech.example.model.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthenticationRepository extends JpaRepository<UserSession, Long> {

    Optional<UserSession> findByUser(User user);
}
