package com.lokytech.questionservice.service;

import com.lokytech.questionservice.repository.AnswersRepository;
import com.lokytech.questionservice.repository.QuestionRepository;
import com.theokanning.openai.service.OpenAiService;

public class AnswersService {

    private final QuestionRepository questionRepository;
    private final AnswersRepository answersRepository;
    private final OpenAiService openAiService;

    public AnswersService(QuestionRepository questionRepository, AnswersRepository answersRepository, OpenAiService openAiService) {
        this.questionRepository = questionRepository;
        this.answersRepository = answersRepository;
        this.openAiService = openAiService;
    }


}
