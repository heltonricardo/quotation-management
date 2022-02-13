package br.inatel.quotation_management.dto;

import br.inatel.quotation_management.model.Quote;
import br.inatel.quotation_management.model.Stock;
import lombok.Getter;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class StockRequestDTO {

    String id;
    String stockId;
    Map<Date, Double> quotes;

    public Stock toObj() {

        List<Quote> quoteList = quotes
                .entrySet()
                .stream()
                .map(QuoteRequestDTO::toObj)
                .toList();

        return Stock.builder()
                .id(id)
                .stockId(stockId)
                .quotes(quoteList)
                .build();
    }
}
