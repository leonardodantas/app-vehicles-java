package com.br.app.vehicles.java.infra.controller.http;

import com.br.app.vehicles.java.app.usecases.UpdateVehicle;
import com.br.app.vehicles.java.domain.Vehicle;
import com.br.app.vehicles.java.infra.controller.jsons.requests.VehicleRequest;
import com.br.app.vehicles.java.infra.controller.jsons.requests.VehicleUpdateRequest;
import com.br.app.vehicles.java.infra.controller.jsons.responses.VehicleResponse;
import com.br.app.vehicles.java.infra.controller.mappers.VehicleMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/vehicles")
public class UpdateVehicleController {

    private final UpdateVehicle updateVehicle;
    private final VehicleMapper vehicleMapper;

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public VehicleResponse update(@PathVariable final String id, @Valid @RequestBody final VehicleRequest request) {
        final Vehicle domain = vehicleMapper.toDomain(id, request);
        final Vehicle response = updateVehicle.update(domain);
        return vehicleMapper.toResponse(response);
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public VehicleResponse updateDescriptionAndSold(@PathVariable final String id, @Valid @RequestBody final VehicleUpdateRequest request) {
        final Vehicle domain = vehicleMapper.toDomain(id, request);
        final Vehicle response = updateVehicle.updateDescriptionAndSold(domain);
        return vehicleMapper.toResponse(response);
    }

}
