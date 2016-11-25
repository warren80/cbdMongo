package com.cbd.backend.helper;

public class EmailValidator {

    static boolean isValidMail(String email) {
        if (email == null || "".equals(email))
            return false;

        email = email.trim();

        org.apache.commons.validator.routines.EmailValidator ev = org.apache.commons.validator.routines.EmailValidator.getInstance();
        return ev.isValid(email);

    }
}
