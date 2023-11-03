package com.example.demo.util.reservation_request;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerData {
//    private final static String nameRegex = "^[A-Z][a-zA-Z]{2,}$";
//    private final static String surnameRegex = "^[A-Z][a-zA-Z]{2,}(-[A-Z][a-zA-Z]{2,})?$";
    private final static String nameRegex = "^[\\p{Lu}][\\p{Ll}]{2,}$";
    private final static String surnameRegex = "^[\\p{Lu}][\\p{Ll}]{2,}(-[\\p{Lu}][\\p{Ll}]{2,})?$";
    private String name;
    private String surname;

    public CustomerData(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public boolean isValid() {
        Pattern namePattern = Pattern.compile(nameRegex);
        Matcher nameMatcher = namePattern.matcher(name);
        if (!nameMatcher.matches()) {
            return false;
        }

        Pattern surnamePattern = Pattern.compile(surnameRegex);
        Matcher surnameMatcher = namePattern.matcher(surname);
        return surnameMatcher.matches();
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
