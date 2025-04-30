package com.br.app.vehicles.java.infra.controller.jsons.requests;

import com.br.app.vehicles.java.infra.controller.annotations.MarkValid;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Dados para criação e atualização de um veículo")
public record VehicleRequest(

        @Schema(description = "Marca do veículo", example = "Toyota")
        @MarkValid(message = "{mark.valid}")
        String mark,

        @Schema(description = "Ano de fabricação (entre 1886 e 2100)", example = "2000")
        @Min(value = 1886, message = "{year.min}")
        @Max(value = 2100, message = "{year.max}")
        @NotNull(message = "{year.not.null}")
        Integer year,

        @Schema(description = "Modelo do veículo", example = "Corolla")
        @NotBlank(message = "{model.not.blank}")
        String model,

        @Schema(description = "Descrição do veículo", example = "Carro em ótimo estado")
        @NotBlank(message = "{description.not.blank}")
        String description,

        @Schema(description = "Indica se o veículo foi vendido", example = "false")
        @NotNull(message = "{sold.not.null}")
        Boolean sold
) {}

