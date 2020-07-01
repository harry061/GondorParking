package com.interview.target.gondorparking.service;

import com.interview.target.parking.model.ParkingSlot;
import com.interview.target.parking.model.ParkingSummary;
import com.interview.target.parking.repository.ParkingSlotRepository;
import com.interview.target.parking.service.ParkingWorkerServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParkingWorkerServiceImplTest {

    @Mock
    private ParkingSlotRepository parkingSlotRepository;

    @InjectMocks
    ParkingWorkerServiceImpl parkingWorkerService;

    @Test
    public void retrieveParkingSummaryTest() {
        ParkingSlot ps = ParkingSlot.builder().parkingSystemId(1).buildingId(1).floorId(1).laneId(1).slotId(1).isNearExit(Boolean.TRUE).slotStatus("F").vehicleType("C").build();
        List<ParkingSlot> lps = new ArrayList<>();
        lps.add(ps);
        when(parkingSlotRepository.fetchParkingSlots(anyInt())).thenReturn(lps);
        ParkingSummary psummary = parkingWorkerService.retrieveParkingSummary(1, Optional.empty());
        Assert.assertNotNull(psummary);
    }

    @Test
    public void retrieveParkingSummarywithBuildingIDTest() {
        ParkingSlot ps = ParkingSlot.builder().parkingSystemId(1).buildingId(1).floorId(1).laneId(1).slotId(1).isNearExit(Boolean.TRUE).slotStatus("F").vehicleType("C").build();
        List<ParkingSlot> lps = new ArrayList<>();
        lps.add(ps);
        when(parkingSlotRepository.fetchParkingSlotsByBuilding(anyInt(),anyInt())).thenReturn(lps);
        ParkingSummary psummary = parkingWorkerService.retrieveParkingSummary(1, Optional.of(1));
        Assert.assertNotNull(psummary);
    }
}
