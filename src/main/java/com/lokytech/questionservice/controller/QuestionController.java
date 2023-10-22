package com.lokytech.questionservice.controller;

import com.lokytech.questionservice.dto.QuestionsDTO;
import com.lokytech.questionservice.entity.Questions;
import com.lokytech.questionservice.exception.UserNotFoundException;
import com.lokytech.questionservice.service.QuestionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @PostMapping("/question/{userId}")
    public ResponseEntity<Questions> createQuestions(@PathVariable Long userId, @Valid @RequestBody Questions questions){
        Questions savedQuestion = questionService.saveQuestion(questions, userId);
        return new ResponseEntity<>(savedQuestion, HttpStatus.CREATED);
    }

    @GetMapping("/question/{userId}")
    public ResponseEntity<?> findQuestionByUserId(@PathVariable Long userId){
        try{
            QuestionsDTO question = questionService.findQuestionById(userId);
            return new ResponseEntity<>(question, HttpStatus.OK);
        } catch (UserNotFoundException e){
            return  new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
