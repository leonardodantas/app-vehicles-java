package com.br.app.vehicles.java.domain;

import lombok.*;

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

    public Vehicle of(final String description, final boolean sold) {
        return new Vehicle(
                this.id,
                this.mark,
                this.year,
                this.model,
                description,
                sold
        );
    }
}
