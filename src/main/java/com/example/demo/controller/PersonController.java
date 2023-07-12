package com.example.demo.controller;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@CrossOrigin(origins="*")
public class PersonController {

    @Autowired
    PersonService personService;

    @PostMapping("/post/pessoas")
    public ResponseEntity<Object> saveNewPerson(@RequestBody Person person){
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.save(person));
    }

    @DeleteMapping("/delete/pessoas/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable(value="id") Integer id){
        Optional<Person>  optionalPerson = personService.findById(id);
        if(!optionalPerson.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada.");
        }
        personService.delete(optionalPerson.get());
        return ResponseEntity.status(HttpStatus.OK).body("Pessoa deleta com sucesso");
    }

    @PutMapping("/put/pessoas/{id}")
    public ResponseEntity<Object> updatePerson(@PathVariable(value="id")Integer id, @RequestBody Person person){
        Optional<Person> optionalPerson = personService.findById(id);
        if(!optionalPerson.isPresent() ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada");
        }
        Integer departmentId = person.getDepartment().getDepartmentId();
        if(departmentId == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Digite um departamento valido");
        }

        person.setPersonId(optionalPerson.get().getPersonId());
        return ResponseEntity.status(HttpStatus.OK).body(personService.save(person));
    }

    @GetMapping("/get/pessoas")
    public ResponseEntity<List<Object[]>> getAllPerson(){
        return ResponseEntity.status(HttpStatus.OK).body(personService.getAllPerson());
    }
}
