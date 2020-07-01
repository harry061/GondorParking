package com.interview.target.parking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PT_TRANSACTION")
public class ParkingTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PT_TXN_ID")
    private Integer txnId;
    @Column(name = "PT_SYSTEMID")
    private Integer parkingsystemID;
    @Column(name = "PT_BUILDING_ID")
    private Integer parkingBuildingId;
    @Column(name = "PT_FLOOR_ID")
    private Integer floorId;
    @Column(name = "PT_LANE_ID")
    private Integer laneId;
    @Column(name = "PT_SLOT_ID")
    private Integer slotId;
    @Column(name = "SLOT_ALLOCATED_DATETIME")
    private Timestamp slotAllocatedTime;
    @Column(name = "SLOT_RETURNED_DATETIME")
    private Timestamp slotreturnedTime;
    @Column(name = "PT_VEHICLE_NUMBER")
    private String vehicleNumber;
    @Column(name = "PT_PARKED_STATUS")
    private String parkedStatus;

}
