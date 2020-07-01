package com.interview.target.parking.model;

public enum ParkingType {
    REGULAR("O"), ROYAL("R"), ELDER("E");

    private String type;

    ParkingType(String vtype) {
        this.type = vtype;
    }

    @Override
    public String toString() {
        return this.type;
    }
}
