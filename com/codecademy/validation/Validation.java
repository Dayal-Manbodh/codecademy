package com.codecademy.validation;

import java.sql.Date;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {


    public boolean EmailValidation(String email){
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean DateValidation(LocalDate date){
        if(!date.isAfter(LocalDate.now())){
            return true;
        }
        return false;

    }

    public boolean ZipcodeValidation(String zipcode){
        Pattern pattern = Pattern.compile("^[1-9][0-9]{3} [A-Z]{2}$");
        Matcher matcher = pattern.matcher(zipcode);
        return matcher.matches();
    }
}