package com.interview.target.parking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ParkingSlotId.class)
@Table(name = "PS_PARKING_SLOTS")
public class ParkingSlot implements Serializable {

    @Id
    @Column(name = "PAS_SYSTEMID")
    private Integer parkingSystemId;
    @Id
    @Column(name = "PAS_BUILDING_ID")
    private Integer buildingId;
    @Id
    @Column(name = "PAS_FLOOR_ID")
    private Integer floorId;
    @Id
    @Column(name = "PAS_LANE_ID")
    private Integer laneId;
    @Id
    @Column(name = "PAS_SLOT_ID")
    private Integer slotId;
    @Column(name = "PAS_SUBSLOTS_MAX_ALLOWED")
    private Integer subSlotsMaxAllowed;
    @Column(name = "PAS_SUBSLOTS_USED")
    private Integer subSlotsUsed;
    @Column(name = "PAS_SLOT_STATUS")
    private String slotStatus;
    @Column(name = "PAS_SLOT_EXIT")
    private Boolean isNearExit;
    @Column(name = "PAS_VEHICLE_TYPE")
    private String vehicleType;
}
