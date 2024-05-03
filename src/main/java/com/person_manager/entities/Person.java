package com.person_manager.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Document(collection = "person")
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String fullName;
    private String birthDate;
    private List<Address> addressList;
    private String publicPlace;
    private String cep;
    private String phoneNumber;
    private String city;
    private String state;

    public Person(String fullName, String birthDate,
                  String publicPlace, String cep, String phoneNumber,
                  String city, String state) {
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.addressList = new ArrayList<>();
        this.publicPlace = publicPlace;
        this.cep = cep;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.state = state;
    }


}
