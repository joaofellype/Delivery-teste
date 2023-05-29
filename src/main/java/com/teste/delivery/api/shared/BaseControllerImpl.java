package com.teste.delivery.api.shared;

import com.teste.delivery.api.shared.error.ApiError;
import com.teste.delivery.core.domain.shared.exceptions.DomainException;
import com.teste.delivery.core.domain.shared.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

public abstract class BaseControllerImpl<T> {

    protected ResponseEntity<T> notFoundAggregate() {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    protected ResponseEntity<List<T>> notFound() {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    protected ResponseEntity<Void> created() {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    protected ResponseEntity<T> ok(T obj) {
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    protected ResponseEntity<List<T>> ok(List<T> items) {
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @ExceptionHandler(DomainException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiError> handleDomainException(DomainException exception) {
        return ResponseEntity
                .badRequest()
                .body(new ApiError(HttpStatus.BAD_REQUEST, exception.getErrorMessages()));
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiError> handleException(NotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiError(HttpStatus.NOT_FOUND, exception.getMessage()));
    }
}
