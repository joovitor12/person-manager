package com.person_manager.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Address {

    private Person personAddress;
    private String addressName;
    private boolean isPrincipal;
}
