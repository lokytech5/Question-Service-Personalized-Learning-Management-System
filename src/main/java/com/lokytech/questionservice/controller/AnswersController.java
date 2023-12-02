package com.lokytech.questionservice.controller;

import com.lokytech.questionservice.dto.AnswersDTO;
import com.lokytech.questionservice.entity.Answers;
import com.lokytech.questionservice.repository.AnswersRepository;
import com.lokytech.questionservice.service.AnswersService;
import com.lokytech.questionservice.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AnswersController {
    @Autowired
    AnswersService answersService;
    @Autowired
    QuestionService questionService;

    @Autowired
    AnswersRepository answersRepository;


    @PostMapping("/generate-answer/{questionId}")
    public ResponseEntity<Answers> generateAiAnswerForQuestion(@PathVariable Long questionId) {
        Answers savedAnswer = answersService.generateAndSaveAnswerForQuestionAndReturn(questionId);
        return new ResponseEntity<>(savedAnswer, HttpStatus.CREATED);
    }

    @GetMapping("/answers/{questionId}")
    public ResponseEntity<AnswersDTO> getAnswerForQuestion(@PathVariable Long questionId) {
        AnswersDTO answer = answersService.findAnswerByQuestionId(questionId);
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

}
