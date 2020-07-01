package com.interview.target.parking.repository;

import com.interview.target.parking.model.ParkingSlot;
import com.interview.target.parking.model.ParkingTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingTransactionRepository extends JpaRepository<ParkingTransaction, Integer> {

}
