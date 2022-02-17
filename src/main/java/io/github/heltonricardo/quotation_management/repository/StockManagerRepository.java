package io.github.heltonricardo.quotation_management.repository;

import io.github.heltonricardo.quotation_management.model.StockManager;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockManagerRepository
        extends CrudRepository<StockManager, String> {
}
