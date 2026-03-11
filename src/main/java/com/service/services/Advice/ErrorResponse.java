package com.service.services.Advice;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {

    private LocalDateTime timeStamp;
    private String  Message;
    private HttpStatus statusCode;

    public ErrorResponse(String message, HttpStatus statusCode) {
        Message = message;
        this.statusCode = statusCode;
    }
}
