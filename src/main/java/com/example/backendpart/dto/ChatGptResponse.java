package com.example.backendpart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatGptResponse {

    private List<Choice> choices;
    @Getter
    private String id;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Choice {

        private Message message;
    }

}
