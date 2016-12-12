package com.cbd.backend.restApi.secured;

import com.cbd.backend.common.Helpers;
import com.cbd.backend.common.model.OrganizationValidation;
import com.cbd.backend.model.dbo.Organization;
import com.cbd.backend.security.JwtTokenUtil;
import com.cbd.backend.service.OrganizationService;
import com.cbd.backend.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrganizationRestController {

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private UserService userService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    static Logger log = Logger.getLogger( OrganizationRestController.class.getName()) ;

    /**
     * A new admin user can create a new organization( only once per user account )
     * @param token jwtToken
     * @param org new Organization
     * @return
     */
    @RequestMapping(value= "${api.createOrganization}", method = RequestMethod.POST)
    public ResponseEntity<?> createOrganization( @RequestHeader( "Authorization" ) String token,
                                                 @RequestBody final Organization org ) {
        final String tokenUsername = jwtTokenUtil.getUsernameFromToken( token );
        String tokenOrganization = null;
        try {
            tokenOrganization = jwtTokenUtil.getOrganizationNameFromToken(token);
        } catch ( NullPointerException e ) {
            //expecting user who has never had an organization set.
        }

        if ( tokenOrganization != null ) {
            return new ResponseEntity( HttpStatus.BAD_REQUEST );
        }

        log.info( "received request " + org);

        OrganizationValidation av = organizationService.validateOrganization( org );

        //validate
        if ( !av.isValid() ) {
            log.error( "Invalid Organization Creation Request: " + Helpers.objectToJson( org ) );
            log.error( "Validation Result: " + Helpers.objectToJson( av ) );
            return ResponseEntity.ok( av );
        }
        log.debug( "Organization validation result: " + Helpers.objectToJson( av ) );

        //Save new organization
        Organization savedOrganization = null;
        try {
            savedOrganization = organizationService.createOrganization( org );
        } catch ( Exception e ) {
            log.error( "Failed to create organization: ", e );
        }
        if( savedOrganization == null ) {
            return new ResponseEntity( HttpStatus.BAD_REQUEST );
        }

        try {
            //set user creating organization to own new organization
            userService.setOrganization( tokenUsername, org.getOrganizationName() );
        } catch ( Exception e ) {
            log.error( "Cannot update User does not exists", e );
            return ResponseEntity.ok( HttpStatus.BAD_REQUEST );
        }
        return ResponseEntity.ok( savedOrganization );
    }


}
