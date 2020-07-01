package com.interview.target.parking.exception;

public class InvalidTokenException extends RuntimeException{

    public InvalidTokenException(String Message){
        super(Message);
    }
}
