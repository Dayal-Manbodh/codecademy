package Validation;

import java.sql.Date;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {


    public boolean EmailValidation(String email){
        Pattern pattern = Pattern.compile("(.*)(@)(.*)(.[a-z])", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
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