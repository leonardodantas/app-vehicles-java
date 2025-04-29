package com.br.app.vehicles.java.app.usecases;

import com.br.app.vehicles.java.app.repositories.IVehicleRepository;
import com.br.app.vehicles.java.domain.Search;
import com.br.app.vehicles.java.domain.Vehicle;
import com.mongodb.client.MongoIterable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindVehicle {

    private final IVehicleRepository vehicleRepository;

    public Page<Vehicle> findVehicles(final int page, final int size, final Search search) {
        return vehicleRepository.findVehiclesAvailable(page, size, search);
    }

    public Page<Vehicle> findVehiclesFromLastWeek(final int page, final int size) {
        return vehicleRepository.findVehiclesFromLastWeek(page, size);
    }
}
