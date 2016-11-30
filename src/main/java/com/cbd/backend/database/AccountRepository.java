package com.cbd.backend.database;

import com.cbd.backend.model.Account.dbo.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository()
public interface AccountRepository extends MongoRepository<Account, String> {
    Account findByAccountNameOrderByLastUpdatedDesc(String name);
}
