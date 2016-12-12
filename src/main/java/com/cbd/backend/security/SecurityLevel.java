package com.cbd.backend.security;

import com.cbd.backend.model.AuthorityPermission;
import com.cbd.backend.service.impl.UserServiceImpl;
import io.jsonwebtoken.Claims;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * Determines if the tokens has the required authority
 * 
 * 1 Is the requested operation in the same organization.
 * 2 Does the token have the correct authority.
 */
public class SecurityLevel {

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    static Logger log = Logger.getLogger( UserServiceImpl.class.getName() );


    public SecurityLevel(){}

    public boolean isReadonlyUser( final String organization, final String token) {
        if (isAdmin( token ) ) {
            return true;
        }
        return validateOrganizationAndAuthorityLevel( AuthorityPermission.ROLE_READ_ACCESS, organization, token );
    }

    public boolean isPermittedToUpdatingInventory( final String organization, final String user, final String token ) {
        if (isAdmin( token ) ) {
            return true;
        }
        return validateOrganizationAndAuthorityLevel( AuthorityPermission.ROLE_WRITE, organization, token );
    }
    public boolean isOrganizationAdmin( final String organization, final String token ) {
        if (isAdmin( token ) ) {
            return true;
        }
        return validateOrganizationAndAuthorityLevel( AuthorityPermission.ROLE_ORGANIZATION_ADMIN, organization, token );
    }
    public boolean isAdmin( final String token) {
        return getPermissionInToken(token).contains( AuthorityPermission.ROLE_ADMIN );
    }

    private boolean validateOrganizationAndAuthorityLevel( final AuthorityPermission authorityPermission, final String organization, final String token ) {
        boolean hasCorrectPermission = false;
        Set permissions = getPermissionInToken(token);
        if ( permissions.contains( authorityPermission ) ) {
            hasCorrectPermission = true;
        }
        boolean isCorrectOrganization = false;
        if ( organization.equals(jwtTokenUtil.getOrganizationNameFromToken( token ) ) ) {
            isCorrectOrganization = true;
        }
        return hasCorrectPermission && isCorrectOrganization;
    }


     private Set<AuthorityPermission> getPermissionInToken( final String token ) {
         Set<AuthorityPermission> authorityPermissions = new HashSet<>();
         Claims claims = jwtTokenUtil.getClaimsFromToken( token );

         for ( AuthorityPermission permission: AuthorityPermission.values() ) {
             Object permitted = claims.get( permission.name() );
             if ( permitted != null ) {
                 authorityPermissions.add( permission );
             }
         }
         return authorityPermissions;
     }

     void setJwtTokenUtil(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
     }

}
