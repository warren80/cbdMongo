package com.cbd.backend.service;

import com.cbd.backend.common.model.OrganizationValidation;
import com.cbd.backend.model.dbo.Organization;

public interface OrganizationService {
    Organization createOrganization( final Organization organization );
    String deactivateOrganization( final String organizationId );
    String activateOrganization( final String organizationId );
    OrganizationValidation validateOrganization(final Organization organization );
    boolean deleteOrganization( String organizationId );
}
