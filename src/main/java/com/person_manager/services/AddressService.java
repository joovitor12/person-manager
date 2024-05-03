package com.person_manager.services;

import com.person_manager.entities.Address;
import com.person_manager.exceptions.BadRequestException;
import com.person_manager.exceptions.ResourceNotFoundException;
import com.person_manager.repositories.PersonRepository;
import com.person_manager.utils.address.AddressUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class AddressService {

    private Logger logger = Logger.getLogger(AddressService.class.getName());

    @Autowired
    PersonRepository repository;

    public List<Address> findAll(String id){
        logger.info("Finding all address from " + id);

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        return  entity.getAddressList();

    }

    public List<Address> save(Address address, String id) {
        logger.info("Adding address for " + id);

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        AddressUtils.reorganizeAddressList(entity, address);

        List<Address> addressList = entity.getAddressList();

        entity.setAddressList(addressList);
        repository.save(entity);

        return addressList;
    }


    public Address findByName(String addressName, String id) {
        logger.info("Finding address " + addressName + " in " + id + " address list");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        return entity.getAddressList().stream()
                .filter(a -> a.getAddressName().equalsIgnoreCase(addressName))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this address name!"));
    }

    public Address update(Address address, String addressName, String id) {
        logger.info("Updating address " + addressName + " for ID " + id);

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID: " + id));

        List<Address> addressList = entity.getAddressList();

        Optional<Address> addressOptional = addressList.stream()
                .filter(a -> a.getAddressName().equalsIgnoreCase(addressName))
                .findFirst();

        if (addressOptional.isPresent()) {
            Address existingAddress = addressOptional.get();
            existingAddress.setAddressName(address.getAddressName());
            existingAddress.setPrincipal(address.isPrincipal());

            AddressUtils.reorganizeAddressList(entity, existingAddress);
            repository.save(entity);

            return existingAddress;
        } else {
            throw new ResourceNotFoundException("Address not found with name: " + addressName);
        }
    }

    }
