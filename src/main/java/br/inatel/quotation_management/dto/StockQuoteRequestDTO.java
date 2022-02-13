package br.inatel.quotation_management.dto;

import br.inatel.quotation_management.model.Quote;
import br.inatel.quotation_management.model.StockQuote;
import lombok.Getter;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Getter
public class StockQuoteRequestDTO {

    String id;
    String stockQuoteId;
    Map<Date, Double> quotes;

    public StockQuote toObj() {

        List<Quote> quoteList = quotes
                .entrySet()
                .stream()
                .map(QuoteRequestDTO::toObj)
                .toList();

        return StockQuote.builder()
                .id(id)
                .stockQuoteId(stockQuoteId)
                .quotes(quoteList)
                .build();
    }
}
