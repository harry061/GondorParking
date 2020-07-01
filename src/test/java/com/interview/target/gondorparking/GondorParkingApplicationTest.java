package com.interview.target.gondorparking;

import com.interview.target.parking.controller.ParkingController;
import com.interview.target.parking.factory.ParkingSearchServiceFactory;
import com.interview.target.parking.repository.ParkingSlotRepository;
import com.interview.target.parking.repository.ParkingTransactionRepository;
import com.interview.target.parking.service.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GondorParkingApplicationTest {

    @Autowired
    private ParkingController parkingController;

    @Autowired
    private ParkingSlotRepository parkingSlotRepository;

    @Autowired
    private ParkingTransactionRepository parkingTransactionRepository;

    @Autowired
    private ElderlyParkingService elderlyParkingService;

    @Autowired
    private ParkingWorkerService parkingWorkerService;

    @Autowired
    private RegularParkingService regularParkingService;

    @Autowired
    private RoyalParkingService royalParkingService;

    @Autowired
    private ParkingSearchServiceFactory parkingSearchServiceFactory;

    @Test
    public void contextLoads() {
        Assert.assertNotNull(parkingController);
        Assert.assertNotNull(parkingSlotRepository);
        Assert.assertNotNull(parkingTransactionRepository);
       // Assert.assertNotNull(elderlyParkingService);
        Assert.assertNotNull(parkingWorkerService);
        //Assert.assertNotNull(privilegedParkingService);
        //Assert.assertNotNull(regularParkingService);
        //Assert.assertNotNull(royalParkingService);
    }

}