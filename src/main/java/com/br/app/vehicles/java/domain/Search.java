package com.br.app.vehicles.java.domain;

import com.br.app.vehicles.java.infra.controller.annotations.MarkEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Search {

    private String available;
    private Integer decade;
    private String mark;

    public Boolean getAvailable() {
        if ("s".equalsIgnoreCase(available)) {
            return Boolean.TRUE;
        }
        if("n".equalsIgnoreCase(available)) {
            return Boolean.FALSE;
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
