package com.br.app.vehicles.java.infra.controller.http;

import com.br.app.vehicles.java.app.usecases.CreateVehicle;
import com.br.app.vehicles.java.domain.Vehicle;
import com.br.app.vehicles.java.infra.controller.jsons.requests.VehicleRequest;
import com.br.app.vehicles.java.infra.controller.jsons.responses.VehicleResponse;
import com.br.app.vehicles.java.infra.controller.mappers.VehicleMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/vehicles")
public class CreateVehicleController {

    private final CreateVehicle createVehicle;
    private final VehicleMapper vehicleMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VehicleResponse create(@Valid @RequestBody final VehicleRequest request) {
        final Vehicle domain = vehicleMapper.toDomain(request);
        final Vehicle response = createVehicle.create(domain);
        return vehicleMapper.toResponse(response);
    }

}
