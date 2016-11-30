package com.cbd.backend.common;


import com.cbd.backend.common.model.UserValidation;
import org.apache.log4j.Logger;

import java.util.regex.Pattern;

public class PasswordValidator {
    private static Logger log = Logger.getLogger(PasswordValidator.class.getName());
    private final Pattern hasUppercase = Pattern.compile("[A-Z]");
    private final Pattern hasLowercase = Pattern.compile("[a-z]");
    private final Pattern hasNumber = Pattern.compile("\\d");
    private final Pattern hasSpecialChar = Pattern.compile("[^a-zA-Z0-9 ]");
    private final UserValidation userValidationResults;

    public PasswordValidator( UserValidation results ) {
        this.userValidationResults = results;
    }
    public void validateNewPass(String pass1, String pass2) {
        boolean result = true;
        if (pass1 == null || pass2 == null) {
            log.info("Passwords = null");
            result = false;
        }

        if (pass1.isEmpty() || pass2.isEmpty()) {
            result = false;
        }

        if (pass1.equals(pass2)) {

            if ( pass1.length() < 5 || pass1.length() > 20) {
                log.info( "password length < 9" );
                result = false;
            }

            if (!hasUppercase.matcher(pass1).find()) {
                log.info( "passwordneeds uppercase" );
                result = false;
            }

            if (!hasLowercase.matcher(pass1).find()) {
                log.info( "passwordneeds <-- needs lowercase" );
                result = false;
            }

            if (!hasNumber.matcher(pass1).find()) {
                log.info( "passwordneeds needs a number" );
                result = false;
            }

            if (!hasSpecialChar.matcher(pass1).find()) {
                log.info( "passwordneeds needs a specail character" );
                result = false;
            }
        } else {
            log.info( "passwords don't match" );
            result = false;;
        }
        if ( result ) {
            log.info("Password validates");
        }

        userValidationResults.setPasswordValid( result );
    }
}