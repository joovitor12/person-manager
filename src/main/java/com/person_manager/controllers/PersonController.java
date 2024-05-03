package com.person_manager.controllers;

import com.person_manager.entities.Address;
import com.person_manager.entities.Person;
import com.person_manager.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/person/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonController {

    @Autowired
    PersonService service;


    @GetMapping()
    public ResponseEntity<List<Person>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @PostMapping()
    public ResponseEntity<Person> save(@RequestBody Person person){
        return ResponseEntity.ok().body(service.create(person));
    }

    @GetMapping(path = "/find")
    public ResponseEntity<Person> findById(@RequestParam String id){
        return ResponseEntity.ok().body(service.findById(id));
    }
}
