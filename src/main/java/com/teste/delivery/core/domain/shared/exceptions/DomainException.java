package com.teste.delivery.core.domain.shared.exceptions;

import java.util.ArrayList;
import java.util.List;

public class DomainException extends RuntimeException {

    private final List<String> errorMessages;

    public DomainException(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public DomainException(String error) {

        this.errorMessages = new ArrayList<>();
        this.errorMessages.add(error);
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }
}
