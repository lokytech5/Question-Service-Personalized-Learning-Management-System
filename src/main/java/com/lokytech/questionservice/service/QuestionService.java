package com.lokytech.questionservice.service;

import com.lokytech.questionservice.client.UsersClient;
import com.lokytech.questionservice.dto.QuestionsDTO;
import com.lokytech.questionservice.dto.UsersDTO;
import com.lokytech.questionservice.entity.Questions;
import com.lokytech.questionservice.repository.QuestionRepository;
import com.lokytech.questionservice.validator.UserExistenceValidator;
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

    @Autowired
    UserExistenceValidator userExistenceValidator;

    public Questions saveQuestion(Questions questions, Long userId){
        UsersDTO user = userExistenceValidator.validateUserExists(userId);
        questions.setUserId(user.getId());
        return questionRepository.save(questions);
    }
}
