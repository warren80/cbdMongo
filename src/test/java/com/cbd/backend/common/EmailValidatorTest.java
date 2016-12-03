package com.cbd.backend.common;

import org.junit.Test;

import static org.junit.Assert.*;

public class EmailValidatorTest {
    @Test
    public void isValidMailTestFast() throws Exception {
        assertFalse( EmailValidator.isValidMail( "bugs.moo") );
        assertTrue( EmailValidator.isValidMail( "warrenvoelkl@gmail.com" ) );
    }

}