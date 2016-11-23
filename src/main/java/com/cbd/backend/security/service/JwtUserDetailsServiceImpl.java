package com.cbd.backend.security.service;

import com.cbd.backend.database.UserRepository;
import com.cbd.backend.model.Authority;
import com.cbd.backend.model.User;
import com.cbd.backend.security.JwtUserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stephan on 20.03.16.
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Value("${authentication.user}") String adminUser;
    @Value("${authentication.password}") String adminPass;
    @Value("${authentication.firstName}") String adminFirstName;
    @Value("${authentication.lastName}") String adminLastName;
    @Value("${authentication.email}") String adminEmail;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = null;
        if ( username.equalsIgnoreCase( "admin" ) ) {
            user = createAdminSystemUser();
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


    private User createAdminSystemUser() {
        long timestamp = System.currentTimeMillis();
        User u = new User();

        List<Authority> authorities = new ArrayList<Authority>();
        authorities.add(new Authority("ROLE_SITE_ADMIN" , true) );
        authorities.add(new Authority("ROLE_ACCOUNT_ADMIN", true) );
        // authorities.add(new Authority("ROLE_ACCOUNT_USER", true ) );

        u.setAuthory( authorities );
        u.setUsername( adminUser );
        u.setPassword( adminPass );
        u.setFirstName( adminFirstName );
        u.setLastName( adminLastName );
        u.setEmail( adminEmail );
        u.setPasswordUpdateDate( timestamp );
        u.setLastUpdated( timestamp );
        u.setId( 0L );
        u.setEnabled( true );
        return u;
    }
}
