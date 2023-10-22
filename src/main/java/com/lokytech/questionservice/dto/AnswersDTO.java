package com.lokytech.questionservice.dto;

import com.lokytech.questionservice.entity.Questions;

import java.time.LocalDateTime;

public class AnswersDTO {
    private QuestionsDTO questions;
    private String content;
    private Boolean isHumanGenerated;
    private LocalDateTime timeStamp;
    private String AnsweredBy;

    public QuestionsDTO getQuestions() {
        return questions;
    }

    public void setQuestions(QuestionsDTO question) {
        this.questions = question;
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
