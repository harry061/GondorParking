package com.interview.target.parking.model;

import com.interview.target.parking.model.parkingstructure.ParkingBuilding;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class ParkingSummary {

    @Getter
    @Setter
    private int parkingSystemId;
    @Getter
    @Setter
    private int totalSlots;
    @Getter
    @Setter
    private int freeSlots;
    @Getter
    @Setter
    private int reservedSlots;

    private List<ParkingBuilding> parkingBuildings;

    public List<ParkingBuilding> getParkingBuildings() {
        if (null == parkingBuildings) {
            parkingBuildings = new ArrayList<ParkingBuilding>();
        }
        return parkingBuildings;
    }
}
