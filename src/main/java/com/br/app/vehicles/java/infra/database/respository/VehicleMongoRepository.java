package com.br.app.vehicles.java.infra.database.respository;

import com.br.app.vehicles.java.infra.database.document.VehicleDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VehicleMongoRepository extends MongoRepository<VehicleDocument, String> {
}
