package com.cbd.backend.database;

import com.cbd.backend.model.dbo.Organization;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository()
public interface OrganizationRepository extends MongoRepository<Organization, String> {
    Organization findByOrganizationNameOrderByLastUpdatedDesc(String name);
    Organization findFirstByOrganizationName(String name );
    List<Organization> findAllByOrganizationName(String organizationName );
}
