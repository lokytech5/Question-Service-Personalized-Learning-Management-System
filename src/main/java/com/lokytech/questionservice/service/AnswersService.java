package com.lokytech.questionservice.service;

import com.lokytech.questionservice.entity.Answers;
import com.lokytech.questionservice.entity.Questions;
import com.lokytech.questionservice.enums.QuestionStatus;
import com.lokytech.questionservice.repository.AnswersRepository;
import com.lokytech.questionservice.repository.QuestionRepository;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;
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

    public Questions postQuestionsAndFetchAnswers(Questions question){
        try{
            // Constructing request for OpenAI
            CompletionRequest completionRequest = CompletionRequest.builder()
                    .prompt(question.getContent())
                    .model("gpt-4")
                    .build();

            // Sending question to open ai
            CompletionResult completion = openAiService.createCompletion(completionRequest);

            // Processing respond
            String chatGptAnswer = completion.getChoices().get(0).getText();

            // Creating answer entity and saving it to database
            Answers answer = new Answers();
            answer.setContent(chatGptAnswer);
            answer.setQuestion(question);
            answersRepository.save(answer);

            return question;

        } catch (Exception e){
            throw new RuntimeException("Error while processing the question with OpenAI", e);
        }

    }
}