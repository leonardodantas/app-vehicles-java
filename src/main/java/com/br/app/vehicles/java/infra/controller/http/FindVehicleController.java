package com.br.app.vehicles.java.infra.controller.http;

import com.br.app.vehicles.java.app.usecases.FindVehicle;
import com.br.app.vehicles.java.domain.Search;
import com.br.app.vehicles.java.infra.controller.jsons.responses.ErrorResponse;
import com.br.app.vehicles.java.infra.controller.jsons.responses.VehicleResponse;
import com.br.app.vehicles.java.infra.controller.mappers.VehicleMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/vehicles")
@Tag(name = "Lista", description = "Endpoints relacionados a listagem de veículos")
public class FindVehicleController {

    private final FindVehicle findVehicle;
    private final VehicleMapper vehicleMapper;

    @Operation(
            summary = "Buscar veículos com filtros",
            description = "Retorna uma lista paginada de veículos aplicando filtros opcionais por disponibilidade, década e marca."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Veículos encontrados com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping
    public Page<VehicleResponse> findVehicles(
            @Parameter(description = "Disponibilidade do veículo (ex: s ou n)", example = "s")
            @RequestParam(required = false) final String available,

            @Parameter(description = "Década de fabricação (ex: 1990, 2000)", example = "1990")
            @RequestParam(required = false) final Integer decade,

            @Parameter(description = "Marca do veículo (ex: Toyota, Honda)", example = "Toyota")
            @RequestParam(required = false) final String mark,

            @Parameter(description = "Número da página", example = "0")
            @RequestParam(defaultValue = "0") final int page,

            @Parameter(description = "Tamanho da página", example = "20")
            @RequestParam(defaultValue = "20") final int size) {

        final Search search = Search.builder()
                .available(available)
                .decade(decade)
                .mark(mark)
                .build();

        return findVehicle.findVehicles(page, size, search)
                .map(vehicleMapper::toResponse);
    }

    @Operation(
            summary = "Listar veículos da última semana",
            description = "Retorna uma lista paginada de veículos cadastrados nos últimos 7 dias com base no campo createdAt."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de veículos retornada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/last-week")
    public Page<VehicleResponse> findVehiclesFromLastWeek(
            @Parameter(description = "Número da página", example = "0")
            @RequestParam(defaultValue = "0") final int page,

            @Parameter(description = "Tamanho da página", example = "20")
            @RequestParam(defaultValue = "20") final int size) {

        return findVehicle.findVehiclesFromLastWeek(page, size)
                .map(vehicleMapper::toResponse);
    }


}
