package com.szalai.adapter.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {
    private String message;
    private String details;
    private HttpStatus httpStatus;
    private Exception exception;

    public static CustomException connectionToClientFailure() {
        return new CustomException(
                "SERVICE UNAVAILABLE",
                "Connection to client could not be performed",
                HttpStatus.SERVICE_UNAVAILABLE,
                null
        );
    }
}
