package com.example.backendpart.controllers;

import com.example.backendpart.dto.ChatGptRequest;
import com.example.backendpart.dto.ChatGptResponse;
import com.example.backendpart.models.Person;
import com.example.backendpart.services.ChatService;
import com.example.backendpart.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/chats")
public class MainController {

    private final PersonService personService;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiUrl;

    @Autowired
    private RestTemplate template;

    @Autowired
    private ChatService chatService;


    public MainController(PersonService personService) {
        this.personService = personService;
    }

    @CrossOrigin
    @GetMapping("/all/{username}")
    public ResponseEntity<Person> getAllByUsername(@PathVariable String username) {
        return new ResponseEntity<>(personService.findAllByPersonUsername(username), HttpStatus.OK);
    }


    @CrossOrigin
    @PostMapping("/new/{username}")
    public void createChat(@RequestBody Map<String, String> payload, @PathVariable String username) {
        String question = payload.get("question");
        ChatGptRequest request = new ChatGptRequest(model, question);
        ChatGptResponse chatGptResponse = template.postForObject(apiUrl, request, ChatGptResponse.class);
        chatService.addChatToPerson(username, question, chatGptResponse);
    }

    @CrossOrigin
    @GetMapping("/delete/{id}")
    public void deleteChatById(@PathVariable("id") String chatIdToDelete) {
        chatService.deleteChatByChatId(chatIdToDelete);
    }
}