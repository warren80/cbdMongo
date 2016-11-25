package com.cbd.backend.api;


import com.cbd.backend.model.JwtUser;
import com.cbd.backend.model.User;
import com.cbd.backend.security.JwtTokenUtil;
import com.cbd.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserRestController {

    @Autowired
    UserService userService;

//    @Value( "${api.auth}" )
//    final static String value /api;

    @RequestMapping(value = "/api/users", method = RequestMethod.POST)
    public ResponseEntity<?> getAuthenticatedUser(@RequestBody User user) {
        User result = userService.addUser( user );
        return ResponseEntity.ok( result );
    }

    @RequestMapping(value= "${api.auth}", method = RequestMethod.PUT)
        public ResponseEntity<?> updateUser( @RequestBody User user ) {
            return null;
        }

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public JwtUser getAuthenticatedUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        return user;
    }


}
