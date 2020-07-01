package com.interview.target.parking.factory;

import com.interview.target.parking.model.ParkingRequest;
import com.interview.target.parking.model.ParkingType;
import com.interview.target.parking.service.PrivilegedParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingSearchServiceFactory {

    @Autowired
   private  List<PrivilegedParkingService> parkingServices;

    public PrivilegedParkingService getSearchService(ParkingType parkingType) {
        return parkingServices.stream().filter(service->service.fit(parkingType)).findFirst().orElseThrow(IllegalArgumentException::new);
    }
}
