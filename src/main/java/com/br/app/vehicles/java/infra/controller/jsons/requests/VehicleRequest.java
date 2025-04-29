package com.br.app.vehicles.java.infra.controller.jsons.requests;

import com.br.app.vehicles.java.infra.controller.annotations.MarkValid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VehicleRequest(
        @MarkValid
        String mark,
        @Min(value = 1886, message = "{year.min}")
        @Max(value = 2100, message = "{year.max}")
        Integer year,
        @NotBlank
        String model,
        @NotBlank
        String description,
        @NotNull
        Boolean sold
) {
}
