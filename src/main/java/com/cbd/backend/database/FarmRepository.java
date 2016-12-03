package com.cbd.backend.database;

import com.cbd.backend.model.Account.dbo.Farm;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository()
public interface AccountRepository extends MongoRepository<Farm, String> {
    Farm findByAccountNameOrderByLastUpdatedDesc(String name);
    Farm findFirstByAccountName(String name );
}
