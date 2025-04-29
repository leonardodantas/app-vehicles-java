package com.br.app.vehicles.java.app.usecases;

import com.br.app.vehicles.java.app.exceptions.ResourceNotFoundException;
import com.br.app.vehicles.java.app.repositories.IVehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteVehicle {

    private final IVehicleRepository vehicleRepository;

    public void delete(final String id) {
        vehicleRepository.findById(id)
                .ifPresentOrElse(vehicle -> {
                    vehicleRepository.delete(id);
                }, () -> {
                    throw new ResourceNotFoundException("Vehicle not found");
                });
    }
}
