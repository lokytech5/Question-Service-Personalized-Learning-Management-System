package com.lokytech.questionservice.dto;

import java.util.List;

public class ChatCompletionRequestDTO {
    private String model;
    private List<ChatMessageDTO> messages;
    private int max_tokens;
    private double temperature;

    public ChatCompletionRequestDTO() {
    }

    public ChatCompletionRequestDTO(String model, List<ChatMessageDTO> messages, int max_tokens, double temperature) {
        this.model = model;
        this.messages = messages;
        this.max_tokens = max_tokens;
        this.temperature = temperature;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<ChatMessageDTO> getMessages() {
        return messages;
    }

    public void setMessages(List<ChatMessageDTO> messages) {
        this.messages = messages;
    }

    public int getMax_tokens() {
        return max_tokens;
    }

    public void setMax_tokens(int max_tokens) {
        this.max_tokens = max_tokens;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

}
