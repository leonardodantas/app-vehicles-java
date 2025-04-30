package com.br.app.vehicles.java.app.usecases;

import com.br.app.vehicles.java.app.exceptions.ResourceNotFoundException;
import com.br.app.vehicles.java.app.repositories.IVehicleRepository;
import com.br.app.vehicles.java.domain.Vehicle;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateVehicle {

    private final IVehicleRepository vehicleRepository;

    public Vehicle update(final Vehicle vehicle) {
        final Vehicle vehicleExist = vehicleRepository.findById(vehicle.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found"));

        final Vehicle vehicleUpdate = vehicle.of(vehicleExist.getCreatedAt(), vehicleExist.getUpdatedAt());

        return vehicleRepository.save(vehicleUpdate);
    }

    public Vehicle updateDescriptionAndSold(final Vehicle vehicle) {
        final Vehicle vehicleUpdate = vehicleRepository.findById(vehicle.getId())
                .map(vehicleExist -> vehicleExist.of(vehicle.getDescription(), vehicle.isSold()))
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found"));

        return vehicleRepository.save(vehicleUpdate);
    }
}
