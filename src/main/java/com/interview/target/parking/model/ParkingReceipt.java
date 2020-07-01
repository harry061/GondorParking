package com.interview.target.parking.model;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class ParkingReceipt {

    private Timestamp parkedTime;
    private Timestamp ExitTime;


}
