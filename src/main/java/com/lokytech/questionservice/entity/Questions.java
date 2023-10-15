package com.lokytech.questionservice.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Questions {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long questionId;
    private Long userId;
    private String content;
    private LocalDateTime timeStamp;
    private String Topic;
    private String status;
//    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Answers> answers;

    public Questions() {
    }

    public Questions(Long questionId, Long userId, String content, LocalDateTime timeStamp, String topic, String status) {
        this.questionId = questionId;
        this.userId = userId;
        this.content = content;
        this.timeStamp = timeStamp;
        Topic = topic;
        this.status = status;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getTopic() {
        return Topic;
    }

    public void setTopic(String topic) {
        Topic = topic;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
