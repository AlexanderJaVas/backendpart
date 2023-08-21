package com.example.backendpart.repositories;

import com.example.backendpart.models.Chat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends MongoRepository<Chat, String> {
    void deleteByChatId(String chatId);

}
