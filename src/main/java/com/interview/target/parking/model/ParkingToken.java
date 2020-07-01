package com.interview.target.parking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkingToken {

    private Integer txnId;
    private Integer parkingSystemId;
    private Integer buildingId;
    private Integer floorId;
    private Integer laneId;
    private Integer slotId;
    private ParkingType parkingType;
    private Date parkingStartTime;



}
