package com.br.app.vehicles.java.infra.controller.http;

import com.br.app.vehicles.java.app.usecases.FindVehicle;
import com.br.app.vehicles.java.domain.Search;
import com.br.app.vehicles.java.infra.controller.jsons.responses.VehicleResponse;
import com.br.app.vehicles.java.infra.controller.mappers.VehicleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/vehicles")
public class FindVehicleController {

    private final FindVehicle findVehicle;
    private final VehicleMapper vehicleMapper;

    @GetMapping
    public Page<VehicleResponse> findVehicles(
            @RequestParam(required = false) final String available,
            @RequestParam(required = false) final Integer decade,
            @RequestParam(required = false) final String mark,
            @RequestParam(defaultValue = "0") final int page,
            @RequestParam(defaultValue = "20") final int size) {

        final Search search = Search.builder()
                .available(available)
                .decade(decade)
                .mark(mark)
                .build();

        return findVehicle.findVehicles(page, size, search)
                .map(vehicleMapper::toResponse);
    }

    @GetMapping
    public Page<VehicleResponse> findVehiclesFromLastWeek(
            @RequestParam(defaultValue = "0") final int page,
            @RequestParam(defaultValue = "20") final int size) {

        return findVehicle.findVehiclesFromLastWeek(page, size)
                .map(vehicleMapper::toResponse);
    }

}
