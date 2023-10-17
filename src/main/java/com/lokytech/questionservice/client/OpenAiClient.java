package com.lokytech.questionservice.client;

import com.lokytech.questionservice.dto.CompletionRequestDTO;
import com.lokytech.questionservice.dto.CompletionResponseDTO;
import com.theokanning.openai.completion.CompletionRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "openai", url= "${openai.endpoint}")
public interface OpenAiClient {
    @PostMapping( value = "/completions", consumes = "application/json")
    CompletionResponseDTO fetchAnswerFromAi(CompletionRequestDTO requestBody, @RequestHeader("Authorization") String authHeader);
}
