package com.example.backendpart.controllers;

import com.example.backendpart.models.Person;
import com.example.backendpart.services.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@CrossOrigin
public class AuthenticationController {

    private final PersonService personService;

    public AuthenticationController(PersonService personService) {
        this.personService = personService;
    }

    @CrossOrigin
    @PostMapping("/authentication")
    public ResponseEntity<String> login(@RequestBody Map<String, String> payload) {
        Person personToValidate = new Person(payload.get("username"), payload.get("password"));
        switch (personService.findPerson(personToValidate)) {
            case -1 -> {
                return new ResponseEntity<>("No such user or username is incorrect.", HttpStatus.FORBIDDEN);
            }
            case 0 -> {
                return new ResponseEntity<>("Password is incorrect.", HttpStatus.FORBIDDEN);
            }
            case 1 -> {
                return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
            }
            case 2 -> {
                return new ResponseEntity<>("User not registered", HttpStatus.FORBIDDEN);
            }
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @CrossOrigin
    @PostMapping("/registration")
    public ResponseEntity<String> resultOfRegistration(@RequestBody Map<String, String> payload) {
        Person personToValidate = new Person(payload.get("signupUsername"), payload.get("signupPassword"));
        if (personService.register(personToValidate)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

}
