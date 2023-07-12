package com.example.demo.service;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PersonService {
    @Autowired
    PersonRepository personRepository;

    public Optional<Person> findById(Integer id){
        return  personRepository.findById(id);
    }

    @Transactional
    public void delete(Person person){
        personRepository.delete(person);
    }

    @Transactional
    public Person save(Person person){
        return personRepository.save(person);
    }

    public List<Object[]> getAllPerson(){
        return personRepository.getAllPerson();
    }
}
