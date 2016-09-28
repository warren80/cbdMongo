package com.cbd.backend.database;

import com.cbd.backend.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository( "customerRepository" )
public interface CustomerRepository extends MongoRepository<Customer, String>  {
    List<Customer> findAll();
    Customer findTopByNameOrderByDateTime(String name);
}
