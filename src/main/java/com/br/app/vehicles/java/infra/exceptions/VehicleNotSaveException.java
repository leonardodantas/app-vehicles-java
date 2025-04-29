package com.br.app.vehicles.java.infra.exceptions;

import lombok.Getter;

@Getter
public class VehicleNotSaveException extends RuntimeException {

    private final String message;

    public VehicleNotSaveException(final String message, final Exception e) {
        super(e);
        this.message = message;
    }

}
