package com.cbd.backend.restApi.secured;

import com.cbd.backend.common.Helpers;
import com.cbd.backend.common.ValidationHelper;
import com.cbd.backend.common.model.OrganizationValidation;
import com.cbd.backend.model.AuthorityPermission;
import com.cbd.backend.model.JwtUser;
import com.cbd.backend.model.UserWithPasswordCheck;
import com.cbd.backend.common.model.UserValidation;
import com.cbd.backend.model.dbo.Authority;
import com.cbd.backend.model.dbo.User;
import com.cbd.backend.security.JwtTokenUtil;
import com.cbd.backend.security.SecurityLevel;
import com.cbd.backend.service.UserService;
import io.jsonwebtoken.Claims;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

import static com.cbd.backend.security.JwtTokenUtil.CLAIM_KEY_AUDIENCE;

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

    @Value("${createNewUser.user}")
    private String creationUser;

    static Logger log = Logger.getLogger(UserRestController.class.getName());

    private static SecurityLevel securityLevel = new SecurityLevel();

    /**
     * New Organization Admin account for nonexistent organization
     *
     * @param authorization security token
     * @param user user to be created
     * @return Success: savedUser. Failure: UserValidation
     **/
    @RequestMapping(value = "${api.users}", method = RequestMethod.POST)
    public ResponseEntity<?> createNewUser(@RequestHeader("Authorization") String authorization, @RequestBody final UserWithPasswordCheck user) {
        final String tokenUsername = jwtTokenUtil.getUsernameFromToken(authorization);
        String orgName = jwtTokenUtil.getOrganizationNameFromToken(authorization);

        UserValidation userValidation = validateNewUser( user, tokenUsername );
        if (userValidation != null ) {
            return ResponseEntity.ok(userValidation);
        }
        boolean isAdmin = false;
        try {
            isAdmin = securityLevel.isAdmin(authorization);
        } catch ( NullPointerException e ) {
            //TODO make this better
            //Do nothing this scenario indicates a new user
        }

        if( isAdmin ) {
                //no restrictions just create user without checks
                return ResponseEntity.ok(createUser(user));
        } else {
                //Overwrite authorities so no user gets created with random org or permissions
                if (tokenUsername.equals(creationUser)) {
                    user.setAuthority( createOrganiziationAdminAuthorities() );
                } else {
                    if (!securityLevel.isOrganizationAdmin(orgName, authorization)) {
                        user.setAuthority(createReadOnlyAuthorities());
                        return new ResponseEntity(HttpStatus.FORBIDDEN);
                    }
                }
                // Create user with just READ
                user.setOrganization(orgName);

        }
        return ResponseEntity.ok(createUser(user));
    }

    /**
     * Organization user updates his personal account
     *
     * @param authorization token
     * @param user to be updated
     * @return copy of saved object or validation failure
     */
    @RequestMapping(value = "${api.users}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@RequestHeader("Authorization") final String authorization, @RequestBody final UserWithPasswordCheck user) {
        if (securityLevel.isReadonlyUser(authorization, user.getUsername())) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
//
//        final String username = jwtTokenUtil.getUsernameFromToken( authorization );
//        final String organizationname = jwtTokenUtil.getOrganizationnameFromToken( authorization );
//        userService.updateUser(  );
//
//        //TODO ensure incoming request doesn't change admin authority fields;
//        //TODO organizationname, username, enabled etc.
//
//        if( !hasAuthority( jwtTokenUtil.getClaimsFromToken(), username, user) ) {
//            return new ResponseEntity( HttpStatus.BAD_REQUEST );
//        }
//        permissionCheck( authorization, user );
//        userService.newOrgValidateUser( user )
        return null;
    }

    @RequestMapping(value = "{$api.users}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        User u = userService.disableUser(username);
        return ResponseEntity.ok(u);
    }

    @RequestMapping(value = "${api.users}/{userName}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(HttpServletRequest request, @PathVariable String userName) {
        String tokenUserName = getUserNameFromAuthenticationFromRequest(request);
        String token = request.getHeader(tokenHeader);
        String tokenOrgName = getOrgFromAuthenticationFromRequest(request);
        if ((securityLevel.isReadonlyUser(tokenOrgName, request.getHeader(token))
                && tokenUserName.equals(userName))
                || securityLevel.isOrganizationAdmin(tokenOrgName, token)
                ) {
            JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(userName);
            if (user.getOrganization().equals(tokenOrgName)) {
                return ResponseEntity.ok(user);
            }
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "${api.usersSiteAdmin}/{userName}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(HttpServletRequest request, @PathVariable String userName) {
        String token = request.getHeader(tokenHeader);
//TODO finish this
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);

        return null;
    }

    private String getUserNameFromAuthenticationFromRequest(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        return jwtTokenUtil.getUsernameFromToken(token);
    }

    private String getAuthoritiesFromRequest(HttpServletRequest request) {
        request.getHeader(tokenHeader);
//        jwtTokenUtil.getC
        return null;
    }

    private String getOrgFromAuthenticationFromRequest(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        return jwtTokenUtil.getOrganizationNameFromToken(token);
    }


    boolean hasAuthority(Claims claims, String username, User user) {
        if (claims.get(AuthorityPermission.ROLE_ADMIN) != null) {
            return true;
        }
        if (claims.get(AuthorityPermission.ROLE_ORGANIZATION_ADMIN) != null
                && user.getOrganization().equalsIgnoreCase((String) claims.get(CLAIM_KEY_AUDIENCE))) {
            return true;
        }
        return username.equalsIgnoreCase(user.getUsername());
    }


    private User createUser(UserWithPasswordCheck user) {
        User result;
        try {
            result = userService.addUser(user);
        } catch (Exception e) {
            return null;
        }
        return result;
    }


    private UserValidation validateNewUser(final UserWithPasswordCheck user, String tokenUsername ) {


        UserValidation userValidation = userService.validateUser( user );
        new ValidationHelper().validateUserFields( userValidation, user );

        if ( tokenUsername.equals( creationUser ) ) {
            if (!userValidation.isOrganizationValid() && !userValidation.isUsernameValid()
                    && userValidation.isEmailValid()
                    && userValidation.isFirstNameValid()
                    && userValidation.isLastNameValid()
                    && userValidation.isPasswordValid() ) {
                //Return null when valid
                return null;
            }
        } else {
            if (!userValidation.isUsernameValid()
                    && userValidation.isOrganizationValid()
                    && userValidation.isEmailValid()
                    && userValidation.isFirstNameValid()
                    && userValidation.isLastNameValid()
                    && userValidation.isPasswordValid() ) {
                //Return null when valid
                return null;
            }
        }
        log.error( "Invalid User Creation Request: " + Helpers.objectToJson( user ) );
        log.error( "Validation Result: " + Helpers.objectToJson( userValidation ) );
        return userValidation;

    }

    private List<Authority> createOrganiziationAdminAuthorities() {
        List<Authority> authorityList = new ArrayList<Authority>();
        authorityList.add(new Authority(AuthorityPermission.ROLE_ORGANIZATION_ADMIN, true));
        authorityList.add(new Authority(AuthorityPermission.ROLE_WRITE, true));
        authorityList.add(new Authority(AuthorityPermission.ROLE_READ_ACCESS, true));
        return authorityList;
    }

    private List<Authority> createReadOnlyAuthorities() {
        List<Authority> authorityList = new ArrayList<Authority>();
        authorityList.add(new Authority(AuthorityPermission.ROLE_READ_ACCESS, true));
        return authorityList;
    }
}
