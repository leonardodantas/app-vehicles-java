package com.br.app.vehicles.java.config;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateToLocalDateTimeConverter implements Converter<Date, LocalDateTime> {

    @Override
    public LocalDateTime convert(final Date source) {
        return LocalDateTime.ofInstant(source.toInstant(), ZoneId.of("America/Sao_Paulo"));
    }
}
