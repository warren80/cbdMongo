package com.cbd.backend.api;

import com.cbd.backend.common.Helpers;
import com.cbd.backend.model.JwtUser;
import com.cbd.backend.model.NewUser;
import com.cbd.backend.common.model.UserValidation;
import com.cbd.backend.model.dbo.User;
import com.cbd.backend.security.JwtTokenUtil;
import com.cbd.backend.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserRestController {

    @Autowired
    UserService userService;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    static Logger log = Logger.getLogger( UserRestController.class.getName()) ;

    @RequestMapping(value= "${api.users}", method = RequestMethod.POST)
    public ResponseEntity<?> createNewUser( @RequestBody NewUser user ) {
        UserValidation vr = userService.validateUser( user );

        if ( !vr.isValid() ) {
            log.error( "Invalid User Creation Request: " + Helpers.objectToJson( user ) );
            log.error( "Validation Result: " + Helpers.objectToJson( vr ) );
            return ResponseEntity.ok( vr );
        }

        User result;
        try {
            result = userService.addUser(user);
        } catch ( Exception e ) {
            return new ResponseEntity<String>( HttpStatus.BAD_REQUEST );
        }
        return ResponseEntity.ok( result );
    }

    @RequestMapping( value = "${api.users}", method = RequestMethod.PUT )
        public ResponseEntity<?> updateUser( @RequestBody User user ) {
            return null;
        }

    @RequestMapping( value = "${api.users}", method = RequestMethod.DELETE )
    public ResponseEntity<?> deleteUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        User u = userService.disableUser( username );
        return ResponseEntity.ok( u );
    }

    @RequestMapping( value = "${api.users}", method = RequestMethod.GET )
    public JwtUser getUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername( username );
        return user;
    }

    @RequestMapping( value = "${api.usersSiteAdmin}/{userName}", method = RequestMethod.DELETE )
    public ResponseEntity<?> deleteUser(HttpServletRequest request, @PathVariable String userName) {
        String token = request.getHeader(tokenHeader);
//TODO finish this
        UserDetails userDetails = this.userDetailsService.loadUserByUsername( userName );

        return null;
    }


}
