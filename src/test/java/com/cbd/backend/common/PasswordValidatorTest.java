package com.cbd.backend.common;

import com.cbd.backend.common.model.UserValidation;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by warrenvoelkl on 2016-11-25.
 */
public class PasswordValidatorTest {

    @Test
    public void validateNewPassTestFast() throws Exception {
        UserValidation result = new UserValidation();
        PasswordValidator pw = new PasswordValidator( result );
        List<String> invalidPasswords = new ArrayList<String>();

        String validPassword = "T5tabjpw@";
        invalidPasswords.add( "" );
        invalidPasswords.add( "1234567891011" );
        invalidPasswords.add( "abcdefghijklm" );
        invalidPasswords.add( "!aB2" );

        pw.validateNewPass( validPassword, validPassword );
        assertTrue( result.isPasswordValid() );

        for( String password : invalidPasswords ) {
            pw.validateNewPass( password, password );
            assertFalse( result.isPasswordValid () );
        }
    }

}