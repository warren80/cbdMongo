package com.cbd.backend.common;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Helpers {

    static Logger log = Logger.getLogger( Helpers.class.getName() );

    public static String objectToJson (Object o) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = null;
        try {
            jsonString = mapper.writeValueAsString( o );
        } catch (JsonProcessingException e) {
            log.error( "Cannot parse object" , e );
        };
        return jsonString;
    }

    public static String passwordEncoder( String rawPass ) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode( rawPass );
        return hashedPassword;
    }
}
