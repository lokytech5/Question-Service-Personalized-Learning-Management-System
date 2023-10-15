package com.lokytech.questionservice.service;

import com.lokytech.questionservice.client.UsersClient;
import com.lokytech.questionservice.dto.QuestionsDTO;
import com.lokytech.questionservice.entity.Questions;
import com.lokytech.questionservice.repository.QuestionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    UsersClient usersClient;

    @Autowired
    ModelMapper mapper;

    public Questions saveQuestion(Questions questions, Long userId){
        Questions savedQuestions = questionRepository.save(questions);
        return savedQuestions;
    }
}
