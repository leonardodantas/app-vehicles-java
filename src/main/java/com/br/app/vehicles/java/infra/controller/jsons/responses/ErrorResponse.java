package com.br.app.vehicles.java.infra.controller.jsons.responses;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Modelo de resposta de erro padrão da API")
public record ErrorResponse(

        @Schema(description = "Momento em que o erro ocorreu", example = "2025-04-29T21:55:00")
        LocalDateTime timestamp,

        @Schema(description = "Código de status HTTP", example = "400")
        int status,

        @Schema(description = "Mensagem descritiva do erro", example = "Requisição inválida")
        String message,

        @Schema(description = "Caminho da requisição que gerou o erro", example = "/v1/vehicles")
        String path
) {}
