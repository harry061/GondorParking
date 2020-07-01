package com.interview.target.parking.controller;

import com.interview.target.parking.model.*;
import com.interview.target.parking.factory.ParkingSearchServiceFactory;
import com.interview.target.parking.service.ParkingWorkerService;
import com.interview.target.parking.service.PrivilegedParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/pworker")
public class ParkingController {

    @Autowired
    ParkingWorkerService parkingWorkerService;

    @Autowired
    ParkingSearchServiceFactory parkingSearchServiceFactory;

    @GetMapping(value = {"/retrieveparkingsummary/{parkingstructureId}", "/retrieveparkingsummary/{parkingstructureId}/{buildingId}"})
    public ParkingSummary numberOfSlotsAvailable(@PathVariable int parkingstructureId, @PathVariable Optional<Integer> buildingId) {
        return parkingWorkerService.retrieveParkingSummary(parkingstructureId, buildingId);
    }

    @PostMapping("/reserveslot")
    public ParkingToken reserveSlot(@RequestBody ParkingRequest parkingRequest) throws Exception {

        PrivilegedParkingService parkingService = parkingSearchServiceFactory.getSearchService(parkingRequest.getParkingType());
        ParkingToken slot = parkingService.reserveSlot(parkingRequest);
        return slot;
    }

    @PostMapping("/releaseslot")
    public ParkingReceipt releaseSlot(@RequestBody ParkingToken parkingToken) {
        PrivilegedParkingService parkingService = parkingSearchServiceFactory.getSearchService(parkingToken.getParkingType());
        ParkingReceipt parkingReceipt = parkingService.releaseSlot(parkingToken);
        return parkingReceipt;
    }

    //Write a fallback method just in case.
}
