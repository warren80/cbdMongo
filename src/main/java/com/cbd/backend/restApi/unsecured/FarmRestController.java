package com.cbd.backend.restApi.unsecured;

import com.cbd.backend.common.Helpers;
import com.cbd.backend.common.model.FarmValidation;
import com.cbd.backend.model.dbo.Farm;
import com.cbd.backend.model.NewFarm;
import com.cbd.backend.model.dbo.User;
import com.cbd.backend.service.FarmService;
import com.cbd.backend.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FarmRestController {

    @Autowired
    private FarmService farmService;

    @Autowired
    private UserService userService;

    static Logger log = Logger.getLogger( FarmRestController.class.getName()) ;

    @RequestMapping(value= "${api.createFarm}", method = RequestMethod.POST)
    public ResponseEntity<?> createFarm(@RequestBody NewFarm newFarm ) {
        log.info( "received request " + newFarm);
        FarmValidation av = farmService.validateFarm( newFarm );

        if ( !av.isValid() ) {
            log.error( "Invalid Farm Creation Request: " + Helpers.objectToJson( newFarm ) );
            log.error( "Validation Result: " + Helpers.objectToJson( av ) );
            return ResponseEntity.ok( av );
        }



        Farm savedFarm = null;
        try {
            savedFarm = farmService.createFarm( newFarm );
        } catch ( Exception e ) {
            log.error( "Failed to create farm: ", e );
        }
        if( savedFarm == null ) {
            return ResponseEntity.ok( "Farm Creation Exception" );
        }

        User savedUser;
        try {
            savedUser = userService.addUser( newFarm.getNewUser() );
        } catch ( Exception e ) {
            log.error( "Failed to create user farm", e );
            return ResponseEntity.ok( savedFarm );
        }
        NewFarm result = new NewFarm( savedFarm, savedUser );

        return ResponseEntity.ok( result );
    }


}
