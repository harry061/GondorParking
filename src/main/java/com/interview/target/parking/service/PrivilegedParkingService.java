package com.interview.target.parking.service;

import com.interview.target.parking.model.*;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Predicate;

public interface PrivilegedParkingService {

     Comparator<ParkingSlot> buildingComparator = (p1, p2) -> p1.getBuildingId().compareTo(p2.getBuildingId());

     Comparator<ParkingSlot> floorComparator = (p1, p2) -> p1.getFloorId().compareTo(p2.getFloorId());

    Predicate<ParkingSlot> slotNearExitPredicate = slt -> slt.getIsNearExit() && slt.getSlotStatus().equals("F");

    Predicate<ParkingSlot> slotPredicate = slt -> slt.getSlotStatus().equals("F");

    Predicate<ParkingSlot> elderParkingPredicate = slt -> slt.getSlotStatus().equals("R");


    ParkingToken reserveSlot(ParkingRequest parkingRequest) throws Exception;

    ParkingReceipt releaseSlot(ParkingToken parkingToken);

    boolean fit(ParkingType parkingType);
}
