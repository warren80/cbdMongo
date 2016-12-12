package com.cbd.backend.restApi.secured;

import com.cbd.backend.service.OrganizationService;
import com.cbd.backend.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AdminRestController {

    static Logger log = Logger.getLogger( AdminRestController.class.getName()) ;

    @Autowired
    UserService userService;
    @Autowired
    OrganizationService organizationService;



    @RequestMapping( value = "/api/admin/deleteOrganization/{organizationName}", method = RequestMethod.DELETE)
    ResponseEntity<?> permanentDeleteOrganization( HttpServletRequest request, @PathVariable( "organizationName" ) String organizationName ) {
        log.info( "Request recieved adminDeleteOrganization: " + " " + request.getRequestURL() );
     ResponseEntity<?> entity = ResponseEntity.ok( new Result( organizationService.deleteOrganization( organizationName ) ) );
     return entity;
    }

    @RequestMapping( value = "/api/admin/deleteUser/{userName}", method = RequestMethod.DELETE)
    ResponseEntity<?> permanentDeleteUser( HttpServletRequest request, @PathVariable( "userName" ) String userName ) {
        log.info( "Request recieved adminDeleteUser: " + request.getRemoteAddr() + " " + request.getRequestURL() );

       String result = "{ \"SUCCESS\": \"" +  userService.deleteUser( userName ) + "\"} ";
        ResponseEntity<?> entity = ResponseEntity.ok( new Result ( userService.deleteUser( userName ) ) );
        return entity;
    }

    private class Result{
        private boolean SUCCESS;
        Result( boolean result ) {
            this.SUCCESS = result;
        }


        public boolean isSUCCESS() {
            return SUCCESS;
        }

        public void setSUCCESS(boolean SUCCESS) {
            this.SUCCESS = SUCCESS;
        }
    }

}
