package com.interview.target.parking.model.parkingstructure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Floor {

    private int floorId;
    private int noOfSlots;
    private int reservedSlots;
    private int availableSlots;
}
