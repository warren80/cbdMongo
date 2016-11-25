package com.cbd.backend.database;

import com.cbd.backend.model.dbo.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository( "orderRepository" )
public interface OrderRepository extends MongoRepository<Order, String>{


}
