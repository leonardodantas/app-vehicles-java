package com.br.app.vehicles.java.infra.database.respository;

import com.br.app.vehicles.java.app.repositories.IVehicleRepository;
import com.br.app.vehicles.java.domain.Search;
import com.br.app.vehicles.java.domain.Vehicle;
import com.br.app.vehicles.java.infra.database.document.VehicleDocument;
import com.br.app.vehicles.java.infra.database.mappers.VehicleDocumentMapper;
import com.br.app.vehicles.java.infra.exceptions.VehicleNotSaveException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class VehicleRepository implements IVehicleRepository {

    private final VehicleMongoRepository vehicleMongoRepository;
    private final VehicleDocumentMapper vehicleDocumentMapper;
    private final MongoTemplate mongoTemplate;

    @Override
    public Vehicle save(final Vehicle vehicle) {
        try {
            final VehicleDocument document = vehicleDocumentMapper.toDocument(vehicle);
            final VehicleDocument documentSave = vehicleMongoRepository.save(document);
            return vehicleDocumentMapper.toDomain(documentSave);
        } catch (final Exception e) {
            throw new VehicleNotSaveException("Error saving vehicle", e);
        }
    }

    @Override
    public Optional<Vehicle> findById(final String id) {
        return vehicleMongoRepository.findById(id)
                .map(vehicleDocumentMapper::toDomain);
    }

    @Override
    public void delete(final String id) {
        vehicleMongoRepository.deleteById(id);
    }

    @Override
    public Page<Vehicle> findVehicles(final int page, final int size, final Search search) {

        final List<Criteria> criteria = getCriteria(search);
        final Query query = getQuery(criteria);

        final PageRequest pageRequest = PageRequest.of(page, size);
        query.with(pageRequest);

        long total = mongoTemplate.count(query, Vehicle.class);

        final List<Vehicle> vehicle = mongoTemplate.find(query, VehicleDocument.class)
                .stream()
                .map(vehicleDocumentMapper::toDomain)
                .toList();

        return new PageImpl<>(vehicle, pageRequest, total);
    }

    @Override
    public Page<Vehicle> findVehiclesFromLastWeek(final int page, final int size) {
        final PageRequest pageRequest = PageRequest.of(page, size);
        final LocalDateTime oneWeekAgo = LocalDateTime.now(ZoneId.of("America/Sao_Paulo")).minusWeeks(1);
        return vehicleMongoRepository.findByCreatedAtAfter(oneWeekAgo, pageRequest)
                .map(vehicleDocumentMapper::toDomain);
    }

    private Query getQuery(final List<Criteria> criteria) {
        final Query query = new Query();

        if (!criteria.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[0])));
        }
        return query;
    }

    private List<Criteria> getCriteria(final Search search) {
        final List<Criteria> criteria = new ArrayList<>();

        if (Objects.nonNull(search.getDecade())) {
            criteria.add(Criteria.where("decade").is(search.getDecade()));
        }

        if (Objects.nonNull(search.getMark())) {
            criteria.add(Criteria.where("mark").is(search.getMark()));
        }

        if (Objects.nonNull(search.getAvailable())) {
            criteria.add(Criteria.where("available").is(search.getAvailable()));
        }

        return criteria;
    }
}
