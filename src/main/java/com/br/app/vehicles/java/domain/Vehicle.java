package com.br.app.vehicles.java.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Vehicle {

    private String id;
    private String mark;
    private int year;
    private String model;
    private String description;
    private boolean sold;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Vehicle of(final String description, final boolean sold) {
        return new Vehicle(
                this.id,
                this.mark,
                this.year,
                this.model,
                description,
                sold,
                this.createdAt,
                this.updatedAt
        );
    }

    public Vehicle of(final LocalDateTime createdAt, final LocalDateTime updatedAt) {
        return new Vehicle(
                this.id,
                this.mark,
                this.year,
                this.model,
                this.description,
                this.sold,
                createdAt,
                updatedAt
        );
    }
}
