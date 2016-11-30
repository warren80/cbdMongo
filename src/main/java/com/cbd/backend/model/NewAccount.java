package com.cbd.backend.model;

import com.cbd.backend.model.Account.dbo.Account;
import com.cbd.backend.model.dbo.User;

/**
 * Created by warrenvoelkl on 2016-11-27.
 */
public class NewAccount extends Account {


private NewUser newUser;

    public NewAccount(Account savedAccount, User savedUser) {
        super();
        this.setLastUpdated( savedAccount.getLastUpdated() );
        this.setAccountName( savedAccount.getAccountName() );
        this.setAccountPhone( savedAccount.getAccountPhone() );
        this.setAddress( savedAccount.getAddress() );
        this.setBillingDetails( savedAccount.getBillingDetails() );
        this.setEnabled( savedAccount.isEnabled() );
        this.setFarmPlotScheme( savedAccount.getFarmPlotScheme() );
        this.setLocale( savedAccount.getLocale() );
        this.setId( savedAccount.getId() );
        this.setMeasurements( savedAccount.getMeasurements() );
        this.setSubscriptionEndDate( savedAccount.getSubscriptionEndDate() );
        NewUser u = new NewUser( savedUser );
        this.setNewUser( u );
    }

    public NewAccount(){
        super();
    }

    public NewUser getNewUser() {
        return newUser;
    }

    public void setNewUser(NewUser newUser) {
        this.newUser = newUser;
    }
}
