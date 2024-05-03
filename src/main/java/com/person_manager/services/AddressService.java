package com.person_manager.services;

import com.person_manager.entities.Address;
import com.person_manager.exceptions.ResourceNotFoundException;
import com.person_manager.repositories.PersonRepository;
import com.person_manager.utils.address.AddressUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    PersonRepository repository;

    public List<Address> addAddress(Address address, String id) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        AddressUtils.reorganizeAddressList(entity, address);

        List<Address> addressList = entity.getAddressList();
        entity.setAddressList(addressList);
        repository.save(entity);

        return addressList;
    }
}
