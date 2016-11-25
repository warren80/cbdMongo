package com.cbd.backend.service.impl;

import com.cbd.backend.database.CustomerRepository;
import com.cbd.backend.database.OrderRepository;
import com.cbd.backend.model.dbo.Customer;
import com.cbd.backend.model.dbo.Order;
import com.cbd.backend.service.OrderService;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.google.gson.internal.$Gson$Preconditions.checkArgument;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public OrderServiceImpl( @Qualifier("orderRepository") OrderRepository repo,
                             @Qualifier("customerRepository") CustomerRepository customerRepository){
        this.orderRepository = repo;
        this.customerRepository = customerRepository;

    }


    Gson gson = new Gson();
    static Logger log = Logger.getLogger(OrderRepository.class.getName());
    @Override
    public String createOrder( String jSonBody ) {

        LocalDateTime dateTime = LocalDateTime.now();
        Order order = fromJson( jSonBody );

        Customer customer = order.getCustomer();
        saveCustomer( customer, dateTime );

        saveOrder( order, customer.getId(),  dateTime);

        return gson.toJson( order );
    }

    private Customer saveCustomer( Customer customer, LocalDateTime dateTime ) {
        customer.setDateTime( dateTime );
        customerRepository.save( customer );
        log.info( "customer Saved: " + customer.getId() );
        return customer;
    }

    private Order saveOrder( Order order, String customerId, LocalDateTime dateTime ) {
        order.setCustomerId( customerId );
        order.setCustomer( null );
        order.setDateTime( dateTime );
        orderRepository.save( order );
        log.info( "Order Saved: " + order.getId() );
        return order;
    }

    private Order fromJson(String json) {
        checkArgument(!isNullOrEmpty(json));
        return gson.fromJson(json, Order.class);
    }
}
