package com.br.app.vehicles.java.domain;

import com.br.app.vehicles.java.infra.controller.annotations.MarkEnum;
import lombok.Builder;

import java.util.Objects;

@Builder
public class Search {

    private String available;
    private Integer decade;
    private String mark;

    public String getAvailable() {
        if ("s".equalsIgnoreCase(available) || "n".equalsIgnoreCase(available)) {
            return available;
        }
        return null;
    }

    public String getMark() {
        if (MarkEnum.isValid(mark)) {
            return mark;
        }
        return null;
    }

    public Integer getDecade() {
        if (Objects.nonNull(decade)) {
            return (decade / 10) * 10;
        }
        return null;
    }
}
