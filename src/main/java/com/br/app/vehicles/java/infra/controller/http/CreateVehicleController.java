package com.br.app.vehicles.java.infra.controller.http;

import com.br.app.vehicles.java.app.usecases.CreateVehicle;
import com.br.app.vehicles.java.domain.Vehicle;
import com.br.app.vehicles.java.infra.controller.jsons.requests.VehicleRequest;
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
@Tag(name = "Novo", description = "Endpoint relacionado a criação de um novo veículo")
public class CreateVehicleController {

    private final CreateVehicle createVehicle;
    private final VehicleMapper vehicleMapper;

    @Operation(
            summary = "Criar um novo veículo",
            description = "Cadastra um novo veículo com marca, ano, modelo, descrição e status de venda."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Veículo criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos enviados na requisição",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VehicleResponse create(
            @Parameter(description = "Dados do veículo a ser criado", required = true)
            @Valid @RequestBody final VehicleRequest request) {

        final Vehicle domain = vehicleMapper.toDomain(request);
        final Vehicle response = createVehicle.create(domain);
        return vehicleMapper.toResponse(response);
    }


}
