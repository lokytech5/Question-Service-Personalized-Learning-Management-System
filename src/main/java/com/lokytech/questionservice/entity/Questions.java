package com.lokytech.questionservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime timeStamp;
    private String topic;
    private String status;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    private List<Answers> answers;
//    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Answers> answers;

    public Questions() {
    }

    public Questions(Long questionId, Long userId, String content, LocalDateTime timeStamp, String topic, String status) {
        this.questionId = questionId;
        this.userId = userId;
        this.content = content;
        this.timeStamp = timeStamp;
        this.topic= topic;
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
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
