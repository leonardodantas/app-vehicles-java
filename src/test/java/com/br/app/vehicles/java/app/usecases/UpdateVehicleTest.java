package com.br.app.vehicles.java.app.usecases;

import com.br.app.vehicles.java.app.exceptions.ResourceNotFoundException;
import com.br.app.vehicles.java.app.repositories.IVehicleRepository;
import com.br.app.vehicles.java.domain.Vehicle;
import com.br.app.vehicles.java.utils.JsonUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateVehicleTest {

    @Mock
    private IVehicleRepository vehicleRepository;

    @InjectMocks
    private UpdateVehicle updateVehicle;

    @Test
    void should_update_vehicle(){
        final Vehicle vehicle = JsonUtils.readJson("jsons/domain/vehicle_domain_1.json", Vehicle.class);

        final Vehicle vehicleExist = JsonUtils.readJson("jsons/domain/vehicle_domain_2.json", Vehicle.class);

        when(vehicleRepository.findById(vehicle.getId())).thenReturn(Optional.of(vehicleExist));
        when(vehicleRepository.save(ArgumentMatchers.any(Vehicle.class))).thenReturn(vehicleExist);

        final Vehicle result = updateVehicle.update(vehicle);

        assertThat(result).usingRecursiveComparison().isEqualTo(vehicleExist);

        verify(vehicleRepository, times(1)).findById(vehicle.getId());
        verify(vehicleRepository, times(1)).save(ArgumentMatchers.any(Vehicle.class));
    }

    @Test
    void should_throw_resource_not_found_exception_when_vehicle_not_found(){
        final Vehicle vehicle = JsonUtils.readJson("jsons/domain/vehicle_domain_1.json", Vehicle.class);

        when(vehicleRepository.findById(vehicle.getId())).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> updateVehicle.update(vehicle));

        verify(vehicleRepository, times(1)).findById(vehicle.getId());
        verify(vehicleRepository, never()).save(vehicle);
    }

    @Test
    void should_update_description_sold_vehicle(){
        final Vehicle vehicle = JsonUtils.readJson("jsons/domain/vehicle_domain_1.json", Vehicle.class);

        final Vehicle vehicleExist = JsonUtils.readJson("jsons/domain/vehicle_domain_2.json", Vehicle.class);

        when(vehicleRepository.findById(vehicle.getId())).thenReturn(Optional.of(vehicleExist));
        when(vehicleRepository.save(any(Vehicle.class))).thenReturn(vehicleExist);

        final Vehicle result = updateVehicle.updateDescriptionAndSold(vehicle);

        assertThat(result).usingRecursiveComparison().isEqualTo(vehicleExist);

        verify(vehicleRepository, times(1)).findById(vehicle.getId());
        verify(vehicleRepository, times(1)).save(any(Vehicle.class));
    }

    @Test
    void should_throw_resource_not_found_exception_when_try_update_description_and_sold(){
        final Vehicle vehicle = JsonUtils.readJson("jsons/domain/vehicle_domain_1.json", Vehicle.class);

        when(vehicleRepository.findById(vehicle.getId())).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> updateVehicle.updateDescriptionAndSold(vehicle));

        verify(vehicleRepository, times(1)).findById(vehicle.getId());
        verify(vehicleRepository, never()).save(vehicle);
    }

}
