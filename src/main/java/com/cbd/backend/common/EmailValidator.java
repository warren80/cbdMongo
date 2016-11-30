package com.cbd.backend.common;

import org.apache.log4j.Logger;

public class EmailValidator {

    static Logger log = Logger.getLogger( EmailValidator.class.getName() );

    static public boolean isValidMail(String email) {
        if (email == null || "".equals(email))
            return false;

        email = email.trim();

        org.apache.commons.validator.routines.EmailValidator ev = org.apache.commons.validator.routines.EmailValidator.getInstance();
        boolean result = ev.isValid(email);

        if ( result ) {
            log.debug( "invalidEmail: " + email);
        }
        return result;
    }
}
