package com.br.app.vehicles.java.app.usecases;

import com.br.app.vehicles.java.app.repositories.IVehicleRepository;
import com.br.app.vehicles.java.domain.Vehicle;
import com.br.app.vehicles.java.utils.JsonUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateVehicleTest {

    @Mock
    private IVehicleRepository vehicleRepository;

    @InjectMocks
    private CreateVehicle createVehicle;

    @Test
    void should_save_vehicle(){
        final Vehicle vehicle = JsonUtils.readJson("jsons/domain/vehicle_domain_1.json", Vehicle.class);

        final Vehicle vehicleSave = JsonUtils.readJson("jsons/domain/vehicle_domain_2.json", Vehicle.class);

        when(vehicleRepository.save(vehicle)).thenReturn(vehicleSave);

        final Vehicle result = createVehicle.create(vehicle);

        assertThat(result).usingRecursiveComparison().isEqualTo(vehicleSave);

        verify(vehicleRepository, times(1)).save(vehicle);
    }
}
