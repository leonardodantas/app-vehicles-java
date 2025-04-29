package com.br.app.vehicles.java.app.usecases;

import com.br.app.vehicles.java.app.exceptions.ResourceNotFoundException;
import com.br.app.vehicles.java.app.repositories.IVehicleRepository;
import com.br.app.vehicles.java.domain.Vehicle;
import com.br.app.vehicles.java.utils.JsonUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteVehicleTest {

    @Mock
    private IVehicleRepository vehicleRepository;

    @InjectMocks
    private DeleteVehicle deleteVehicle;

    private static final String ID = "661f7e9be91cc909bfe9db7c";

    @Test
    void should_delete_vehicle_by_id(){
        final Vehicle vehicle = JsonUtils.readJson("jsons/domain/vehicle_domain_2.json", Vehicle.class);

        when(vehicleRepository.findById(ID)).thenReturn(Optional.of(vehicle));

        Assertions.assertDoesNotThrow(() -> deleteVehicle.delete(ID));

        verify(vehicleRepository, times(1)).delete(ID);
    }

    @Test
    void should_throw_resource_not_found_exception_when_product_not_found(){
        when(vehicleRepository.findById(ID)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> deleteVehicle.delete(ID));

        verify(vehicleRepository, never()).delete(ID);
    }
}
