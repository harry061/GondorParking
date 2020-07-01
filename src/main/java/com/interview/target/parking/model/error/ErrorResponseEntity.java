package com.interview.target.parking.model.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor@AllArgsConstructor
public class ErrorResponseEntity {

    private String errorCode;
    private String errorDescription;
}
