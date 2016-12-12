package com.cbd.backend;


import com.cbd.backend.model.dbo.*;
import com.cbd.backend.model.Measurements;
import com.cbd.backend.model.UserWithPasswordCheck;

import java.util.ArrayList;
import java.util.List;

import static com.cbd.backend.model.AuthorityPermission.ROLE_ORGANIZATION_ADMIN;
import static com.cbd.backend.model.AuthorityPermission.ROLE_READ_ACCESS;

public class TestFactories {

    public static Organization createOrganization() {
        long timestamp = System.currentTimeMillis();


        String organizationName = new String( "TestOrganization" );

        Organization organization = new Organization();
        organization.setSubscriptionEndDate( 1L );
        organization.setMeasurements( Measurements.METRIC );
        organization.setLanguageAndCountry( new LanguageAndCountry( "en", "CA", null ) );
        organization.setLastUpdated( timestamp );
        organization.setOrganizationName( organizationName );
        organization.setEnabled( true );
        organization.setFarmPlotScheme( FarmPlotScheme.AREA );
        organization.setOrganizationPhone( "6043196009" );
//        organization.setBillingDetails( new BillingDetails() );
        organization.setAddress( new Address() );

        return organization;
    }


    public static UserWithPasswordCheck getNewUser() {
        long timestamp = System.currentTimeMillis();
        UserWithPasswordCheck u = new UserWithPasswordCheck();
        List<Authority> authorities = new ArrayList<>();
        authorities.add( new Authority(ROLE_ORGANIZATION_ADMIN, true ) );
        authorities.add( new Authority(ROLE_READ_ACCESS, true ) );


        u.setAuthority( authorities );
        u.setUsername( new String( "newUser" ) );
        u.setPassword( new String( "vA!Id001" ) );
        u.setPasswordCheck( "vA!Id001" );
        u.setOrganization( organization );
        u.setFirstName( firstName );
        u.setLastName( lastName );
        u.setEmail( email );
        u.setPasswordUpdateDate( timestamp );
        u.setLastUpdated( timestamp );
        u.setSecurityId( 1L );

        return u;
    }

    public static UserWithPasswordCheck getNewUser( final String username ) {
        UserWithPasswordCheck user = getNewUser();
        user.setUsername( username );
        return user;
    }


    public static UserWithPasswordCheck getNewUser( final String username, final String organization ) {
        UserWithPasswordCheck user = getNewUser();
        user.setUsername( username );
        user.setOrganization( organization );
        return user;
    }

    public static User getNewUser(String admin, String org, String pass) {
        UserWithPasswordCheck user = getNewUser();
        user.setUsername( username );
        user.setOrganization( org );
        user.setPassword( pass );
        return user;
    }

    public static Organization createOrganization( String orgName ) {
        Organization org = createOrganization();
        org.setOrganizationName( orgName );
        long timestamp = System.currentTimeMillis();
        User u = new User();
        List<Authority> authorities = new ArrayList<>();
        authorities.add(new Authority( ROLE_READ_ACCESS, true) );

        return org;
    }

    public static User createOrganizationAdminUser() {
        long timestamp = System.currentTimeMillis();
        User u = new User();
        List<Authority> authorities = new ArrayList<>();
        authorities.add(new Authority( ROLE_ORGANIZATION_ADMIN, true) );


        u.setAuthority( authorities );
        u.setUsername( username );
        u.setPassword( password );
        u.setOrganization(organization);
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
    public static String organization =  "BugsSoftware";
}
