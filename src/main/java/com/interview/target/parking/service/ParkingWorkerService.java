package com.interview.target.parking.service;

import com.interview.target.parking.model.ParkingSlot;
import com.interview.target.parking.model.ParkingSummary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ParkingWorkerService {

    ParkingSummary retrieveParkingSummary(int parksystemId, Optional<Integer> parkbuildingId);

}
