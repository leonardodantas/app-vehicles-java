package com.br.app.vehicles.java.app.repositories;

import com.br.app.vehicles.java.domain.Vehicle;

public interface IVehicleRepository {
    Vehicle save(final Vehicle vehicle);
}
