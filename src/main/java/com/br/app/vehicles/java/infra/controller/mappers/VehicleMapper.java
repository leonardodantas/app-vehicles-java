package com.br.app.vehicles.java.infra.controller.mappers;

import com.br.app.vehicles.java.domain.Vehicle;
import com.br.app.vehicles.java.infra.controller.jsons.requests.VehicleRequest;
import com.br.app.vehicles.java.infra.controller.jsons.requests.VehicleUpdateRequest;
import com.br.app.vehicles.java.infra.controller.jsons.responses.VehicleResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VehicleMapper {

    Vehicle toDomain(final VehicleRequest request);

    Vehicle toDomain(final String id, final VehicleRequest request);

    Vehicle toDomain(final String id, final VehicleUpdateRequest request);

    VehicleResponse toResponse(final Vehicle vehicle);
}
