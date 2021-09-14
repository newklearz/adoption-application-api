package com.p5.adoptions.service.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ApiError implements Serializable{

    private HttpStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyy HH:mm:ss")
    private final LocalDateTime timestamp;

    private String message;

    private String debugMessage;

    private List<Violation> violations = new ArrayList<>();

    public ApiError() {
        this.timestamp = LocalDateTime.now();

    }

    public ApiError(HttpStatus status, String message) {
        this();
        this.status = status;
        this.message = message;
    }

    public ApiError(HttpStatus status, String message, String debugMessage) {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = debugMessage;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public ApiError setStatus(HttpStatus status) {
        this.status = status;
        return this;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public ApiError setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public ApiError setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
        return this;
    }

    public List<Violation> getViolations() {
        return violations;
    }

    public ApiError setViolations(List<Violation> violations) {
        this.violations = violations;
        return this;
    }
}
