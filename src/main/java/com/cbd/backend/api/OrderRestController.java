package com.cbd.backend.api;

import com.cbd.backend.database.OrderRepository;
import com.cbd.backend.service.impl.OrderServiceImpl;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderRestController {

    @Autowired
    OrderServiceImpl os;
    static Logger log = Logger.getLogger(OrderRepository.class.getName());

    @RequestMapping(value="/order", method = RequestMethod.POST )
    @ResponseBody
    public String order(HttpServletRequest request, @RequestBody String jsonString) {
        String result = os.createOrder( jsonString );
        log.info("Create orderRequest From: " + request.getRemoteAddr() );
        return  result;
    }
}
