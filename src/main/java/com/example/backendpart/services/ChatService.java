package com.example.backendpart.services;

import com.example.backendpart.dto.ChatGptResponse;
import com.example.backendpart.models.Chat;
import com.example.backendpart.models.Person;
import com.example.backendpart.repositories.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public void addChatToPerson(String username, String question, ChatGptResponse chatGptResponse) {
        String answer = chatGptResponse.getChoices().get(0).getMessage().getContent();
        String chatId = chatGptResponse.getId();
        Chat chat = chatRepository.insert(new Chat(chatId, question, answer));
        mongoTemplate.update(Person.class)
                .matching(Criteria.where("username").is(username))
                .apply(new Update().push("chatIds").value(chat))
                .first();
    }

    public void deleteChatByChatId(String chatIdToDelete) {
        chatRepository.deleteByChatId(chatIdToDelete);

    }
}

