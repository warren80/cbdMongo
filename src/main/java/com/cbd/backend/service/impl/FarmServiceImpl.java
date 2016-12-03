package com.cbd.backend.service.impl;

import com.cbd.backend.common.ValidationHelper;
import com.cbd.backend.common.model.FarmValidation;
import com.cbd.backend.model.dbo.Farm;
import com.cbd.backend.model.NewFarm;
import com.cbd.backend.service.FarmService;
import com.cbd.backend.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FarmServiceImpl implements FarmService {


    //TODO subscription handling
    @Autowired
    DataService dataAccessService;

    @Override
    public Farm createFarm(Farm farm) {
        return dataAccessService.saveFarm( farm );
    }

    @Override
    public String deactivateFarm(String farmId) {
        return null;
    }

    @Override
    public String activateFarm(String farmId) {
        return null;
    }

    @Override
    public FarmValidation validateFarm(final NewFarm farm ) {
        FarmValidation farmValidation = new FarmValidation();
        new ValidationHelper().validateNewFarm( farmValidation, farm );
        farmValidation.setValidFarmPhone( farm.getFarmPhone() != null && farm.getFarmPhone().matches("\\d{10}" ) );
        farmValidation.setValidSecondaryPhoneNumber( farm.getSecondaryFarmPhone() == null  || ( farm.getSecondaryFarmPhone() != null && farm.getSecondaryFarmPhone().matches("\\d{10}" ) ) );

        farmValidation.setValidMeasurements( farm.getMeasurements() != null );
        farmValidation.setValidAddress( farm.getAddress() != null );
        farmValidation.setFarmValid( farm.getId() == null );
//        farmValidation.setValidBillingDetails( farm.getBillingDetails() != null );
        farmValidation.setValidFarmPlotScheme( true );
        farmValidation.setValidSubscription( true );
        farmValidation.setValidLocale( true );

        if ( ! dataAccessService.farmExists( farm.getFarmName() ) ) {
            farmValidation.setValidFarmName( true );
        }
        if ( ! dataAccessService.userExists( farm.getNewUser().getUsername() ) ) {
            farmValidation.setUsernameValid( true );
        }
        return farmValidation;
    }

    public void setDataAccessService( final DataService dataAccessService ) {
        this.dataAccessService = dataAccessService;
    }
}
