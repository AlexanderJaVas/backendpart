package com.example.backendpart.services;

import com.example.backendpart.models.Person;
import com.example.backendpart.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public int findPerson(Person person) {

        if (findPersonByUsername(person.getUsername()) == null) {
            return -1;
        } else if (findPersonByUsername(person.getUsername()).getPassword().equals(person.getPassword())) {
            return 1;
        } else
            return 2;
    }

    private Person findPersonByUsername(String username) {
        Optional<Person> personDb = personRepository.findByUsername(username);
        return personDb.orElse(null);

    }

    public boolean register(Person person) {
        Optional<Person> personDb = personRepository.findByUsername(person.getUsername());
        if (personDb.isEmpty()) {
            personRepository.save(person);
            return true;
        } else return false;
    }

    public Person findAllByPersonUsername(String username) {
        Optional<Person> personDb = personRepository.findByUsername(username);
        return personDb.orElse(new Person());
    }
}