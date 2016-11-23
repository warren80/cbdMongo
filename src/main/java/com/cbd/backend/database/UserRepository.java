package com.cbd.backend.database;

import com.cbd.backend.model.Authority;
import com.cbd.backend.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository()
public interface UserRepository extends MongoRepository<User, String> {
    List<User> findAll();
    List<Authority> findAuthoritiesByUsername(String username);
    User findTopByUsernameOrderByLastUpdated(String username);
    User findByUsername(String username);

}
