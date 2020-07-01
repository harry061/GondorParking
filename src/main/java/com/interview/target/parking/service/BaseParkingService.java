package com.interview.target.parking.service;

import com.interview.target.parking.exception.InvalidTokenException;
import com.interview.target.parking.exception.ParkingSlotsFullException;
import com.interview.target.parking.model.*;
import com.interview.target.parking.repository.ParkingSlotRepository;
import com.interview.target.parking.repository.ParkingTransactionRepository;
import com.interview.target.parking.util.ParkingUtility;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BaseParkingService {

    @Autowired
    private ParkingSlotRepository parkingSlotRepository;

    @Autowired
    ParkingTransactionRepository parkingTransactionRepository;

    public ParkingToken confirmSlot(ParkingRequest parkingRequest, Predicate<ParkingSlot> parkingPredicate) throws Exception {

        //implement cache and evict the cache whenever a new slot is reserved or a new slot is released.
        List<ParkingSlot> parkingslots = parkingSlotRepository.fetchParkingSlotByVehicleType(parkingRequest.getParkingSystemId(), parkingRequest.getVehicleType().toString());
        List<ParkingSlot> distinctBuildings = parkingslots.stream().filter(ParkingUtility.distinctByKey(bId -> bId.getBuildingId())).sorted(PrivilegedParkingService.buildingComparator).collect(Collectors.toList());
        Optional<ParkingSlot> slot = searchSlot(distinctBuildings, parkingslots, parkingPredicate);
        Integer txnID = 0;
        if (slot.isPresent()) {
            txnID = this.updateParkingSlot(slot.get(), parkingRequest);
        } else {
            throw new ParkingSlotsFullException("Parking Space is Full.");
        }
        return ParkingToken.builder().parkingSystemId(parkingRequest.getParkingSystemId()).buildingId(slot.get().getBuildingId()).floorId(slot.get().getFloorId()).laneId(slot.get().getLaneId()).slotId(slot.get().getSlotId()).parkingStartTime(new Date()).txnId(txnID).parkingType(parkingRequest.getParkingType()).build();
    }

    private Optional<ParkingSlot> searchSlot(List<ParkingSlot> distinctBuildings, List<ParkingSlot> parkingslots, Predicate<ParkingSlot> parkingPredicate) {

        Optional<ParkingSlot> vehicleSlot = Optional.empty();
        //sort by building, comparator can be changed to change the sort order
        for (ParkingSlot ps : distinctBuildings) {
            vehicleSlot = parkingslots.stream().filter(bId -> bId.getBuildingId() == ps.getBuildingId()).sorted(PrivilegedParkingService.floorComparator).filter(parkingPredicate).findFirst();
            if (!vehicleSlot.isPresent()) {
                vehicleSlot = parkingslots.stream().filter(bId -> bId.getBuildingId() == ps.getBuildingId()).sorted(PrivilegedParkingService.floorComparator).filter(PrivilegedParkingService.slotPredicate).findFirst();
            }
            if (vehicleSlot.isPresent()) {
                return vehicleSlot;
            }
        }
        return vehicleSlot;
    }

    private Integer updateParkingSlot(ParkingSlot parkingSlot, ParkingRequest parkingRequest) {
        parkingSlot.setSlotStatus("O");
        parkingSlotRepository.save(parkingSlot);
        ParkingTransaction parkingTransaction = ParkingTransaction.builder().parkingsystemID(parkingSlot.getParkingSystemId()).parkingBuildingId(parkingSlot.getBuildingId()).floorId(parkingSlot.getFloorId()).laneId(parkingSlot.getLaneId()).parkedStatus("P").slotId(parkingSlot.getSlotId()).slotAllocatedTime(new Timestamp(new Date().getTime())).vehicleNumber(parkingRequest.getVehicleNumber()).build();
        parkingTransactionRepository.save(parkingTransaction);
        return parkingTransaction.getTxnId();
    }

    public ParkingTransaction vacateSlot(ParkingToken parkingToken) {
        Optional<ParkingTransaction> optParkingTransaction = parkingTransactionRepository.findById(parkingToken.getTxnId());
        if (!optParkingTransaction.isPresent() ||(optParkingTransaction.isPresent() && !optParkingTransaction.get().getParkedStatus().equals("P"))) {
            throw new InvalidTokenException("Invalid parking token.");
        }
        ParkingTransaction parkingTransaction = optParkingTransaction.get();
        parkingTransaction.setParkedStatus("V");
        parkingTransaction.setSlotreturnedTime(new Timestamp(new Date().getTime()));
        parkingTransactionRepository.save(parkingTransaction);
        ParkingSlot pSlot = parkingSlotRepository.fetchParkingSlotBySlotId(parkingTransaction.getParkingsystemID(), parkingTransaction.getParkingBuildingId(), parkingTransaction.getFloorId(), parkingTransaction.getLaneId(), parkingTransaction.getSlotId());
        pSlot.setSlotStatus("F");
        parkingSlotRepository.save(pSlot);
        return parkingTransaction;
    }
}
