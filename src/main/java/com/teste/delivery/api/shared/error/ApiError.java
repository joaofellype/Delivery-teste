package com.teste.delivery.api.shared.error;

import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

public class ApiError {

    private final long timestamp;
    private final int status;
    private final HttpStatus error;
    private final List<String> errors;

    public ApiError(HttpStatus status, List<String> errors) {
        super();
        this.timestamp = Instant.now().getEpochSecond();
        this.status = status.value();
        this.error = status;
        this.errors = errors;
    }

    public ApiError(HttpStatus status, String errorMessage) {
        super();
        this.timestamp = Instant.now().getEpochSecond();
        this.status = status.value();
        this.error = status;
        errors = Arrays.asList(errorMessage);
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public HttpStatus getError() {
        return error;
    }

    public List<String> getErrors() {
        return errors;
    }
}
