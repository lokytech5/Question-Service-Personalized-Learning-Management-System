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
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AnswersController {
    @Autowired
    AnswersService answersService;
    @Autowired
    QuestionService questionService;

    @PostMapping("/generate-answer/{questionId}")
    public ResponseEntity<Answers> generateAiAnswerForQuestion(@PathVariable Long questionId){
        // This will throw an exception if the question doesn't exist, which will be caught by my ControllerAdvice and return a 404 status.
        Questions questions = questionService.findQuestionEntityById(questionId)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found with id: " + questionId));

        String aiAnswerContent = answersService.fetchAnswerFromAI(questions.getContent());
        Answers savedAnswer = answersService.createAnswerForQuestion(questionId, aiAnswerContent);

        return new ResponseEntity<>(savedAnswer, HttpStatus.CREATED);
    }
}
