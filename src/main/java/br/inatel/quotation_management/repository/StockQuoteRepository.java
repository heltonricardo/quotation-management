package br.inatel.quotation_management.repository;

import br.inatel.quotation_management.model.StockQuote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockQuoteRepository
        extends CrudRepository<StockQuote, String> {

    Optional<StockQuote> findByStockQuoteId(String stockQuoteId);
}
