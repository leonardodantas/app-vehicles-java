package com.br.app.vehicles.java.infra.controller.jsons.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VehicleUpdateRequest(
        @NotBlank
        String description,
        @NotNull
        Boolean sold
) {
}
