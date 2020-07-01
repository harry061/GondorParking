package com.interview.target.parking.exception;

public class ParkingSlotsFullException extends RuntimeException {

    public ParkingSlotsFullException(String Message){
        super(Message);
    }
}
