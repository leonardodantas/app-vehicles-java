package com.br.app.vehicles.java.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Vehicle {

    private String id;
    private String mark;
    private int year;
    private String model;
    private String description;
    private boolean sold;

}
