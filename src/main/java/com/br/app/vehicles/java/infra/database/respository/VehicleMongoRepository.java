package com.br.app.vehicles.java.infra.database.respository;

import com.br.app.vehicles.java.infra.database.document.VehicleDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;

public interface VehicleMongoRepository extends MongoRepository<VehicleDocument, String> {

    Page<VehicleDocument> findByCreatedAtAfter(final LocalDateTime oneWeekAgo, final PageRequest pageRequest);
}
