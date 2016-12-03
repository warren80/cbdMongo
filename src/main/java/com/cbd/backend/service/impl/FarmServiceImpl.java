package com.cbd.backend.service.impl;

import com.cbd.backend.common.ValidationHelper;
import com.cbd.backend.common.model.AccountValidation;
import com.cbd.backend.model.Account.dbo.Farm;
import com.cbd.backend.model.NewFarm;
import com.cbd.backend.service.AccountService;
import com.cbd.backend.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {


    //TODO subscription handling
    @Autowired
    DataService dataAccessService;

    @Override
    public Farm createAccount(Farm account) {
        return dataAccessService.saveAccount( account );
    }

    @Override
    public String deactivateAccount(String accountId) {
        return null;
    }

    @Override
    public String activateAccount(String accountId) {
        return null;
    }

    @Override
    public AccountValidation validateAccount(final NewFarm account ) {
        AccountValidation accountValidation = new AccountValidation();
        new ValidationHelper().validateNewAccount( accountValidation, account );
        accountValidation.setValidAccountPhone( account.getFarmPhone() != null && account.getFarmPhone().matches("\\d{10}" ) );
        accountValidation.setValidSecondaryPhoneNumber( account.getSecondaryAccountPhone() == null  || ( account.getSecondaryAccountPhone() != null && account.getSecondaryAccountPhone().matches("\\d{10}" ) ) );

        accountValidation.setValidMeasurements( account.getMeasurements() != null );
        accountValidation.setValidAddress( account.getAddress() != null );
        accountValidation.setAccountValid( account.getId() == null );
        accountValidation.setValidBillingDetails( account.getBillingDetails() != null );
        accountValidation.setValidFarmPlotScheme( true );
        accountValidation.setValidSubscription( true );
        accountValidation.setValidLocale( true );

        if ( ! dataAccessService.accountExists( account.getFarmName() ) ) {
            accountValidation.setValidAccountName( true );
        }
        if ( ! dataAccessService.userExists( account.getNewUser().getUsername() ) ) {
            accountValidation.setUsernameValid( true );
        }
        return accountValidation;
    }

    public void setDataAccessService( final DataService dataAccessService ) {
        this.dataAccessService = dataAccessService;
    }
}
