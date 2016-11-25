package com.cbd.backend.helper;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by warrenvoelkl on 2016-11-25.
 */
public class EmailValidatorTest {
    @Test
    public void isValidMailTest() throws Exception {
        assertFalse( EmailValidator.isValidMail( "bugs.moo") );
        assertTrue( EmailValidator.isValidMail( "warrenvoelkl@gmail.com" ) );
    }

}