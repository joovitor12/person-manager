package com.person_manager.utils.person;

import com.person_manager.entities.Person;
import com.person_manager.exceptions.BadRequestException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PersonUtils {

    public static void validateDate(Person person){
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
        inputFormat.setLenient(false);

        try {
            Date birthDate = inputFormat.parse(person.getBirthDate());
        } catch (ParseException e) {
            throw new BadRequestException("Invalid birth date format. Please provide the date in the format dd/MM/yyyy.");
        }
    }

    public static void setProperties(Person update, Person requestBodyPerson){
        update.setFullName(requestBodyPerson.getFullName());
        update.setBirthDate(requestBodyPerson.getBirthDate());
        update.setPublicPlace(requestBodyPerson.getPublicPlace());
        update.setCep(requestBodyPerson.getCep());
        update.setPhoneNumber(requestBodyPerson.getPhoneNumber());
        update.setCity(requestBodyPerson.getCity());
        update.setState(requestBodyPerson.getState());
    }
}
