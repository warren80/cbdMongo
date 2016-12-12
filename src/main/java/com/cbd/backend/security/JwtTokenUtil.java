package com.cbd.backend.security;

import com.cbd.backend.model.AuthorityPermission;
import com.cbd.backend.model.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mobile.device.Device;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.*;

@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -3301605591108950415L;

    public static final String CLAIM_KEY_USERNAME = "sub";
    public static final String CLAIM_KEY_AUDIENCE = "audience";
    public static final String CLAIM_KEY_CREATED = "created";
    public static final String CLAIM_KEY_ORGANIZATION = "org";

    private static final String AUDIENCE_UNKNOWN = "unknown";
    private static final String AUDIENCE_WEB = "web";
    private static final String AUDIENCE_MOBILE = "mobile";
    private static final String AUDIENCE_TABLET = "tablet";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String getUsernameFromToken( String token ) {
        String username;
        try {
            final Claims claims = getClaimsFromToken( token );
            username = claims.getSubject();
        } catch ( Exception e ) {
            username = null;
        }
        return username;
    }

    public String getOrganizationNameFromToken( String token ) {
        String organizationname;
        try {
            final Claims claims = getClaimsFromToken( token );
            organizationname = (String) claims.get( CLAIM_KEY_ORGANIZATION );
        } catch (Exception e) {
            organizationname = null;
        }
        return organizationname;
    }

    public Date getCreatedDateFromToken( String token ) {

        final Claims claims = getClaimsFromToken( token );
        return new Date((Long) claims.get( CLAIM_KEY_CREATED ) );
    }

    public Date getExpirationDateFromToken( String token ) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken( token );
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    public String getAudienceFromToken( String token ) {
        String audience;
        try {
            final Claims claims = getClaimsFromToken( token );
            audience = (String) claims.get( CLAIM_KEY_AUDIENCE );
        } catch ( Exception e ) {
            audience = null;
        }
        return audience;
    }

    public Claims getClaimsFromToken( String token ) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey( secret )
                    .parseClaimsJws( token )
                    .getBody();
        } catch ( Exception e ) {
            claims = null;
        }
        return claims;
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration );
    }

    private Boolean isTokenExpired( String token ) {
        final Date expiration = getExpirationDateFromToken( token );
        return expiration.before( new Date() );
    }

    private Boolean isCreatedBeforeLastPasswordReset( Date created, Date lastPasswordReset ) {
        return ( lastPasswordReset != null && created.before( lastPasswordReset ) );
    }

    private String generateAudience( Device device ) {
        String audience = AUDIENCE_UNKNOWN;
        if (device.isNormal()) {
            audience = AUDIENCE_WEB;
        } else if (device.isTablet()) {
            audience = AUDIENCE_TABLET;
        } else if (device.isMobile()) {
            audience = AUDIENCE_MOBILE;
        }
        return audience;
    }

    private Boolean ignoreTokenExpiration( String token ) {
        String audience = getAudienceFromToken( token );
        return ( AUDIENCE_TABLET.equals( audience ) || AUDIENCE_MOBILE.equals( audience ) );
    }

    public String generateToken( JwtUser userDetails, Device device ) {
        Map<String, Object> claims = new HashMap<>();
        claims.put( CLAIM_KEY_USERNAME, userDetails.getUsername() );
        claims.put( CLAIM_KEY_AUDIENCE, generateAudience( device ) );
        claims.put( CLAIM_KEY_CREATED, new Date() );
        claims.put( CLAIM_KEY_ORGANIZATION, userDetails.getOrganization() );

        Collection<SimpleGrantedAuthority> permissionsList = (Collection<SimpleGrantedAuthority>)userDetails.getAuthorities();


        for( SimpleGrantedAuthority permission :  permissionsList ) {
            claims.put( permission.getAuthority(), true);
        }
        return generateToken(claims);
    }

    String generateToken( Map<String, Object> claims ) {
        return Jwts.builder()
                .setClaims( claims )
                .setExpiration( generateExpirationDate() )
                .signWith( SignatureAlgorithm.HS512, secret )
                .compact();
    }

    public Boolean canTokenBeRefreshed( String token, Date lastPasswordReset ) {
        final Date created = getCreatedDateFromToken( token );
        return !isCreatedBeforeLastPasswordReset( created, lastPasswordReset )
                && ( !isTokenExpired( token ) || ignoreTokenExpiration( token ) );
    }

    public String refreshToken( String token ) {
        String refreshedToken;
        try {
            final Claims claims = getClaimsFromToken( token );
            claims.put(CLAIM_KEY_CREATED, new Date() );
            refreshedToken = generateToken( claims );
        } catch ( Exception e ) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        JwtUser user = (JwtUser) userDetails;
        final String username = getUsernameFromToken(token);
        final Date created = getCreatedDateFromToken(token);
        //final Date expiration = getExpirationDateFromToken(token);
        return username.equals(user.getUsername())
                && !isTokenExpired( token )
                && !isCreatedBeforeLastPasswordReset( created, user.getLastPasswordResetDate() ) ;
    }

    public boolean isAdmin( String token ) {
        Claims claims = getClaimsFromToken( token );
        String authority = (String) claims.get( AuthorityPermission.ROLE_ADMIN );
        if ( authority != null )
            return true;
        return false;
    }

    public boolean isOrganizationAdmin( String token ) {
        Claims claims = getClaimsFromToken( token );
        String authority = (String) claims.get( AuthorityPermission.ROLE_ORGANIZATION_ADMIN);
        if ( authority != null )
            return true;
        return false;
    }

    public boolean isUpdater( String token ) {
        Claims claims = getClaimsFromToken( token );
        String authority = (String) claims.get( AuthorityPermission.ROLE_WRITE );
        if ( authority != null )
            return true;
        return false;
    }



}