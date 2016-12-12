package com.cbd.backend.restApi.secured;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mobile.device.FunctionalTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.cbd.backend.TestFactories.createOrganization;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AdminRestControllerSlow extends FunctionalTest {



    @Test
    public void permanentlyDeleteOrganizationSlow() throws Exception {

    }
}