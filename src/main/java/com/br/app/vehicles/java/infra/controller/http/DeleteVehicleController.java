package com.br.app.vehicles.java.infra.controller.http;

import com.br.app.vehicles.java.app.usecases.DeleteVehicle;
import com.br.app.vehicles.java.infra.controller.jsons.responses.ErrorResponse;
import com.br.app.vehicles.java.infra.controller.mappers.VehicleMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/vehicles")
@Tag(name = "Remoção", description = "Endpoint relacionado a remoção de veículos")
public class DeleteVehicleController {

    private final DeleteVehicle deleteVehicle;
    private final VehicleMapper vehicleMapper;

    @Operation(
            summary = "Deletar veículo por ID",
            description = "Remove permanentemente um veículo existente com base no seu ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Veículo removido com sucesso"),
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
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(
            @Parameter(description = "ID do veículo a ser removido", example = "661f7e9be91cc909bfe9db7c")
            @PathVariable final String id) {
        deleteVehicle.delete(id);
    }


}
