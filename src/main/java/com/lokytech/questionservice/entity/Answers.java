package com.lokytech.questionservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Entity
public class Answers {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long answerId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "questionId", nullable = false)
    private Questions question;
    @NotBlank(message = "content can not be blank")
    private String content;
    private Boolean isHumanGenerated;
    private LocalDateTime timeStamp;
    private String AnsweredBy;

    public Answers() {
    }

    public Answers(Long answerId, Questions question, String content, Boolean isHumanGenerated, LocalDateTime timeStamp, String answeredBy) {
        this.answerId = answerId;
        this.question = question;
        this.content = content;
        this.isHumanGenerated = isHumanGenerated;
        this.timeStamp = timeStamp;
        AnsweredBy = answeredBy;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public Questions getQuestion() {
        return question;
    }

    public void setQuestion(Questions question) {
        this.question = question;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getHumanGenerated() {
        return isHumanGenerated;
    }

    public void setHumanGenerated(Boolean humanGenerated) {
        isHumanGenerated = humanGenerated;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getAnsweredBy() {
        return AnsweredBy;
    }

    public void setAnsweredBy(String answeredBy) {
        AnsweredBy = answeredBy;
    }
}
