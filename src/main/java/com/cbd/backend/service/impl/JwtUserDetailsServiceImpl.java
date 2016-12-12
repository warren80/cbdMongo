package com.cbd.backend.service.impl;

import com.cbd.backend.database.UserRepository;
import com.cbd.backend.model.dbo.Authority;
import com.cbd.backend.model.dbo.User;
import com.cbd.backend.model.Factory.JwtUserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.cbd.backend.model.AuthorityPermission.*;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Value("${authentication.user}") String adminUser;
    @Value("${authentication.password}") String adminPass;
    @Value("${authentication.firstName}") String adminFirstName;
    @Value("${authentication.lastName}") String adminLastName;
    @Value("${authentication.email}") String adminEmail;

    @Value("${createNewUser.user}") String createNewUser;
    @Value("${createNewUser.password}") String createNewPass;
    @Value("${createNewUser.firstName}") String createNewFirstName;
    @Value("${createNewUser.lastName}") String createNewLastName;
    @Value("${createNewUser.email}") String createNewEmail;




    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername( final String username) throws UsernameNotFoundException {

        User user = null;
        if ( username.equalsIgnoreCase( "admin" ) ) {
            user = createAdminSystemUser( adminUser, adminPass, adminFirstName, adminLastName, adminEmail);
        } else if (username.equalsIgnoreCase( "signup" ) ) {
            user = createSignupUser();
        } else {
            try {
                user = userRepository.findByUsername(username);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (user == null) {
                throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
            }
        }
        return JwtUserFactory.create(user);
    }


    public static User createAdminSystemUser( String adminUser, String adminPass, String adminFirstName, String adminLastName, String adminEmail ) {
        long timestamp = System.currentTimeMillis();
        User u = new User();

        List<Authority> authorities = new ArrayList<Authority>();
        authorities.add(new Authority( ROLE_ADMIN, true) );
        authorities.add(new Authority(ROLE_ORGANIZATION_ADMIN, true) );
        // authorities.add(new Authority("ROLE_ACCOUNT_USER", true ) );

        u.setAuthority( authorities );
        u.setUsername( adminUser );
        u.setPassword( BCrypt.hashpw( adminPass, BCrypt.gensalt() ) );
        u.setFirstName( adminFirstName );
        u.setLastName( adminLastName );
        u.setEmail( adminEmail );
        u.setPasswordUpdateDate( timestamp );
        u.setLastUpdated( timestamp );
        u.setSecurityId( 1L );
        u.setEnabled( true );
        return u;
    }

    public User createSignupUser() {
        long timestamp = System.currentTimeMillis();
        User u = new User();

        List<Authority> authorities = new ArrayList<Authority>();
        authorities.add(new Authority(ROLE_CREATE_NEW_USER, true) );

        u.setAuthority( authorities );
        u.setUsername( createNewUser );
        u.setPassword( BCrypt.hashpw( createNewPass, BCrypt.gensalt() ) );
        u.setFirstName( createNewFirstName );
        u.setLastName( createNewLastName );
        u.setEmail( createNewEmail );
        u.setPasswordUpdateDate( timestamp );
        u.setLastUpdated( timestamp );
        u.setSecurityId( 1L );
        u.setEnabled( true );
        return u;
    }




}
