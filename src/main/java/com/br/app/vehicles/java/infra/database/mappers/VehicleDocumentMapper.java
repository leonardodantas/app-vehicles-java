package com.br.app.vehicles.java.infra.database.mappers;

import com.br.app.vehicles.java.domain.Vehicle;
import com.br.app.vehicles.java.infra.database.document.VehicleDocument;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VehicleDocumentMapper {

    VehicleDocument toDocument(final Vehicle vehicle);

    Vehicle toDomain(final VehicleDocument vehicleDocument);

}
