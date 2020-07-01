package com.interview.target.gondorparking.repository;

import com.interview.target.parking.model.ParkingSlot;
import com.interview.target.parking.repository.ParkingSlotRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParkingSlotRepositoryTest {

    @Autowired
    private ParkingSlotRepository parkingSlotRepository;

    @Test
    public void fetchParkingSlotsByBuildingTest() {
        List<ParkingSlot> slots = parkingSlotRepository.fetchParkingSlotsByBuilding(1, 1);
        Assert.assertEquals(100, slots.size());
    }

    @Test
    public void fetchParkingSlotsTest() {
        List<ParkingSlot> slots = parkingSlotRepository.fetchParkingSlots(1);
        Assert.assertEquals(104, slots.size());
    }

    @Test
    public void fetchParkingSlotByIdTest() {
        ParkingSlot slots = parkingSlotRepository.fetchParkingSlotBySlotId(1, 1, 1, 1, 1);
        Assert.assertNotNull(slots);
    }

    @Test
    public void fetchParkingSlotByVehicleTypeTest() {
        List<ParkingSlot> slots = parkingSlotRepository.fetchParkingSlotByVehicleType(1, "C");
        Assert.assertEquals(73, slots.size());
    }

}
