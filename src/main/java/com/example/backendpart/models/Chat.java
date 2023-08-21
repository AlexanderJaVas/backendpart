package com.example.backendpart.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Document(collection = "chats")
@Data
@NoArgsConstructor
public class Chat {

    @Id
    private ObjectId id;
    private String chatId;
    private LocalDateTime dateTime;
    private String question;
    private String answer;

    public Chat(String chatId, String question, String answer) {
        this.dateTime = LocalDateTime.now();
        this.chatId = chatId;
        this.question = question;
        this.answer = answer;
    }

}
