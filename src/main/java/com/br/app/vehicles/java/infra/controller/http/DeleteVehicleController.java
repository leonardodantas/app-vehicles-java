package com.br.app.vehicles.java.infra.controller.http;

import com.br.app.vehicles.java.app.usecases.DeleteVehicle;
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
public class DeleteVehicleController {

    private final DeleteVehicle deleteVehicle;
    private final VehicleMapper vehicleMapper;

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable final String id) {
        deleteVehicle.delete(id);
    }

}
