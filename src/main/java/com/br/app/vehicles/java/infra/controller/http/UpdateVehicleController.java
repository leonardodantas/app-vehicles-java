package com.br.app.vehicles.java.infra.controller.http;

import com.br.app.vehicles.java.app.usecases.UpdateVehicle;
import com.br.app.vehicles.java.domain.Vehicle;
import com.br.app.vehicles.java.infra.controller.jsons.requests.VehicleRequest;
import com.br.app.vehicles.java.infra.controller.jsons.requests.VehicleUpdateRequest;
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
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/vehicles")
@Tag(name = "Atualização", description = "Endpoint relacionado a atualização de veículos")
public class UpdateVehicleController {

    private final UpdateVehicle updateVehicle;
    private final VehicleMapper vehicleMapper;

    @Operation(
            summary = "Atualizar completamente um veículo",
            description = "Atualiza todos os campos de um veículo existente com base no ID fornecido."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Veículo atualizado com sucesso"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Requisição inválida",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Veículo não encontrado"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno no servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public VehicleResponse update(
            @Parameter(description = "ID do veículo a ser atualizado", example = "661f7e9be91cc909bfe9db7c")
            @PathVariable final String id,
            @Valid @RequestBody final VehicleRequest request) {
        final Vehicle domain = vehicleMapper.toDomain(id, request);
        final Vehicle response = updateVehicle.update(domain);
        return vehicleMapper.toResponse(response);
    }

    @Operation(
            summary = "Atualizar parcialmente um veículo",
            description = "Atualiza apenas a descrição e o status de venda de um veículo com base no ID fornecido."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Veículo parcialmente atualizado com sucesso"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Requisição inválida",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Veículo não encontrado"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno no servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public VehicleResponse updateDescriptionAndSold(
            @Parameter(description = "ID do veículo a ser atualizado", example = "661f7e9be91cc909bfe9db7c")
            @PathVariable final String id,
            @Valid @RequestBody final VehicleUpdateRequest request) {
        final Vehicle domain = vehicleMapper.toDomain(id, request);
        final Vehicle response = updateVehicle.updateDescriptionAndSold(domain);
        return vehicleMapper.toResponse(response);
    }


}
