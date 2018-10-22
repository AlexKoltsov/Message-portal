package com.intech.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String subject;

    private String content;

    @OneToOne
    @JoinColumn(name = "from_user")
    private User from;

    @OneToOne
    @JoinColumn(name = "to_user")
    private User to;

    private LocalDateTime dateTime;

    public Message() {
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public User getTo() {
        return to;
    }

    public void setTo(User to) {
        this.to = to;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(id, message.id) &&
                Objects.equals(user, message.user) &&
                Objects.equals(subject, message.subject) &&
                Objects.equals(content, message.content) &&
                Objects.equals(from, message.from) &&
                Objects.equals(to, message.to) &&
                Objects.equals(dateTime, message.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, subject, content, from, to, dateTime);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", user=" + user +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", from=" + from +
                ", to=" + to +
                ", dateTime=" + dateTime +
                '}';
    }
}