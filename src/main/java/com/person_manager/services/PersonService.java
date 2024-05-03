package com.person_manager.services;

import com.person_manager.entities.Person;
import com.person_manager.exceptions.ResourceNotFoundException;
import com.person_manager.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    public Person create(Person person){

        logger.info("Creating one person!");

        // Formatar a data de nascimento para o formato desejado
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date birthDate = inputFormat.parse(person.getBirthDate());
            person.setBirthDate(outputFormat.format(birthDate));
        } catch (ParseException e) {
            // Tratar erro de formatação da data, se necessário
            e.printStackTrace();
        }

        return repository.save(person);
    }

    public Person update(Person person){
        logger.info("Updating one person!");

        var entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setFullName(person.getFullName());

        return repository.save(entity);
    }

    public Person findById(String id) {

        logger.info("Finding one person!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        return entity;
    }


}
