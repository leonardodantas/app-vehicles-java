package com.br.app.vehicles.java.app.repositories;

import com.br.app.vehicles.java.domain.Vehicle;

import java.util.Optional;

public interface IVehicleRepository {
    Vehicle save(final Vehicle vehicle);

    Optional<Vehicle> findById(final String id);

    void delete(final String id);
}
