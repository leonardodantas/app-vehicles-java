package com.br.app.vehicles.java.infra.controller.jsons.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VehicleUpdateRequest(
        @NotBlank(message = "{description.not.blank}")
        String description,
        @NotNull(message = "{sold.not.null}")
        Boolean sold
) {
}
