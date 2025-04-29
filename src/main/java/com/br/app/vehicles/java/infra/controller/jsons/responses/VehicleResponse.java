package com.br.app.vehicles.java.infra.controller.jsons.responses;

public record VehicleResponse(
        String id,
        String mark,
        int year,
        String model,
        String description,
        String sold
) {
}
