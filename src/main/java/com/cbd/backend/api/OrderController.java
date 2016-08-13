package com.cbd.backend.api;

import com.cbd.backend.service.impl.OrderServiceImpl;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    @Autowired
    OrderServiceImpl os;

    @RequestMapping(value="/order", method = RequestMethod.POST )
    @ResponseBody
    public String order(HttpServletRequest request, @RequestBody String jsonString) {
        String result = os.createOrder( jsonString, request.getRemoteAddr() );
        return  result;
    }
}
