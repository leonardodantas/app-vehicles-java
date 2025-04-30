package com.br.app.vehicles.java.integration;

import com.br.app.vehicles.java.config.MongoContainerTest;
import com.br.app.vehicles.java.infra.database.document.VehicleDocument;
import com.br.app.vehicles.java.utils.JsonUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DeleteVehicleControllerIntegrationTest extends MongoContainerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void should_delete_vehicle_by_id() {
        final VehicleDocument document = JsonUtils.readJson("jsons/document/vehicle_document_1.json", VehicleDocument.class);
        mongoTemplate.insert(document);

        final ResponseEntity<Void> response = restTemplate.exchange("/v1/vehicles/" + document.getId(), HttpMethod.DELETE, null, Void.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        final Query query = new Query(Criteria.where("_id").is(document.getId()));
        final VehicleDocument vehicleDocument = mongoTemplate.findOne(query, VehicleDocument.class);
        assertNull(vehicleDocument);
    }

    @Test
    void should_return_404_when_vehicle_not_found() {
        final VehicleDocument document = JsonUtils.readJson("jsons/document/vehicle_document_1.json", VehicleDocument.class);

        final ResponseEntity<Void> response = restTemplate.exchange("/v1/vehicles/" + document.getId(), HttpMethod.DELETE, null, Void.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
