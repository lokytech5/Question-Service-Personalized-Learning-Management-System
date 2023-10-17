package com.lokytech.questionservice.service;

import com.lokytech.questionservice.client.UsersClient;
import com.lokytech.questionservice.dto.QuestionsDTO;
import com.lokytech.questionservice.dto.UsersDTO;
import com.lokytech.questionservice.entity.Questions;
import com.lokytech.questionservice.exception.UserNotFoundException;
import com.lokytech.questionservice.repository.QuestionRepository;
import com.lokytech.questionservice.validator.UserExistenceValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public QuestionsDTO findQuestionById(Long userId){
        UsersDTO users = usersClient.getUserById(userId);
        Questions questions = questionRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User Not Found with id" + userId));
        return mapper.map(questions, QuestionsDTO.class);
    }

    public Questions findQuestionEntityById(Long userId){
        return questionRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User Not Found with id" + userId));
    }
}
