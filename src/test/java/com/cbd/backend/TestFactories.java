package com.cbd.backend;


import com.cbd.backend.model.Account.dbo.Address;
import com.cbd.backend.model.Account.dbo.BillingDetails;
import com.cbd.backend.model.Measurements;
import com.cbd.backend.model.NewAccount;
import com.cbd.backend.model.NewUser;
import com.cbd.backend.model.dbo.Authority;
import com.cbd.backend.model.dbo.FarmPlotScheme;
import com.cbd.backend.model.dbo.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.cbd.backend.model.AuthorityPermission.ROLE_ACCOUNT_ADMIN;
import static com.cbd.backend.model.AuthorityPermission.ROLE_SITE_USER;

public class TestFactories {
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
        account.setAccountPhone( "6043196009" );
        account.setBillingDetails( new BillingDetails() );
        account.setAddress( new Address() );

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


    public static NewUser getNewUser() {
        long timestamp = System.currentTimeMillis();
        NewUser u = new NewUser();
        List<Authority> authorities = new ArrayList<>();
        authorities.add( new Authority( ROLE_ACCOUNT_ADMIN, true ) );
        authorities.add( new Authority( ROLE_SITE_USER, true ) );


        u.setAuthority( authorities );
        u.setUsername( new String( "newUser" ) );
        u.setPassword( new String( "vA!Id001" ) );
        u.setPasswordCheck( "vA!Id001" );
        u.setAccount( account );
        u.setFirstName( firstName );
        u.setLastName( lastName );
        u.setEmail( email );
        u.setPasswordUpdateDate( timestamp );
        u.setLastUpdated( timestamp );
        u.setSecurityId( 1L );

        return u;
    }

    public static User getAccountUser( final String account ) {
        long timestamp = System.currentTimeMillis();
        User u = new User();
        List<Authority> authorities = new ArrayList<>();
        authorities.add(new Authority( ROLE_SITE_USER, true) );


        u.setAuthority( authorities );
        u.setUsername( new String( "newUser" ) );
        u.setPassword( new String( "vA!Id001" ) );
        u.setAccount( account );
        u.setFirstName( firstName );
        u.setLastName( lastName );
        u.setEmail( email );
        u.setPasswordUpdateDate( timestamp );
        u.setLastUpdated( timestamp );
        u.setSecurityId( 1L );

        return u;
    }

    public static User getStockUser() {
        long timestamp = System.currentTimeMillis();
        User u = new User();
        List<Authority> authorities = new ArrayList<>();
        authorities.add(new Authority( ROLE_ACCOUNT_ADMIN , true) );


        u.setAuthority( authorities );
        u.setUsername( username );
        u.setPassword( password );
        u.setAccount( account );
        u.setFirstName( firstName );
        u.setLastName( lastName );
        u.setEmail( email );
        u.setPasswordUpdateDate( timestamp );
        u.setLastUpdated( timestamp );
        u.setSecurityId( 1L );

        return u;
    }

    public static String username = "wvoelkl";
    public static String password = "password";
    public static String firstName = "warren";
    public static String lastName = "voelkl";
    public static String email = "warrenvoelkl@gmail.com";
    public static String account =  "BugsSoftware";
}
