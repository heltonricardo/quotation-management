package br.inatel.quotation_management.repository;

import br.inatel.quotation_management.model.Stock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends CrudRepository<Stock, String> {
    Optional<Stock> findByStockId(String stockId);
}
