package com.br.app.vehicles.java.integration;

import com.br.app.vehicles.java.config.MongoContainerTest;
import com.br.app.vehicles.java.infra.controller.jsons.requests.VehicleRequest;
import com.br.app.vehicles.java.infra.controller.jsons.responses.VehicleResponse;
import com.br.app.vehicles.java.infra.database.document.VehicleDocument;
import com.br.app.vehicles.java.utils.JsonUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreateVehicleControllerIntegrationTest extends MongoContainerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void should_create_vehicle() {
        final VehicleRequest request = JsonUtils.readJson("jsons/request/vehicle_request_1.json", VehicleRequest.class);

        final ResponseEntity<VehicleResponse> response = restTemplate.postForEntity("/v1/vehicles", request, VehicleResponse.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        final VehicleResponse vehicleResponse = response.getBody();

        final Query query = new Query(Criteria.where("_id").is(vehicleResponse.id()));
        final VehicleDocument vehicleDocument = mongoTemplate.findOne(query, VehicleDocument.class);
        assertNotNull(vehicleDocument);
    }

    @ParameterizedTest
    @MethodSource("getRequest")
    void should_return_400_when_invalid_request(final VehicleRequest request) {
        final ResponseEntity<VehicleResponse> response = restTemplate.postForEntity("/v1/vehicles", request, VehicleResponse.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    public static Stream<Arguments> getRequest() {
        return Stream.of(
                Arguments.of(JsonUtils.readJson("jsons/request/vehicle_request_1_description_invalid.json", VehicleRequest.class)),
                Arguments.of(JsonUtils.readJson("jsons/request/vehicle_request_1_mark_invalid.json", VehicleRequest.class)),
                Arguments.of(JsonUtils.readJson("jsons/request/vehicle_request_1_model_invalid.json", VehicleRequest.class)),
                Arguments.of(JsonUtils.readJson("jsons/request/vehicle_request_1_sold_invalid.json", VehicleRequest.class)),
                Arguments.of(JsonUtils.readJson("jsons/request/vehicle_request_1_year_invalid.json", VehicleRequest.class))
        );
    }
}
