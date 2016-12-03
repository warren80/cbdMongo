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


    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername( final String username) throws UsernameNotFoundException {

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
        authorities.add(new Authority( ROLE_SITE_ADMIN , true) );
        authorities.add(new Authority(ROLE_FARM_ADMIN, true) );
        // authorities.add(new Authority("ROLE_ACCOUNT_USER", true ) );

        u.setAuthority( authorities );
        u.setUsername( adminUser );
        u.setPassword( adminPass );
        u.setFirstName( adminFirstName );
        u.setLastName( adminLastName );
        u.setEmail( adminEmail );
        u.setPasswordUpdateDate( timestamp );
        u.setLastUpdated( timestamp );
        u.setSecurityId( 1L );
        u.setEnabled( true );
        return u;
    }
}
