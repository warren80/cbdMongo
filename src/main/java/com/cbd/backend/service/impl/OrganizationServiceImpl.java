package com.cbd.backend.service.impl;

import com.cbd.backend.common.EmailValidator;
import com.cbd.backend.common.ValidationHelper;
import com.cbd.backend.common.model.OrganizationValidation;
import com.cbd.backend.model.dbo.Organization;
import com.cbd.backend.service.OrganizationService;
import com.cbd.backend.service.DataService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationServiceImpl implements OrganizationService {


    //TODO subscription handling
    static Logger log = Logger.getLogger( EmailValidator.class.getName() );
    @Autowired
    DataService dataAccessService;

    @Override
    public Organization createOrganization(Organization organization) {
        return dataAccessService.saveOrganization(organization);
    }

    @Override
    public String deactivateOrganization(String organizationId) {
        return null;
    }

    @Override
    public String activateOrganization(String organizationId) {
        return null;
    }

    @Override
    public OrganizationValidation validateOrganization(final Organization organization ) {
        OrganizationValidation organizationValidation = new OrganizationValidation();
        new ValidationHelper().validateNewOrganization( organizationValidation, organization );
        organizationValidation.setValidOrganizationPhone( organization.getOrganizationPhone() != null && organization.getOrganizationPhone().matches("\\d{10}" ) );
        organizationValidation.setValidSecondaryPhoneNumber( organization.getSecondaryOrganizationPhone() == null  || ( organization.getSecondaryOrganizationPhone() != null && organization.getSecondaryOrganizationPhone().matches("\\d{10}" ) ) );

        organizationValidation.setValidMeasurements( organization.getMeasurements() != null );
        organizationValidation.setValidAddress( organization.getAddress() != null );
//        organizationValidation.setOrganizationValid( organization.getId() == null );
        organizationValidation.setValidOrganizationPlotScheme( true );
        organizationValidation.setValidSubscription( true );
        organizationValidation.setValidLocale( true );

        if ( dataAccessService.organizationExists( organization.getOrganizationName() ) ) {
            log.debug( "Organization " + organization.getOrganizationName() + ": already Exists");

        } else {
            organizationValidation.setValidOrganizationName( true );
        }
        return organizationValidation;
    }

    @Override
    public boolean deleteOrganization(String organizationId) {
        return dataAccessService.deleteOrganization( organizationId );
    }

    public void setDataAccessService( final DataService dataAccessService ) {
        this.dataAccessService = dataAccessService;
    }
}
