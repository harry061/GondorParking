package com.interview.target.parking.model;

public enum VehicleType {
    BIKE("B"), CAR("C");

    private String type;

    VehicleType(String vtype) {
        this.type = vtype;
    }

    @Override
    public String toString() {
        return this.type;
    }
}
