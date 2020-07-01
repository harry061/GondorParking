package com.interview.target.parking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingLaneId implements Serializable {
    private Integer parkingSystemId;
    private Integer buildingId;
    private Integer floorId;
    private Integer laneId;
}
