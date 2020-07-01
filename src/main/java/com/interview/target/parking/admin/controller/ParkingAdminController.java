package com.interview.target.parking.admin.controller;

import com.interview.target.parking.model.parkingstructure.ParkingSystem;
import com.interview.target.parking.admin.repository.AdminParkingSystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/padmin")
public class ParkingAdminController {

    @Autowired
    private AdminParkingSystemRepository parkingRepo;

    @GetMapping("/createparkinglot")
    public ParkingSystem createParkingLot() {
        System.out.println("This is a message");
        ParkingSystem ps = ParkingSystem.builder().buildingId(1).parkingbuildingName("parkingOne").floorCount(5).totalSlotCount(100).build();
        parkingRepo.save(ps);
        return ps;
    }
}
