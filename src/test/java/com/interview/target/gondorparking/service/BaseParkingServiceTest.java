package com.interview.target.gondorparking.service;


import com.interview.target.parking.exception.InvalidTokenException;
import com.interview.target.parking.exception.ParkingSlotsFullException;
import com.interview.target.parking.model.*;
import com.interview.target.parking.repository.ParkingSlotRepository;
import com.interview.target.parking.repository.ParkingTransactionRepository;
import com.interview.target.parking.service.BaseParkingService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseParkingServiceTest {

    @Mock
    private ParkingSlotRepository parkingSlotRepository;

    @Mock
    ParkingTransactionRepository parkingTransactionRepository;

    @InjectMocks
    BaseParkingService baseParkingService;

    @Test
    public void confirmSlotTest() throws Exception {
        ParkingSlot ps = ParkingSlot.builder().parkingSystemId(1).buildingId(1).floorId(1).laneId(1).slotId(1).isNearExit(Boolean.TRUE).slotStatus("F").vehicleType("C").build();
        List<ParkingSlot> lps = new ArrayList<>();
        lps.add(ps);
        ParkingTransaction pt = ParkingTransaction.builder().parkingBuildingId(1).parkingsystemID(1).floorId(1).laneId(1).parkedStatus("P").slotId(1).txnId(1).build();
        when(parkingSlotRepository.fetchParkingSlotByVehicleType(1, "C")).thenReturn(lps);
        when(parkingSlotRepository.save(any())).thenReturn(ps);
        when(parkingTransactionRepository.save(any())).thenReturn(pt);
        ParkingRequest pr = ParkingRequest.builder().parkingSystemId(1).vehicleType(VehicleType.CAR).build();
        Assert.assertNotNull(baseParkingService.confirmSlot(pr, slt -> slt.getIsNearExit() && slt.getSlotStatus().equals("F")));
    }

    @Test(expected = ParkingSlotsFullException.class)
    public void confirmSlotTestException() throws Exception {
        ParkingSlot ps = ParkingSlot.builder().parkingSystemId(1).buildingId(1).floorId(1).laneId(1).slotId(1).isNearExit(Boolean.TRUE).slotStatus("F").vehicleType("C").build();
        List<ParkingSlot> lps = new ArrayList<>();
        ParkingTransaction pt = ParkingTransaction.builder().parkingBuildingId(1).parkingsystemID(1).floorId(1).laneId(1).parkedStatus("P").slotId(1).txnId(1).build();
        when(parkingSlotRepository.fetchParkingSlotByVehicleType(1, "C")).thenReturn(lps);
        ParkingRequest pr = ParkingRequest.builder().parkingSystemId(1).vehicleType(VehicleType.BIKE).build();
        baseParkingService.confirmSlot(pr, slt -> slt.getIsNearExit() && slt.getSlotStatus().equals("F"));
    }

    @Test
    public void vacateSlotTest() throws Exception {
        ParkingTransaction pt = ParkingTransaction.builder().parkingBuildingId(1).parkingsystemID(1).floorId(1).laneId(1).parkedStatus("P").slotId(1).txnId(1).build();
        when(parkingTransactionRepository.findById(any())).thenReturn(Optional.of(pt));
        when(parkingTransactionRepository.save(any())).thenReturn(pt);
        ParkingSlot ps = ParkingSlot.builder().parkingSystemId(1).buildingId(1).floorId(1).laneId(1).slotId(1).isNearExit(Boolean.TRUE).slotStatus("F").vehicleType("C").build();
        when(parkingSlotRepository.fetchParkingSlotBySlotId(1,1,1,1,1)).thenReturn(ps);
        when(parkingSlotRepository.save(any())).thenReturn(ps);
        ParkingToken parkingToken = ParkingToken.builder().parkingSystemId(1).buildingId(1).floorId(1).laneId(1).txnId(1).slotId(1).parkingType(ParkingType.ELDER).build();
        Assert.assertNotNull(baseParkingService.vacateSlot(parkingToken));
    }

    @Test(expected = InvalidTokenException.class)
    public void vacateSlotTestException() throws Exception {
        when(parkingTransactionRepository.findById(any())).thenReturn(Optional.empty());
        ParkingToken parkingToken = ParkingToken.builder().parkingSystemId(1).buildingId(1).floorId(1).laneId(1).txnId(1).slotId(1).parkingType(ParkingType.ELDER).build();
        baseParkingService.vacateSlot(parkingToken);
    }
}
