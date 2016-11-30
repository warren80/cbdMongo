package com.cbd.backend.model;

import com.cbd.backend.model.dbo.Authority;
import com.cbd.backend.model.dbo.FarmPlotScheme;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.cbd.backend.model.AuthorityPermission.ROLE_ACCOUNT_ADMIN;

/**
 * Created by warrenvoelkl on 2016-11-30.
 */
public class NewUserTestFast {

    private static String firstName = "warren";
    private static String lastName = "voelkl";
    private static String email = "warrenvoelkl@gmail.com";

    public static NewAccount getNewAccount() {
        long timestamp = System.currentTimeMillis();

        List<Authority> authorities = new ArrayList<>();
        String accountName = new String( "TestAccount" );
        authorities.add( new Authority( ROLE_ACCOUNT_ADMIN, true ) );

        NewAccount account = new NewAccount();
        account.setSubscriptionEndDate( 1L );
        account.setMeasurements( Measurements.METRIC );
        account.setLocale( new Locale( "en", "CA" ) );
        account.setLastUpdated( timestamp );
        account.setAccountName( accountName );
        account.setEnabled( true );
        account.setFarmPlotScheme( FarmPlotScheme.AREA );

        NewUser u = new NewUser();

        u.setAuthority( authorities );
        u.setUsername( new String( "newUser" ) );
        u.setPassword( new String( "vA!Id001" ) );
        u.setPasswordCheck( "vA!Id001" );
        u.setAccount( accountName );
        u.setFirstName( firstName );
        u.setLastName( lastName );
        u.setEmail( email );
        u.setPasswordUpdateDate( timestamp );
        u.setLastUpdated( timestamp );
        u.setSecurityId( 1L );

        account.setNewUser( u );

        return account;
    }
}
