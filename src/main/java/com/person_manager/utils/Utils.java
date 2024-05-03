package com.person_manager.utils;

import com.person_manager.entities.Person;
import com.person_manager.exceptions.BadRequestException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static void validateDate(Person person) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date birthDate = inputFormat.parse(person.getBirthDate());
            person.setBirthDate(outputFormat.format(birthDate));
        } catch (ParseException e) {
            throw new BadRequestException("Invalid date format. Please provide the date in the format dd/MM/yyyy.");
        }
    }
}
