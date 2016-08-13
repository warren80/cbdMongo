package com.cbd.backend.service.impl;

import com.cbd.backend.database.OrderRepository;
import com.cbd.backend.model.Order;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {
    @Mock
    OrderRepository repo;

    private Gson gson = new Gson();

    @Test
    public void createOrder()  {
        OrderServiceImpl SUT = createSUT();
        SUT.createOrder( jsonBody, ipAddress );
        assertNotNull(SUT);
    }


    @Test
    public void verifyGsonObjects()  {
        OrderServiceImpl SUT =createSUT();
        String str = SUT.createOrder( jsonBody, ipAddress );
        Order order = gson.fromJson(str, Order.class);

        assertNotNull( order.getId() );
    }

    OrderServiceImpl createSUT() {
        return new OrderServiceImpl( repo );
    }

    private String ipAddress = "127.0.0.1";
    private String jsonBody = "{ \"name\":\"Warren Voelkl\", \"customer\":{ \"email\":\"warrenvoelkl@gmail.com\", \"phoneNumber\":\"6043196009\", \"address\":{ \"city\":\"Vancouver\", \"streetNumber\":\"1311 Beach\", \"postalCode\":\"v6e1v6\", \"province\":\"BC\", \"country\":\"Canada\" }, \"OrderItems\":[ { \"itemId\":\"lkasj\", \"count\":2 }, { \"itemId\":\"ldka\", \"count\":3 } ] }}";

}

