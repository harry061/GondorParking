package com.interview.target.parking.admin.repository;

import com.interview.target.parking.model.parkingstructure.ParkingSystem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminParkingSystemRepository extends JpaRepository<ParkingSystem, Integer> {

}
