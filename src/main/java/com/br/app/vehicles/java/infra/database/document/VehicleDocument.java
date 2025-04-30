package com.br.app.vehicles.java.infra.database.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "vehicles")
public class VehicleDocument {

    @Id
    private String id;
    @Field("marca")
    private String mark;
    @Field("ano")
    private int year;
    @Field("modelo")
    private String model;
    @Field("descricao")
    private String description;
    @Field("vendido")
    private Boolean sold;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
