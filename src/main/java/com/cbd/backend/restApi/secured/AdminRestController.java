package com.cbd.backend.restApi.secured;

import com.cbd.backend.model.dbo.Farm;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminRestController {

    @Value( "${api.auth}" )
    ResponseEntity<?> permanentDeleteFarm(@RequestBody Farm farm ) {

     return null;
    }




}
