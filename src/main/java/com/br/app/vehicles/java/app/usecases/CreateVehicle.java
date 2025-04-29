package com.br.app.vehicles.java.app.usecases;

import com.br.app.vehicles.java.app.repositories.IVehicleRepository;
import com.br.app.vehicles.java.domain.Vehicle;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateVehicle {

    private final IVehicleRepository vehicleRepository;

    public Vehicle create(final Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }
}
