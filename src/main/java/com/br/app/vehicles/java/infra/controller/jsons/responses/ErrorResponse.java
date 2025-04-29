package com.br.app.vehicles.java.infra.controller.jsons.responses;

import java.time.LocalDateTime;

public record ErrorResponse(LocalDateTime timestamp,
                            int status,
                            String message,
                            String path) {
}