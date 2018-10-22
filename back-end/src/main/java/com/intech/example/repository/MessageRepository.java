package com.intech.example.repository;

import com.intech.example.model.Message;
import com.intech.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findByUser(User user);
}
