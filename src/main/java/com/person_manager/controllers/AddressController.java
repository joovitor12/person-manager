package com.person_manager.controllers;

import com.person_manager.entities.Address;
import com.person_manager.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/address/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class AddressController {

    @Autowired
    AddressService service;

    @PostMapping(path = "/address")
    public ResponseEntity<List<Address>> saveAddress(@RequestBody Address address, @RequestParam String id) {
        return ResponseEntity.ok().body(service.addAddress(address, id));
    }
}
