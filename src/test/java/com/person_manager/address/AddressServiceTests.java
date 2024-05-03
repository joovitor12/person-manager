package com.person_manager.address;

import com.person_manager.entities.Address;
import com.person_manager.entities.Person;
import com.person_manager.exceptions.ResourceNotFoundException;
import com.person_manager.repositories.PersonRepository;
import com.person_manager.services.AddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class AddressServiceTests {

    @InjectMocks
    AddressService addressService;

    @Mock
    PersonRepository personRepository;

    Address address;

    Person person;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        address = new Address("Outworld", true);
        person = new Person("Johnny Cage", "17/06/2002", "Earthrealm", "99999-999", "(99)99999-9999", "City", "State");

    }

    @Test
    public void testFindAll() {

        person.setAddressList(Arrays.asList(address));
        when(personRepository.findById(anyString())).thenReturn(Optional.of(person));

        List<Address> result = addressService.findAll("1");

        assertEquals(1, result.size());
        assertEquals(address, result.get(0));
    }

    @Test
    public void testSave() {
        person.setAddressList(new ArrayList<>(Arrays.asList(address)));
        when(personRepository.findById(anyString())).thenReturn(Optional.of(person));
        when(personRepository.save(any(Person.class))).thenReturn(person);

        List<Address> result = addressService.save(address, "1");

        assertEquals(1, result.size());
        assertEquals(address, result.get(0));
    }

    @Test
    public void testFindByName() {
        person.setAddressList(Arrays.asList(address));
        when(personRepository.findById(anyString())).thenReturn(Optional.of(person));

        Address result = addressService.findByName("Outworld", "1");

        assertEquals(address, result);
    }

    @Test
    public void testFindByNameNotFound() {
        when(personRepository.findById(anyString())).thenReturn(Optional.of(person));

        assertThrows(ResourceNotFoundException.class, () -> addressService.findByName("Home", "1"));
    }

    @Test
    public void testUpdate() {
        person.setAddressList(new ArrayList<>(Arrays.asList(address)));
        when(personRepository.findById(anyString())).thenReturn(Optional.of(person));
        when(personRepository.save(any(Person.class))).thenReturn(person);

        Address result = addressService.update(address, "Outworld", "1");

        assertEquals(address, result);
    }

    @Test
    public void testUpdateNotFound() {
        when(personRepository.findById(anyString())).thenReturn(Optional.of(person));

        assertThrows(ResourceNotFoundException.class, () -> addressService.update(new Address("Home", true), "Home", "1"));
    }


}
