package br.inatel.quotation_management.repository;

import br.inatel.quotation_management.model.StockManager;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockManagerRepository
        extends CrudRepository<StockManager, String> {
}
