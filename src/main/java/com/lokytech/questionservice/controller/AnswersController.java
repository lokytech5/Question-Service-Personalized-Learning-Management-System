package com.lokytech.questionservice.controller;

import com.lokytech.questionservice.entity.Answers;
import com.lokytech.questionservice.entity.Questions;
import com.lokytech.questionservice.exception.ResourceNotFoundException;
import com.lokytech.questionservice.service.AnswersService;
import com.lokytech.questionservice.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


public class AnswersController {
    @Autowired
    AnswersService answersService;
    @Autowired
    QuestionService questionService;

    @PostMapping("/Answers/{questionId}")
    public ResponseEntity<Answers> createAnswer(@PathVariable Long questionId){
        Questions questions = questionService.findQuestionEntityById(questionId);

        if (questions == null) {
            // Handle the case where the question is not found.
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return null;
    }
}
