package com.example.backendpart.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Document(collection = "persons")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Id
    private ObjectId id;
    private String personId;
    private String username;
    private String password;
    @DocumentReference
    private List<Chat> chatIds;

    public Person(String username, String password) {
        this.personId = UUID.randomUUID().toString();
        this.username = username;
        this.password = password;
        this.chatIds = new ArrayList<>();
    }
}
