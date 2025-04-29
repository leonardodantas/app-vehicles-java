package com.br.app.vehicles.java.infra.controller.jsons.responses;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resposta com os dados do veículo")
public record VehicleResponse(

        @Schema(description = "ID do veículo", example = "661f7e9be91cc909bfe9db7c")
        String id,

        @Schema(description = "Marca do veículo", example = "Ford")
        String mark,

        @Schema(description = "Ano de fabricação", example = "2000")
        int year,

        @Schema(description = "Modelo do veículo", example = "Fiesta")
        String model,

        @Schema(description = "Descrição adicional do veículo", example = "Compacto, econômico e com ar-condicionado")
        String description,

        @Schema(description = "Indica se o veículo foi vendido", example = "false")
        String sold

) {}

