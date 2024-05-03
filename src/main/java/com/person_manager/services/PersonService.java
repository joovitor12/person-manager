package com.person_manager.services;

import com.person_manager.entities.Person;
import com.person_manager.exceptions.ResourceNotFoundException;
import com.person_manager.repositories.PersonRepository;
import com.person_manager.utils.person.PersonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonService {

    private Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository repository;

    public List<Person> findAll(){
        logger.info("Finding all people!");

        return repository.findAll();
    }

    public Person create(Person person) {

        logger.info("Creating one person!");

        PersonUtils.validateDate(person);

        return repository.save(person);
    }

    public Person update(Person person, String id){
        logger.info("Updating one person!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        PersonUtils.setProperties(entity, person);

        return repository.save(entity);
    }

    public Person findById(String id) {

        logger.info("Finding one person!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        return entity;
    }


}
