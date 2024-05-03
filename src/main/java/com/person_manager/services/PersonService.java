package com.person_manager.services;

import com.person_manager.entities.Address;
import com.person_manager.entities.Person;
import com.person_manager.exceptions.ResourceNotFoundException;
import com.person_manager.repositories.PersonRepository;
import com.person_manager.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

        Utils.validateDate(person);

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

    public List<Address> addAddress(Address address, String id) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        List<Address> addressList = entity.getAddressList();

        boolean newAddressIsPrincipal = address.isPrincipal();

        addressList.add(0, address);

        if (newAddressIsPrincipal) {
            for (Address a : addressList) {
                if (!a.equals(address)) {
                    a.setPrincipal(false);
                }
            }
        }

        Optional<Address> principalAddressOptional = addressList.stream()
                .filter(Address::isPrincipal)
                .findFirst();

        principalAddressOptional.ifPresent(principalAddress -> {
            addressList.remove(principalAddress);
            addressList.add(0, principalAddress);
        });

        entity.setAddressList(addressList);
        repository.save(entity);

        return addressList;
    }


}
