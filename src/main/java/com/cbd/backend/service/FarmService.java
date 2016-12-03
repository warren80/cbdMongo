package com.cbd.backend.service;

import com.cbd.backend.common.model.FarmValidation;
import com.cbd.backend.model.dbo.Farm;
import com.cbd.backend.model.NewFarm;

public interface FarmService {
    Farm createFarm(final Farm farm );
    String deactivateFarm( final String farmId );
    String activateFarm( final String farmId );

    FarmValidation validateFarm(final NewFarm farm );


}
