package com.interview.target.parking.model.parkingstructure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "PM_PARKING_STRUCTURE")
public class ParkingSystem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PS_SYSTEMID")
    private int parkingSystemID;

    @Column(name = "PS_BUILDINGID")
    private int buildingId;

    @Column(name = "PS_BUILDING_NAME")
    private String parkingbuildingName;

    @Column(name = "PS_NO_OF_FLOORS")
    private int floorCount;

    @Column(name = "PS_NO_OF_SLOTS")
    private int totalSlotCount;
}
