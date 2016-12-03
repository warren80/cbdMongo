package com.cbd.backend.database;

import com.cbd.backend.model.dbo.Farm;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository()
public interface FarmRepository extends MongoRepository<Farm, String> {
    Farm findByFarmNameOrderByLastUpdatedDesc(String name);
    Farm findFirstByFarmName(String name );
}
