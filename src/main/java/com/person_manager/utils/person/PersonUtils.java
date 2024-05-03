package com.person_manager.utils.person;

import com.person_manager.entities.Person;
import com.person_manager.exceptions.BadRequestException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class PersonUtils {

    private static final Pattern CEP_PATTERN = Pattern.compile("\\d{5}-\\d{3}");
    private static final Pattern PHONE_PATTERN = Pattern.compile("\\(\\d{2}\\)\\d{4,5}-\\d{4}");

    public static boolean validateCep(String cep) {
        return CEP_PATTERN.matcher(cep).matches();
    }

    public static boolean validatePhoneNumber(String phoneNumber) {
        return PHONE_PATTERN.matcher(phoneNumber).matches();
    }
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
        if (!validateCep(requestBodyPerson.getCep())) {
            throw new BadRequestException("Invalid CEP format. Please provide the CEP in the format 99999-999.");
        }
        if (!validatePhoneNumber(requestBodyPerson.getPhoneNumber())) {
            throw new BadRequestException("Invalid phone number format. Please provide the phone number in the format (99)99999-9999 or (99)9999-9999.");
        }

        update.setFullName(requestBodyPerson.getFullName());
        update.setBirthDate(requestBodyPerson.getBirthDate());
        update.setPublicPlace(requestBodyPerson.getPublicPlace());
        update.setCep(requestBodyPerson.getCep());
        update.setPhoneNumber(requestBodyPerson.getPhoneNumber());
        update.setCity(requestBodyPerson.getCity());
        update.setState(requestBodyPerson.getState());
    }
}
