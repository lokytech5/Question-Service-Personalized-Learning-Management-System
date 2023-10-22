package com.lokytech.questionservice.client;

import com.lokytech.questionservice.dto.ChatCompletionRequestDTO;
import com.lokytech.questionservice.dto.CompletionResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "openai", url= "${openai.endpoint}")
public interface OpenAiClient {
    @PostMapping(consumes = "application/json")
    CompletionResponseDTO fetchAnswerFromAi(ChatCompletionRequestDTO requestBody, @RequestHeader("Authorization") String authHeader);
}
