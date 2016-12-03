package com.cbd.backend.database;

import com.cbd.backend.model.dbo.Authority;
import com.cbd.backend.model.dbo.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository()
public interface UserRepository extends MongoRepository<User, String> {
    List<User> findAll();
    User findByUsername(String username);
    User findTopByUsernameOrderByLastUpdatedDesc(String username);
    User findFirstByUsername(String username);
}
