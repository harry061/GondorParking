package com.interview.target.parking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ParkingLaneId.class)
@Table(name = "PL_LANE")
public class ParkingLane {

    @Id
    @Column(name = "PL_SYSTEMID")
    private Integer parkingSystemId;
    @Id
    @Column(name = "PL_BUILDING_ID")
    private Integer buildingId;
    @Id
    @Column(name = "PL_FLOOR_ID")
    private Integer floorId;
    @Id
    @Column(name = "PL_LANE_ID")
    private Integer laneId;
    @Column(name = "PL_LANE_TYPE")
    private Integer laneType;
    @Column(name = "PL_NO_OF_SERVICEABLE_SLOTS")
    private Integer noServiceableSlots;

}
