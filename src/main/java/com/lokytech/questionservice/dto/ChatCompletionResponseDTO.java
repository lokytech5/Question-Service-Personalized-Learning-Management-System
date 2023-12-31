package com.lokytech.questionservice.dto;

import java.util.List;

public class ChatCompletionResponseDTO {
    private String id;
    private String object;
    private long created;
    private String model;
    private List<ChoiceResponseDTO> choices;
    private UsageResponseDTO usage;

    public ChatCompletionResponseDTO() {
    }

    public ChatCompletionResponseDTO(String id, String object, long created, String model, List<ChoiceResponseDTO> choices, UsageResponseDTO usage) {
        this.id = id;
        this.object = object;
        this.created = created;
        this.model = model;
        this.choices = choices;
        this.usage = usage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<ChoiceResponseDTO> getChoices() {
        return choices;
    }

    public void setChoices(List<ChoiceResponseDTO> choices) {
        this.choices = choices;
    }

    public UsageResponseDTO getUsage() {
        return usage;
    }

    public void setUsage(UsageResponseDTO usage) {
        this.usage = usage;
    }

    public static class ChoiceResponseDTO {
        private ChatMessageDTO message;
        private int index;
        private Object logprobs;
        private String finish_reason;

        public ChatMessageDTO getMessage() {
            return message;
        }

        public void setMessage(ChatMessageDTO message) {
            this.message = message;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public Object getLogprobs() {
            return logprobs;
        }

        public void setLogprobs(Object logprobs) {
            this.logprobs = logprobs;
        }

        public String getFinish_reason() {
            return finish_reason;
        }

        public void setFinish_reason(String finish_reason) {
            this.finish_reason = finish_reason;
        }
    }

    public static class UsageResponseDTO {
        private int prompt_tokens;
        private int completion_tokens;
        private int total_tokens;

        public int getPrompt_tokens() {
            return prompt_tokens;
        }

        public void setPrompt_tokens(int prompt_tokens) {
            this.prompt_tokens = prompt_tokens;
        }

        public int getCompletion_tokens() {
            return completion_tokens;
        }

        public void setCompletion_tokens(int completion_tokens) {
            this.completion_tokens = completion_tokens;
        }

        public int getTotal_tokens() {
            return total_tokens;
        }

        public void setTotal_tokens(int total_tokens) {
            this.total_tokens = total_tokens;
        }
    }
}
