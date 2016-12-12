package com.cbd.backend.security;

import com.cbd.backend.model.AuthorityPermission;
import com.cbd.backend.model.Factory.JwtUserFactory;
import com.cbd.backend.model.JwtUser;
import com.cbd.backend.model.dbo.Authority;
import com.cbd.backend.model.dbo.User;
import io.jsonwebtoken.Claims;
import org.assertj.core.util.DateUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.*;

import static com.cbd.backend.model.AuthorityPermission.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.mobile.device.FunctionalTest.device;

public class JwtTokenUtilTest {

    private JwtTokenUtil jwtTokenUtil;

    @Before
    public void init() {
        jwtTokenUtil = new JwtTokenUtil();
        ReflectionTestUtils.setField(jwtTokenUtil, "expiration", 3600000L);
        ReflectionTestUtils.setField(jwtTokenUtil, "secret", "mySecret");
    }

    @Test
    public void testGenerateTokenGeneratesDifferentTokensForDifferentCreationDatesFast() throws Exception {
        final Map<String, Object> claims = createClaims("2016-09-08T03:00:00");
        final String token = jwtTokenUtil.generateToken(claims);

        final Map<String, Object> claimsForLaterToken = createClaims("2016-09-08T08:00:00");
        final String laterToken = jwtTokenUtil.generateToken(claimsForLaterToken);

        assertThat(token).isNotEqualTo(laterToken);
    }

    @Test
    public void getClaims() {

        User user = createRegularSystemUser();
        JwtUser jwtUser = createJwtUser( user );

        String token =  jwtTokenUtil.generateToken( jwtUser, device );

        Claims claims = jwtTokenUtil.getClaimsFromToken( token );
        String organizationName = (String) claims.get( "org" );
        assertThat(organizationName.equals( "testOrganization" ) );
    }




    private Map<String, Object> createClaims(String creationDate) {
        Map<String, Object> claims = new HashMap();
        claims.put(JwtTokenUtil.CLAIM_KEY_USERNAME, "testUser");
        claims.put(JwtTokenUtil.CLAIM_KEY_AUDIENCE, "testAudience");
        claims.put(JwtTokenUtil.CLAIM_KEY_CREATED, DateUtil.parseDatetime(creationDate));
        claims.put(JwtTokenUtil.CLAIM_KEY_ORGANIZATION, "testOrganization" );
        return claims;
    }

    static public User createRegularSystemUser() {
        User u = createGenericUser();
        List<Authority> authorities = new ArrayList<Authority>();
        authorities.add(new Authority(ROLE_READ_ACCESS, true) );
        u.setAuthority(authorities);
        return u;
    }


    static public User createInventoryUpdateUser() {
        User u = createGenericUser();
        List<Authority> authorities = new ArrayList<Authority>();
        authorities.add( new Authority( ROLE_WRITE, true ) );
        authorities.add(new Authority(ROLE_READ_ACCESS, true) );
        u.setAuthority(authorities);
        return u;
    }


    static public User createOrganizationAdminUser() {
        User u = createGenericUser();
        List<Authority> authorities = new ArrayList<Authority>();
        authorities.add(new Authority(ROLE_ORGANIZATION_ADMIN, true));
        authorities.add(new Authority( ROLE_WRITE, true));
        authorities.add(new Authority(ROLE_READ_ACCESS, true));
        u.setAuthority(authorities);
        return u;
    }

    static public User createAdmin() {
        User u = createGenericUser();
        List<Authority> authorities = new ArrayList<Authority>();
        authorities.add(new Authority( AuthorityPermission.ROLE_ADMIN , true) );
        u.setAuthority(authorities);
        return u;
    }

    static public User createGenericUser() {
        long timestamp = System.currentTimeMillis();
        User u = new User();




        u.setUsername("testUser");
        u.setPassword("password");
        u.setFirstName("Bob");
        u.setLastName("Johnson");
        u.setEmail("test@test.com");
        u.setPasswordUpdateDate(timestamp);
        u.setLastUpdated(timestamp);
        u.setSecurityId(1L);
        u.setEnabled(true);
        u.setOrganization("testOrganization");
        return u;
    }

    static public JwtUser createJwtUser( User user ) {

        JwtUser jwtUser = JwtUserFactory.create( user );
        return jwtUser;
    }


}