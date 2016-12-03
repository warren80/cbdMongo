package com.cbd.backend.model;

import com.cbd.backend.model.dbo.Farm;
import com.cbd.backend.model.dbo.User;

/**
 * Created by warrenvoelkl on 2016-11-27.
 */
public class NewFarm extends Farm {


private NewUser newUser;

    public NewFarm(Farm farm, User user) {
        super();
        this.setLastUpdated( farm.getLastUpdated() );
        this.setFarmName( farm.getFarmName() );
        this.setFarmPhone( farm.getFarmPhone() );
        this.setAddress( farm.getAddress() );
        //this.setBillingDetails( farm.getBillingDetails() );
        this.setEnabled( farm.isEnabled() );
        this.setFarmPlotScheme( farm.getFarmPlotScheme() );
        this.setLanguageAndCountry( farm.getLanguageAndCountry() );
        this.setId( farm.getId() );
        this.setMeasurements( farm.getMeasurements() );
        this.setSubscriptionEndDate( farm.getSubscriptionEndDate() );

        NewUser u = new NewUser( user );
        this.setNewUser( u );
    }

    public NewFarm(){
        super();
    }

    public NewUser getNewUser() {
        return newUser;
    }

    public void setNewUser(NewUser newUser) {
        this.newUser = newUser;
    }
}
