package com.cbd.backend.service.impl;

import com.cbd.backend.database.OrderRepository;
import com.cbd.backend.model.Order;
import com.cbd.backend.service.OrderService;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.google.gson.internal.$Gson$Preconditions.checkArgument;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository repo;

    @Autowired
    public OrderServiceImpl( @Qualifier("orderRepository") OrderRepository repo ){
        this.repo = repo;
    }

    Gson gson = new Gson();
    static Logger log = Logger.getLogger(OrderRepository.class.getName());
    @Override
    public String createOrder(String jSonBody, String ipAddress) {

        Order o = fromJson(jSonBody);
        System.out.println( o );

        log.debug("Ip Address: " + ipAddress + " Saving order " + o);
        Order savedOrder = repo.save(o);
        log.info("Saved order " + savedOrder);

        return gson.toJson(savedOrder);
    }

    private Order fromJson(String json) {
        checkArgument(!isNullOrEmpty(json));
        return gson.fromJson(json, Order.class);
    }
}
