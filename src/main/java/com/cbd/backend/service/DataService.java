package com.cbd.backend.service;

import com.cbd.backend.model.dbo.Organization;
import com.cbd.backend.model.dbo.User;
import com.mongodb.WriteResult;

public interface DataService {
    /**
     * Save or update organization
     * @param organization
     * @return organization
     */
    Organization saveOrganization(Organization organization);

    /**
     * Sets enabled/disabled toggle
     * @param organizationName
     * @return
     */
    Organization disableOrganization(final String organizationName);

    /**
     * Verify if the use exists in database
     * @param name username
     * @return user
     */
    boolean userExists(String name );

    /**
     * set enabled toggle on user
     * @param username
     * @return
     */
    User disableUser( String username );

    /**
     * Save user to db
     * @param user
     * @return user
     */
    User saveUser(User user);

    /**
     * Retrieve user account from db
     * @param username username
     */
    User getUser(String username);

    /**
     * <p>
     *     Determines if there will be a username collision
     * </p>
     * @param username
     * @return (true) user does not exist
     */
    boolean verifyUsername( String username );

    /**
     * Verify organization exists
     * @param userName
     * @return
     */
    boolean organizationExists( String userName );


    /**
     * admin function used in integration tests to clean up organization db entries
     * Will only work in non prod environments (returns null in prod)
     * @param organizationId
     * @return
     */
    boolean deleteOrganization( String organizationId );
    /**
     * admin function used in integration tests to clean up user db entries
     * Will only work in non prod environments (returns null in prod)
     * @param user
     * @return
     */
    boolean deleteUser( String user );


}
