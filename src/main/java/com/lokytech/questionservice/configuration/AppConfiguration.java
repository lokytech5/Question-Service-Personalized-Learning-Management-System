package com.lokytech.questionservice.configuration;

import com.lokytech.questionservice.client.OpenAiClient;
import com.theokanning.openai.service.OpenAiService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Value("${openai.token}")
    private String openAiToken;

    @Bean
    public OpenAiService openAiService() {
        return new OpenAiService(openAiToken);
    }

}
