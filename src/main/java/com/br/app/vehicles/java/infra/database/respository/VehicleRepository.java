package com.br.app.vehicles.java.infra.database.respository;

import com.br.app.vehicles.java.app.repositories.IVehicleRepository;
import com.br.app.vehicles.java.domain.Vehicle;
import com.br.app.vehicles.java.infra.database.document.VehicleDocument;
import com.br.app.vehicles.java.infra.database.mappers.VehicleDocumentMapper;
import com.br.app.vehicles.java.infra.exceptions.VehicleNotSaveException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class VehicleRepository implements IVehicleRepository {

    private final VehicleMongoRepository vehicleMongoRepository;
    private final VehicleDocumentMapper vehicleDocumentMapper;

    @Override
    public Vehicle save(final Vehicle vehicle) {
        try {
            final VehicleDocument document = vehicleDocumentMapper.toDocument(vehicle);
            final VehicleDocument documentSave = vehicleMongoRepository.save(document);
            return vehicleDocumentMapper.toDomain(documentSave);
        } catch (final Exception e) {
            throw new VehicleNotSaveException("Error saving vehicle", e);
        }
    }

    @Override
    public Optional<Vehicle> findById(final String id) {
        return vehicleMongoRepository.findById(id)
                .map(vehicleDocumentMapper::toDomain);
    }

    @Override
    public void delete(final String id) {
        vehicleMongoRepository.deleteById(id);
    }
}
