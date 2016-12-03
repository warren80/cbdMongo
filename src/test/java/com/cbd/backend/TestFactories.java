package com.cbd.backend;


import com.cbd.backend.model.dbo.Address;
import com.cbd.backend.model.dbo.BillingDetails;
import com.cbd.backend.model.dbo.LanguageAndCountry;
import com.cbd.backend.model.Measurements;
import com.cbd.backend.model.NewFarm;
import com.cbd.backend.model.NewUser;
import com.cbd.backend.model.dbo.Authority;
import com.cbd.backend.model.dbo.FarmPlotScheme;
import com.cbd.backend.model.dbo.User;

import java.util.ArrayList;
import java.util.List;

import static com.cbd.backend.model.AuthorityPermission.ROLE_FARM_ADMIN;
import static com.cbd.backend.model.AuthorityPermission.ROLE_SITE_USER;

public class TestFactories {
    public static NewFarm getNewFarm() {
        long timestamp = System.currentTimeMillis();

        List<Authority> authorities = new ArrayList<>();
        String farmName = new String( "TestFarm" );
        authorities.add( new Authority(ROLE_FARM_ADMIN, true ) );

        NewFarm farm = new NewFarm();
        farm.setSubscriptionEndDate( 1L );
        farm.setMeasurements( Measurements.METRIC );
        farm.setLanguageAndCountry( new LanguageAndCountry( "en", "CA", null ) );
        farm.setLastUpdated( timestamp );
        farm.setFarmName( farmName );
        farm.setEnabled( true );
        farm.setFarmPlotScheme( FarmPlotScheme.AREA );
        farm.setFarmPhone( "6043196009" );
//        farm.setBillingDetails( new BillingDetails() );
        farm.setAddress( new Address() );

        NewUser u = new NewUser();

        u.setAuthority( authorities );
        u.setUsername( new String( "newUser" ) );
        u.setPassword( new String( "vA!Id001" ) );
        u.setPasswordCheck( "vA!Id001" );
        u.setFarm( farmName );
        u.setFirstName( firstName );
        u.setLastName( lastName );
        u.setEmail( email );
        u.setPasswordUpdateDate( timestamp );
        u.setLastUpdated( timestamp );
        u.setSecurityId( 1L );

        farm.setNewUser( u );

        return farm;
    }


    public static NewUser getNewUser() {
        long timestamp = System.currentTimeMillis();
        NewUser u = new NewUser();
        List<Authority> authorities = new ArrayList<>();
        authorities.add( new Authority(ROLE_FARM_ADMIN, true ) );
        authorities.add( new Authority( ROLE_SITE_USER, true ) );


        u.setAuthority( authorities );
        u.setUsername( new String( "newUser" ) );
        u.setPassword( new String( "vA!Id001" ) );
        u.setPasswordCheck( "vA!Id001" );
        u.setFarm( farm );
        u.setFirstName( firstName );
        u.setLastName( lastName );
        u.setEmail( email );
        u.setPasswordUpdateDate( timestamp );
        u.setLastUpdated( timestamp );
        u.setSecurityId( 1L );

        return u;
    }

    public static User createFarmUser(final String farm ) {
        long timestamp = System.currentTimeMillis();
        User u = new User();
        List<Authority> authorities = new ArrayList<>();
        authorities.add(new Authority( ROLE_SITE_USER, true) );


        u.setAuthority( authorities );
        u.setUsername( new String( "newUser" ) );
        u.setPassword( new String( "vA!Id001" ) );
        u.setFarm( farm );
        u.setFirstName( firstName );
        u.setLastName( lastName );
        u.setEmail( email );
        u.setPasswordUpdateDate( timestamp );
        u.setLastUpdated( timestamp );
        u.setSecurityId( 1L );

        return u;
    }

    public static User createFarmAdminUser() {
        long timestamp = System.currentTimeMillis();
        User u = new User();
        List<Authority> authorities = new ArrayList<>();
        authorities.add(new Authority(ROLE_FARM_ADMIN, true) );


        u.setAuthority( authorities );
        u.setUsername( username );
        u.setPassword( password );
        u.setFarm(farm);
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
    public static String farm =  "BugsSoftware";
}
