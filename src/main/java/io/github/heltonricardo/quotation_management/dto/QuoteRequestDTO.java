package io.github.heltonricardo.quotation_management.dto;

import io.github.heltonricardo.quotation_management.model.Quote;

import java.util.Date;
import java.util.Map;

public class QuoteRequestDTO {

    public static Quote toObj(Map.Entry<Date, Double> entry) {

        return Quote.builder()
                .date(entry.getKey())
                .value(entry.getValue())
                .build();
    }
}
