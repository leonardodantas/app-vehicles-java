package com.br.app.vehicles.java.infra.controller.mappers;

import com.br.app.vehicles.java.domain.Vehicle;
import com.br.app.vehicles.java.infra.controller.jsons.requests.VehicleCreateRequest;
import com.br.app.vehicles.java.infra.controller.jsons.responses.VehicleResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VehicleMapper {

    Vehicle toDomain(final VehicleCreateRequest request);

    VehicleResponse toResponse(final Vehicle vehicle);
}
