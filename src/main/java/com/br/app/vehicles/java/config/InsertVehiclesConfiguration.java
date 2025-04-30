package com.br.app.vehicles.java.config;

import com.br.app.vehicles.java.infra.database.document.VehicleDocument;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.tools.javac.Main;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class InsertVehiclesConfiguration {

    private final ObjectMapper objectMapper;
    private final MongoTemplate mongoTemplate;

    private static final String VEHICLE_DOCUMENT = "vehicles";

    @PostConstruct
    private void insertInDataBase() {
        final List<VehicleDocument> documents = getDocumentAsListObject();
        mongoTemplate.remove(new Query(), VEHICLE_DOCUMENT);
        mongoTemplate.insertAll(documents);
    }

    private List<VehicleDocument> getDocumentAsListObject() {
        final var jsonPath = "documents/vehicles.json";
        try {
            final var resource = Optional.ofNullable(Main.class.getClassLoader().getResource(jsonPath))
                    .orElseThrow(() -> new IllegalArgumentException("Arquivo JSON não encontrado"));

            final var path = Paths.get(resource.toURI());
            final var jsonContent = Files.readString(path);

            return objectMapper.readValue(jsonContent, objectMapper.getTypeFactory().constructCollectionType(List.class, VehicleDocument.class));
        } catch (final URISyntaxException | IOException e) {
            throw new RuntimeException("Erro ao carregar o arquivo JSON", e);
        }
    }
}
