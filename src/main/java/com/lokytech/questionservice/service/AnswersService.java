package com.lokytech.questionservice.service;

import com.lokytech.questionservice.entity.Answers;
import com.lokytech.questionservice.entity.Questions;
import com.lokytech.questionservice.enums.QuestionStatus;
import com.lokytech.questionservice.repository.AnswersRepository;
import com.lokytech.questionservice.repository.QuestionRepository;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AnswersService {

    private final QuestionRepository questionRepository;
    private final AnswersRepository answersRepository;
    private final OpenAiService openAiService;
    @Autowired
    private QuestionService questionService;

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


            question.setStatus(QuestionStatus.ANSWERED.toString());
            questionRepository.save(question);

            return question;

        } catch (Exception e){
            throw new RuntimeException("Error while processing the question with OpenAI", e);
        }

    }


    public Answers createAnswerForQuestion(Long questionId, String content) {
        Questions question = questionService.findQuestionEntityById(questionId);

        if (question == null) {
            // Handle error case where the question is not found.
        }

        Answers answer = new Answers();
        answer.setQuestion(question);
        answer.setContent(content);
        answer.setHumanGenerated(false);
        answer.setTimeStamp(LocalDateTime.now());

        return answersRepository.save(answer);
    }

}
