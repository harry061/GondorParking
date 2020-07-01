package com.interview.target.parking.service;

import com.interview.target.parking.exception.ParkingSearchException;
import com.interview.target.parking.model.*;
import com.interview.target.parking.model.parkingstructure.Floor;
import com.interview.target.parking.model.parkingstructure.ParkingBuilding;
import com.interview.target.parking.repository.ParkingSlotRepository;
import com.interview.target.parking.repository.ParkingTransactionRepository;
import com.interview.target.parking.util.ParkingUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ParkingWorkerServiceImpl implements ParkingWorkerService {

    @Autowired
    private ParkingSlotRepository parkingSlotRepository;

    @Override
    public ParkingSummary retrieveParkingSummary(int parksystemId, Optional<Integer> parkbuildingId) {

        // Sort the Parking Slots based on the preferred input using the comparators.
        List<ParkingSlot> parkingslots = this.fetchAllParkingSlots(parksystemId, parkbuildingId);
        if (null == parkingslots || (parkingslots != null && parkingslots.isEmpty())) {
            throw new ParkingSearchException("Parking Entity doesn't exist");
        }
        ParkingSummary parkingSummary = new ParkingSummary();
        parkingSummary.setParkingSystemId(parksystemId);
        List<ParkingBuilding> parkingBuildings = parkingslots.stream().filter(ParkingUtility.distinctByKey(bId -> bId.getBuildingId())).map(temp -> {
            ParkingBuilding pb = new ParkingBuilding();
            pb.setBuildingId(temp.getBuildingId());
            return pb;
        }).collect(Collectors.toList());

        parkingSummary.getParkingBuildings().clear();
        parkingSummary.getParkingBuildings().addAll(parkingBuildings);

        for (ParkingBuilding pa : parkingBuildings) {
            List<Floor> floors = parkingslots.stream().filter(pad -> pa.getBuildingId() == (pad.getBuildingId())).filter(ParkingUtility.distinctByKey(df -> df.getFloorId())).map(temp -> {
                Floor flr = new Floor();
                flr.setFloorId(temp.getFloorId());
                Long freeSlots = parkingslots.stream().filter(pad -> pa.getBuildingId() == (pad.getBuildingId())).filter(df -> df.getFloorId() == temp.getFloorId()).filter(sst -> sst.getSlotStatus().equals("F")).count();
                Long reservedSlots = parkingslots.stream().filter(pad -> pa.getBuildingId() == (pad.getBuildingId())).filter(df -> df.getFloorId() == temp.getFloorId()).filter(sst -> sst.getSlotStatus().equals("R")).count();
                Long occupiedSlots = parkingslots.stream().filter(pad -> pa.getBuildingId() == (pad.getBuildingId())).filter(df -> df.getFloorId() == temp.getFloorId()).filter(sst -> sst.getSlotStatus().equals("O")).count();
                flr.setAvailableSlots(freeSlots.intValue());
                flr.setReservedSlots(reservedSlots.intValue());
                flr.setNoOfSlots(flr.getAvailableSlots() + flr.getReservedSlots() + occupiedSlots.intValue());
                parkingSummary.setFreeSlots(parkingSummary.getFreeSlots() + flr.getAvailableSlots());
                parkingSummary.setReservedSlots(parkingSummary.getReservedSlots() + flr.getReservedSlots());
                parkingSummary.setTotalSlots(parkingSummary.getTotalSlots() + flr.getNoOfSlots());
                return flr;
            }).collect(Collectors.toList());
            pa.getFloors().clear();
            pa.getFloors().addAll(floors);

        }
        return parkingSummary;
    }


    private List<ParkingSlot> fetchAllParkingSlots(int parkingSystemId, Optional<Integer> parkbuildingId) {
        if (parkbuildingId.isPresent()) {
            return parkingSlotRepository.fetchParkingSlotsByBuilding(parkingSystemId, parkbuildingId.get());
        } else {
            return parkingSlotRepository.fetchParkingSlots(parkingSystemId);
        }
    }
}
