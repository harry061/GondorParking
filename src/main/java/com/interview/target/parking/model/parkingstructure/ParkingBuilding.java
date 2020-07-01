package com.interview.target.parking.model.parkingstructure;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class ParkingBuilding {
    @Getter
    @Setter
    private int buildingId;

    private List<Floor> floors;

    public List<Floor> getFloors() {
        if (null == floors) {
            floors = new ArrayList<Floor>();
        }
        return floors;
    }
}
