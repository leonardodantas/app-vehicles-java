package com.br.app.vehicles.java.app.repositories;

import com.br.app.vehicles.java.domain.Search;
import com.br.app.vehicles.java.domain.Vehicle;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface IVehicleRepository {
    Vehicle save(final Vehicle vehicle);

    Optional<Vehicle> findById(final String id);

    void delete(final String id);

    Page<Vehicle> findVehiclesAvailable(final int page, final int size, final Search search);

    Page<Vehicle> findVehiclesFromLastWeek(final int page, final int size);
}
