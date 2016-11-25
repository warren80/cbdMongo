package com.cbd.backend.database;

import com.cbd.backend.model.dbo.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository()
public interface AccountRepository extends MongoRepository<Account, String> {
    List<Account> findAll();
    Account findByNameOrderByLastUpdatedAsc( String name );
}
