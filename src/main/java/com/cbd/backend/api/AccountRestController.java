package com.cbd.backend.api;

import com.cbd.backend.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by warrenvoelkl on 2016-11-24.
 */
public class AccountRestController {

    @Autowired
    AccountService accountService;
}
