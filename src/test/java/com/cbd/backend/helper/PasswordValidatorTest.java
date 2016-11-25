package com.cbd.backend.helper;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by warrenvoelkl on 2016-11-25.
 */
public class PasswordValidatorTest {

    @Test
    public void validateNewPassTest() throws Exception {
        PasswordValidator pw = new PasswordValidator();
        List<String> invalidPasswords = new ArrayList<String>();

        String validPassword = "T5tabjpw@";
        invalidPasswords.add( "" );
        invalidPasswords.add( "1234567891011" );
        invalidPasswords.add( "abcdefghijklm" );
        invalidPasswords.add( "!aB2" );

        assertTrue( pw.validateNewPass( validPassword , validPassword ) );

        for( String password : invalidPasswords) {
            assertFalse( pw.validateNewPass( password, password ) );
        }
    }

}