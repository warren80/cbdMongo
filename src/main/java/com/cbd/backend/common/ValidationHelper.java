package com.cbd.backend.common;

import com.cbd.backend.common.model.AccountValidation;
import com.cbd.backend.common.model.UserValidation;
import com.cbd.backend.model.NewAccount;
import com.cbd.backend.model.NewUser;
import com.cbd.backend.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

public class ValidationHelper {

    static Logger log = Logger.getLogger( UserServiceImpl.class.getName() );

    public static boolean validateName( String str ) {
        final Pattern regex = Pattern.compile( "^[\\p{L} .'-]+$" );
        if (!regex.matcher( str ).find()) {
            log.info( "password has non alphanumeric characters" );
            return false;
        }
        return true;
    }

    public Map<String, String> getCountries() {
        Map<String, String> countries = new HashMap<>();
        for (String iso : Locale.getISOCountries()) {
            Locale l = new Locale("", iso);
            countries.put(l.getDisplayCountry(), iso);
        }
        return countries;
    }

    public Map<String, String> getStates() {
        Map<String, String> states = new HashMap<String, String>();
        states.put("Alabama","AL");
        states.put("Alaska","AK");
        states.put("Alberta","AB");
        states.put("American Samoa","AS");
        states.put("Arizona","AZ");
        states.put("Arkansas","AR");
        states.put("Armed Forces (AE)","AE");
        states.put("Armed Forces Americas","AA");
        states.put("Armed Forces Pacific","AP");
        states.put("British Columbia","BC");
        states.put("California","CA");
        states.put("Colorado","CO");
        states.put("Connecticut","CT");
        states.put("Delaware","DE");
        states.put("District Of Columbia","DC");
        states.put("Florida","FL");
        states.put("Georgia","GA");
        states.put("Guam","GU");
        states.put("Hawaii","HI");
        states.put("Idaho","ID");
        states.put("Illinois","IL");
        states.put("Indiana","IN");
        states.put("Iowa","IA");
        states.put("Kansas","KS");
        states.put("Kentucky","KY");
        states.put("Louisiana","LA");
        states.put("Maine","ME");
        states.put("Manitoba","MB");
        states.put("Maryland","MD");
        states.put("Massachusetts","MA");
        states.put("Michigan","MI");
        states.put("Minnesota","MN");
        states.put("Mississippi","MS");
        states.put("Missouri","MO");
        states.put("Montana","MT");
        states.put("Nebraska","NE");
        states.put("Nevada","NV");
        states.put("New Brunswick","NB");
        states.put("New Hampshire","NH");
        states.put("New Jersey","NJ");
        states.put("New Mexico","NM");
        states.put("New York","NY");
        states.put("Newfoundland","NF");
        states.put("North Carolina","NC");
        states.put("North Dakota","ND");
        states.put("Northwest Territories","NT");
        states.put("Nova Scotia","NS");
        states.put("Nunavut","NU");
        states.put("Ohio","OH");
        states.put("Oklahoma","OK");
        states.put("Ontario","ON");
        states.put("Oregon","OR");
        states.put("Pennsylvania","PA");
        states.put("Prince Edward Island","PE");
        states.put("Puerto Rico","PR");
        states.put("Quebec","QC");
        states.put("Rhode Island","RI");
        states.put("Saskatchewan","SK");
        states.put("South Carolina","SC");
        states.put("South Dakota","SD");
        states.put("Tennessee","TN");
        states.put("Texas","TX");
        states.put("Utah","UT");
        states.put("Vermont","VT");
        states.put("Virgin Islands","VI");
        states.put("Virginia","VA");
        states.put("Washington","WA");
        states.put("West Virginia","WV");
        states.put("Wisconsin","WI");
        states.put("Wyoming","WY");
        states.put("Yukon Territory","YT");
        return states;
    }

    public UserValidation validateUserFields(final UserValidation result, final NewUser user ) {
        PasswordValidator pwv = new PasswordValidator( result );
        result.setEmailValid( EmailValidator.isValidMail( user.getEmail() ) );
        pwv.validateNewPass( user.getPassword(), user.getPasswordCheck() );

        result.setFirstNameValid( validateName( user.getFirstName() ) );
        result.setLastNameValid( validateName( user.getLastName() ) );

        return result;
    }

    public UserValidation validateNewAccount( final AccountValidation accountValidation, final NewAccount account ) {
        validateUserFields( accountValidation, account.getNewUser() );
        // TODO other checks
        accountValidation.setValidLocale(account.getLocale() != null);
        return accountValidation;
    }



}
