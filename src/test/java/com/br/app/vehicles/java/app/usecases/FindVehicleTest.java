package com.br.app.vehicles.java.app.usecases;

import com.br.app.vehicles.java.app.repositories.IVehicleRepository;
import com.br.app.vehicles.java.domain.Search;
import com.br.app.vehicles.java.domain.Vehicle;
import com.br.app.vehicles.java.utils.JsonUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FindVehicleTest {

    @Mock
    private IVehicleRepository vehicleRepository;

    @InjectMocks
    private FindVehicle findVehicle;

    private final static int PAGE = 0;
    private final static int SIZE = 20;

    @Test
    void should_find_vehicles_from_last_week(){
        final Vehicle vehicle = JsonUtils.readJson("jsons/domain/vehicle_domain_1.json", Vehicle.class);

        when(vehicleRepository.findVehiclesFromLastWeek(PAGE,SIZE)).thenReturn(new PageImpl<>(List.of(vehicle), PageRequest.of(PAGE, SIZE), 1));

        final Page<Vehicle> vehiclePage = findVehicle.findVehiclesFromLastWeek(PAGE, SIZE);

        assertThat(vehiclePage.getContent().get(0)).usingRecursiveComparison().isEqualTo(vehicle);

        verify(vehicleRepository, times(1)).findVehiclesFromLastWeek(PAGE, SIZE);
    }

    @Test
    void should_find_vehicles_with_search(){
        final Vehicle vehicle = JsonUtils.readJson("jsons/domain/vehicle_domain_1.json", Vehicle.class);

        final Search search = JsonUtils.readJson("jsons/domain/search_domain_1.json", Search.class);

        when(vehicleRepository.findVehicles(PAGE,SIZE, search)).thenReturn(new PageImpl<>(List.of(vehicle), PageRequest.of(PAGE, SIZE), 1));

        final Page<Vehicle> vehiclePage = findVehicle.findVehicles(PAGE, SIZE, search);

        assertThat(vehiclePage.getContent().get(0)).usingRecursiveComparison().isEqualTo(vehicle);

        verify(vehicleRepository, times(1)).findVehicles(PAGE, SIZE, search);
    }
}
