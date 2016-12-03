package com.cbd.backend.security;

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

import static com.cbd.backend.model.AuthorityPermission.ROLE_SITE_USER;
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

        String token =  jwtTokenUtil.generateToken( (JwtUser) jwtUser, device );

        Claims claims = jwtTokenUtil.getClaimsFromToken( token );
        String farmName = (String) claims.get( "farm" );
        assertThat(farmName.equals( "testFarm" ) );
    }




    private Map<String, Object> createClaims(String creationDate) {
        Map<String, Object> claims = new HashMap();
        claims.put(JwtTokenUtil.CLAIM_KEY_USERNAME, "testUser");
        claims.put(JwtTokenUtil.CLAIM_KEY_AUDIENCE, "testAudience");
        claims.put(JwtTokenUtil.CLAIM_KEY_CREATED, DateUtil.parseDatetime(creationDate));
        claims.put(JwtTokenUtil.CLAIM_KEY_ACCOUNT, "testFarm" );
        return claims;
    }

    public User createRegularSystemUser() {
        long timestamp = System.currentTimeMillis();
        User u = new User();

        List<Authority> authorities = new ArrayList<Authority>();
//        authorities.add(new Authority( ROLE_SITE_ADMIN , true) );
//        authorities.add(new Authority( ROLE_FARM_ADMIN, true) );
        authorities.add(new Authority(ROLE_SITE_USER, true));

        u.setAuthority(authorities);
        u.setUsername("testUser");
        u.setPassword("password");
        u.setFirstName("Bob");
        u.setLastName("Johnson");
        u.setEmail("test@test.com");
        u.setPasswordUpdateDate(timestamp);
        u.setLastUpdated(timestamp);
        u.setSecurityId(1L);
        u.setEnabled(true);
        u.setFarm("testFarm");
        return u;
    }

    public JwtUser createJwtUser( User user ) {

        JwtUser jwtUser = JwtUserFactory.create( user );
        return jwtUser;
    }


}