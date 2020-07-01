package com.interview.target.parking.service;

import com.interview.target.parking.model.*;
import com.interview.target.parking.repository.ParkingSlotRepository;
import com.interview.target.parking.repository.ParkingTransactionRepository;
import com.interview.target.parking.util.ParkingUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ElderlyParkingService extends BaseParkingService implements PrivilegedParkingService {

    @Override
    public ParkingToken reserveSlot(ParkingRequest parkingRequest) throws Exception {
        return this.confirmSlot(parkingRequest, elderParkingPredicate);
    }

    @Override
    public ParkingReceipt releaseSlot(ParkingToken parkingToken) {
        ParkingTransaction parkingTransaction = this.vacateSlot(parkingToken);
        ParkingReceipt parkingReceipt = ParkingReceipt.builder().parkedTime(parkingTransaction.getSlotAllocatedTime()).ExitTime(new Timestamp(new Date().getTime())).build();
        return parkingReceipt;
    }

    @Override
    public boolean fit(ParkingType parkingType) {
        return parkingType.toString().equals("E");
    }
}
