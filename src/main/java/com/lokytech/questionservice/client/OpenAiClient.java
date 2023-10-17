package com.lokytech.questionservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "openai", url= "${openai.endpoint}")
public interface OpenAiClient {
    @PostMapping( value = "/completions", consumes = "application/json")
    String fetchAnswerFromAi(@RequestBody String requestBody, @RequestHeader("Authorization") String authHeader);
}
