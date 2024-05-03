package com.person_manager.person;
import com.person_manager.entities.Person;
import com.person_manager.exceptions.ResourceNotFoundException;
import com.person_manager.repositories.PersonRepository;
import com.person_manager.services.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class PersonServiceTests {

    @InjectMocks
    PersonService personService;

    @Mock
    PersonRepository personRepository;

    Person person;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);

        person = new Person("Johnny Cage", "17/06/2002", "Earthrealm", "99999-999", "(99)99999-9999", "City", "State");
    }

    @Test
    public void testFindAll() {
        when(personRepository.findAll()).thenReturn(Arrays.asList(person));

        List<Person> result = personService.findAll();

        assertEquals(1, result.size());
        assertEquals(person, result.get(0));
    }

    @Test
    public void testCreate() {
        when(personRepository.save(any(Person.class))).thenReturn(person);

        Person result = personService.create(person);

        assertEquals(person, result);
    }

    @Test
    public void testUpdate() {
        when(personRepository.findById(anyString())).thenReturn(Optional.of(person));
        when(personRepository.save(any(Person.class))).thenReturn(person);

        Person result = personService.update(person, "1");

        assertEquals(person, result);
    }

    @Test
    public void testUpdateNotFound() {
        when(personRepository.findById(anyString())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> personService.update(person, "1"));
    }

    @Test
    public void testFindById() {
        when(personRepository.findById(anyString())).thenReturn(Optional.of(person));

        Person result = personService.findById("1");

        assertEquals(person, result);
    }

    @Test
    public void testFindByIdNotFound() {
        when(personRepository.findById(anyString())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> personService.findById("1"));
    }
}
