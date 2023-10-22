package com.lokytech.questionservice.service;

import com.lokytech.questionservice.client.OpenAiClient;
import com.lokytech.questionservice.dto.*;
import com.lokytech.questionservice.entity.Answers;
import com.lokytech.questionservice.entity.Questions;
import com.lokytech.questionservice.enums.QuestionStatus;
import com.lokytech.questionservice.exception.ResourceNotFoundException;
import com.lokytech.questionservice.repository.AnswersRepository;
import com.lokytech.questionservice.repository.QuestionRepository;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;
import com.theokanning.openai.service.OpenAiService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnswersService {

    private final QuestionRepository questionRepository;
    private final AnswersRepository answersRepository;
    private final OpenAiService openAiService;
    @Autowired
    private QuestionService questionService;
    @Value("${openai.token}")
    private String openAiToken;
    @Autowired
    private OpenAiClient openAiClient;

    @Autowired
    private ModelMapper modelMapper;

    public AnswersService(QuestionRepository questionRepository, AnswersRepository answersRepository, OpenAiService openAiService) {
        this.questionRepository = questionRepository;
        this.answersRepository = answersRepository;
        this.openAiService = openAiService;
    }

    public Questions generateAndSaveAnswerForQuestion(Questions question){
        try{
            // Fetching answer from the Chat API
            String chatGptAnswer = fetchAnswerFromAI(question.getContent());

            // Creating answer entity and saving it to database
            Answers answer = new Answers();
            answer.setContent(chatGptAnswer);
            answer.setQuestion(question);
            answersRepository.save(answer);


            question.setStatus(QuestionStatus.ANSWERED);
            questionRepository.save(question);

            return question;

        } catch (Exception e){
            throw new RuntimeException("Error while processing the question with OpenAI", e);
        }

    }


    public Answers createAnswerForQuestion(Long questionId, String content) {
        Optional<Questions> optionalQuestion = questionService.findQuestionEntityById(questionId);

        if (!optionalQuestion.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Question not found with id: " + questionId);
        }

        Questions question = optionalQuestion.get();

        Answers answer = new Answers();
        answer.setQuestion(question);
        answer.setContent(content);
        answer.setHumanGenerated(false);
        answer.setTimeStamp(LocalDateTime.now());

        answer.setAnsweredBy("OpenAI Assistant");

        return answersRepository.save(answer);
    }

    public String fetchAnswerFromAI(String questionContent) {
        ChatCompletionRequestDTO request = new ChatCompletionRequestDTO();
        request.setModel("gpt-3.5-turbo");

        List<ChatMessageDTO> messages = new ArrayList<>();
        messages.add(new ChatMessageDTO("system", "You are a helpful assistant."));
        messages.add(new ChatMessageDTO("user", questionContent));

        request.setMessages(messages);
        request.setMax_tokens(50);
        request.setTemperature(0.7);

        ChatCompletionResponseDTO response = openAiClient.fetchAnswerFromAi(request, "Bearer " + openAiToken);
        return response.getChoices().get(0).getMessage().getContent();
    }
    public QuestionsDTO toQuestionDTO(Questions question){
        return modelMapper.map(question, QuestionsDTO.class);
    }

    public AnswersDTO toDTO(Answers answer){
        AnswersDTO answersDTO = modelMapper.map(answer, AnswersDTO.class);
        answersDTO.setQuestions(toQuestionDTO(answer.getQuestion()));
        return answersDTO;
    }

    public AnswersDTO findAnswerByQuestionId(Long questionId){
        Answers answer = answersRepository.findByQuestionQuestionId(questionId)
                .orElseThrow(() -> new ResourceNotFoundException("Answer not found for question id: " + questionId));
        return toDTO(answer);
    }
}
