package com.br.app.vehicles.java.infra.controller.annotations;

import java.util.Arrays;

public enum MarkEnum {
    AUDI,
    BMW,
    CHEVROLET,
    FORD,
    HONDA,
    HYUNDAI,
    JAGUAR,
    JEEP,
    KIA,
    LAMBORGHINI,
    LAND_ROVER,
    MERCEDES_BENZ,
    NISSAN,
    PEUGEOT,
    PORSCHE,
    RENAULT,
    TOYOTA,
    VOLKSWAGEN,
    VOLVO,
    FIAT,
    MITSUBISHI,
    FERRARI,
    MAZDA,
    SUBARU,
    CHRYSLER,
    TESLA,
    ALFA_ROMEO,
    BENTLEY;

    public static boolean isValid(final String model) {
       return Arrays.stream(MarkEnum.values())
                .anyMatch(value -> value.name().equalsIgnoreCase(model));
    }
}
