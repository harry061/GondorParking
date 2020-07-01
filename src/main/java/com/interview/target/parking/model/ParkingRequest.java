package com.interview.target.parking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkingRequest {

    private String vehicleNumber;
    private VehicleType vehicleType;
    private int parkingSystemId;
    private ParkingType parkingType;

}
